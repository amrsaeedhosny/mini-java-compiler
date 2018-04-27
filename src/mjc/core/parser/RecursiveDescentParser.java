package mjc.core.parser;

import java.util.ArrayList;

import mjc.core.parser.interfaces.*;
import mjc.models.Token;

public class RecursiveDescentParser {

	private ArrayList<Token> codeTokens;
	private Token lookahead;
	private IGoal goalRootNode;

	public RecursiveDescentParser(ArrayList<Token> codeTokens) {
		this.codeTokens = refine(codeTokens);
		lookahead = this.codeTokens.get(0); // references first token in list
	}

	/**
	 * A method to prepare refine the tokens list for parsing
	 * @param tokens tokens after refining
	 * @return
	 */
	private ArrayList<Token> refine(ArrayList<Token> tokens) {
		for (int i = 0; i< tokens.size(); i++) {
			Token t = tokens.get(i);
			if (t.type.equals(Token.EOL_TOKEN_TYPE)) {
				// remove EOL tokens from the tokens list
				tokens.remove(t);
				i--;
			}
		}
//		printCodeTokens(tokens);
		return tokens;
	}
	
	private static void printCodeTokens(ArrayList<Token> matchedTokens) {
		System.out.println("----------------------------- Matched Code Tokens ----------------------------");
		for (Token token: matchedTokens) {
			String tokenLabel = token.getType();
			String tokenValue = token.getValue();
			if (tokenLabel.equals("EOL")) {
				tokenValue = "ENDOFLINE";
			}
			if (tokenLabel.equals(Token.UNKNOWN_TOKEN_TYPE)) {
				System.out.println("ERROR "+ "< "+ tokenLabel +" > : " +" '" + tokenValue + "' This token did not match any RE @ index " + token.getStartIndex());
			}
			else {
				System.out.println("< "+ tokenLabel +" > : "+ "-" + tokenValue + "-");
			}
			
		}
		System.out.println("---------------------------------------------------------------------");
	}

	private void nextToken() {
		if (!codeTokens.isEmpty()) { // null safety
			codeTokens.remove(0);
		}

		// at the end of input we return an EOF token
		if (codeTokens.isEmpty())
			lookahead = new Token(Token.EOF_TOKEN_TYPE, "\n");
		else
			lookahead = codeTokens.get(0);
	}

	private void printSyntaxError(Token errorLocation, String expected) {
		System.out.println("Unexpected token found at: " + errorLocation.getValue() + ", expected: " + expected);
	}

	private void printSyntaxError(String msg) {
		System.out.println(msg);
	}

	public IGoal parse() {

		goalRootNode = goal();
		if (goalRootNode == null) {
			printSyntaxError("parse(): goalRootNode == null");
		}

		return goalRootNode;
	}

	private IGoal goal() {
		// Goal ::= MainClass ClassDeclaration` <EOF>

		IMainClass mainClass = mainClass();
		if (mainClass == null) {
			printSyntaxError("goal(): mainClass == null");
			return null;
		}

		IClassDeclarationPrime classDeclarationPrime = classDeclarationPrime();
		if (classDeclarationPrime == null) {
			printSyntaxError("goal(): classDeclarationPrime == null");
			return null;
		}

		if (!lookahead.type.equals(Token.EOF_TOKEN_TYPE)) {
			printSyntaxError(lookahead, Token.EOF_TOKEN_TYPE);
			return null;
		}

		return new Goal(mainClass, classDeclarationPrime);
	}


	private IClassDeclarationPrime classDeclarationPrime() {
		// ClassDeclaration` ::= ClassDeclaration ClassDeclaration` | lambda

		IClassDeclaration classDeclaration = classDeclaration();
		if (classDeclaration == null) {
			return new ClassDeclarationPrime2();
		}

		IClassDeclarationPrime classDeclarationPrime = classDeclarationPrime();
		// Why didn't check if classDeclarationPrime == null ???
		// Ans: This function can never return null
		
		return new ClassDeclarationPrime1(classDeclaration, classDeclarationPrime);
	}

	private IClassDeclaration classDeclaration() {
		/*
		 * “class” Identifier Extends` “{“ VarDeclaration` ConstructorDeclaration`
		 * MethodDeclaration` “}”
		 */

		if (!lookahead.value.equals("class")) {
			printSyntaxError(lookahead, "class");
			return null;
		}
		nextToken();

		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("classDeclaration(): identifier == null");
			return null;
		}

		IExtendsPrime extendsPrime = extendsPrime();
		if (extendsPrime == null) {
			printSyntaxError("classDeclaration(): extendsPrime == null");
			return null;
		}

		if (!lookahead.value.equals("{")) {
			printSyntaxError(lookahead, "{");
			return null;
		}
		nextToken();

		IVarDeclarationPrime varDeclarationPrime = varDeclarationPrime();
		if (varDeclarationPrime == null) {
			printSyntaxError("classDeclaration(): varDeclarationPrime == null");
			return null;
		}

		IConstructorDeclarationPrime constructorDeclarationPrime = constructorDeclarationPrime();
		if (constructorDeclarationPrime == null) {
			printSyntaxError("classDeclaration(): constructorDeclarationPrime == null");
			return null;
		}

		IMethodDeclarationPrime methodDeclarationPrime = methodDeclarationPrime();
		if (methodDeclarationPrime == null) {
			printSyntaxError("classDeclaration(): methodDeclarationPrime == null");
			return null;
		}

		if (!lookahead.value.equals("}")) {
			printSyntaxError(lookahead, "}");
			return null;
		}
		nextToken();

		return new ClassDeclaration(identifier, extendsPrime, varDeclarationPrime, constructorDeclarationPrime,
				methodDeclarationPrime);
	}

	private IExtendsPrime extendsPrime() {
		// Extends` ::= “extends” Identifier | lambda

		if (!lookahead.value.equals("extends")) {
			return new ExtendsPrime2();
		}
		nextToken();

		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("extendsPrime(): identifier == null");
			return null;
		}

		return new ExtendsPrime1(identifier);
	}

	private IMainClass mainClass() {
		/*
		 * MainClass ::= “class” Identifier “{“ “public” “static” “void” “main” “(“
		 * “String” “[“ “]” Identifier “)” “{“ Statement “}” “}”
		 */
		if (!lookahead.value.equals("class")) {
			printSyntaxError(lookahead, "class");
			return null;
		}
		nextToken();

		IIdentifier identifier1 = identifier();
		if (identifier1 == null) {
			printSyntaxError("mainClass(): identifier1 == null");
			return null;
		}

		if (!lookahead.value.equals("{")) {
			printSyntaxError(lookahead, "{");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("public")) {
			printSyntaxError(lookahead, "public");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("static")) {
			printSyntaxError(lookahead, "static");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("void")) {
			printSyntaxError(lookahead, "void");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("main")) {
			printSyntaxError(lookahead, "main");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("(")) {
			printSyntaxError(lookahead, "(");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("String")) {
			printSyntaxError(lookahead, "String");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("[")) {
			printSyntaxError(lookahead, "[");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("]")) {
			printSyntaxError(lookahead, "]");
			return null;
		}
		nextToken();

		IIdentifier identifier2 = identifier();
		if (identifier2 == null) {
			printSyntaxError("mainClass(): identifier2 == null");
			return null;
		}

		if (!lookahead.value.equals(")")) {
			printSyntaxError(lookahead, ")");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("{")) {
			printSyntaxError(lookahead, "{");
			return null;
		}
		nextToken();

		IStatement statement = statement();
		if (statement == null) {
			printSyntaxError("mainClass(): statement == null");
			return null;
		}

		if (!lookahead.value.equals("}")) {
			printSyntaxError(lookahead, "}");
			return null;
		}
		nextToken();

		if (!lookahead.value.equals("}")) {
			printSyntaxError(lookahead, "}");
			return null;
		}
		nextToken();

		return new MainClass(identifier1, identifier2, statement);
	}

	// Ahmed's Work starts here

	private IVarDeclarationPrime varDeclarationPrime() {
		// VarDeclaration` ::= VarDeclaration VarDeclaration` | λ
		IVarDeclaration varDeclaration = varDeclaration();
		if (varDeclaration == null) {
			return new VarDeclarationPrime2();
		}

		IVarDeclarationPrime varDeclarationPrime = varDeclarationPrime();
		if (varDeclarationPrime == null) {
			printSyntaxError("varDeclarationPrime(): varDeclarationPrime == null");
			return null;
		}
		return new VarDeclarationPrime1(varDeclaration, varDeclarationPrime);
	}

	private IVarDeclaration varDeclaration() {
		// VarDeclaration ::= Type Identifier “;”
		IType type = type();
		if (type == null) {
			printSyntaxError("varDeclaration(): type == null");
			return null;
		}

		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("varDeclaration(): identifier == null");
			return null;
		}

		if (!lookahead.getValue().equals(";")) {
			printSyntaxError(lookahead, ";");
			return null;
		}
		nextToken();

		return new VarDeclaration(identifier, type);
	}

	private IType type() {
		// Type ::= (“int” | “boolean” | “float” | “String” | “char”) Brackets

		String dataType = lookahead.getValue();
		if (!dataType.equals("int") && !dataType.equals("boolean") && !dataType.equals("float")
				&& !dataType.equals("String") && !dataType.equals("char")) {
			printSyntaxError(lookahead, "Data Type");
			return null;
		}

		IBrackets brackets = brackets();
		if (brackets == null) {
			printSyntaxError("type(): brackets == null");
			return null;
		}

		if (dataType.equals("int"))
			return new Type1(brackets);
		if (dataType.equals("boolean"))
			return new Type2(brackets);
		if (dataType.equals("float"))
			return new Type3(brackets);
		if (dataType.equals("String"))
			return new Type4(brackets);
		if (dataType.equals("char"))
			return new Type5(brackets);
		return null;
	}

	private IBrackets brackets() {
		// Brackets ::= “[“ “]” | λ

		if (!lookahead.getValue().equals("[")) {
			return new Brackets2();
		}
		nextToken();

		if (!lookahead.getValue().equals("]")) {
			printSyntaxError(lookahead, "]");
			return null;
		}
		nextToken();

		return new Brackets1();
	}

	private IConstructorDeclarationPrime constructorDeclarationPrime() {
		// ConstructorDeclaration` ::= ConstructorDeclaration ConstructorDeclaration` |
		// λ

		IConstructorDeclaration constructorDeclaration = constructorDeclaration();
		if (constructorDeclaration == null) {
			return new ConstructorDeclarationPrime2();
		}

		IConstructorDeclarationPrime constructorDeclarationPrime = constructorDeclarationPrime();
		if (constructorDeclarationPrime == null) {
			printSyntaxError("constructorDeclarationPrime(): constructorDeclaration == null");
			return null;
		}

		return new ConstructorDeclarationPrime1(constructorDeclarationPrime, constructorDeclaration);
	}

	private IConstructorDeclaration constructorDeclaration() {
		// ConstructorDeclatation ::= Identifier “(“ TypeIdentifier “)” “{“
		// VarDeclaration` Statement` “}”

		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("constructorDeclaration(): identifier == null");
			return null;
		}

		if (!lookahead.getValue().equals("(")) {
			printSyntaxError(lookahead, "(");
			return null;
		}
		nextToken();

		ITypeIdentifier typeIdentifier = typeIdentifier();
		if (typeIdentifier == null) {
			printSyntaxError("constructorDeclaration(): typeIdentifier == null");
			return null;
		}

		if (!lookahead.getValue().equals(")")) {
			printSyntaxError(lookahead, ")");
			return null;
		}
		nextToken();

		if (!lookahead.getValue().equals("{")) {
			printSyntaxError(lookahead, "{");
			return null;
		}
		nextToken();

		IVarDeclarationPrime varDeclarationPrime = varDeclarationPrime();
		if (varDeclarationPrime == null) {
			printSyntaxError("constructorDeclaration(): varDeclarationPrime == null");
			return null;
		}

		IStatmentPrime statmentPrime = statmentPrime();
		if (statmentPrime == null) {
			printSyntaxError("constructorDeclaration(): statmentPrime == null");
			return null;
		}

		if (!lookahead.getValue().equals("}")) {
			printSyntaxError(lookahead, "}");
			return null;
		}
		nextToken();

		return new ConstructorDeclaration(identifier, typeIdentifier, varDeclarationPrime, statmentPrime);
	}

	private IStatmentPrime statmentPrime() {
		// Statement` ::= Statement Statement` | λ

		IStatement statement = statement();
		if (statement == null) {
			return new StatementPrime2();
		}

		IStatmentPrime statementPrime = statmentPrime();
		if (statementPrime == null) {
			printSyntaxError("statmentPrime(): statmentPrime == null");
			return null;
		}

		return new StatementPrime1(statement, statementPrime);
	}

	private ITypeIdentifier typeIdentifier() {
		// TypeIdentifier ::= Type Identifier CommaTypeIdentifier | λ

		IType type = type();
		if (type == null) {
			return new TypeIdentifier2();
		}

		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("typeIdentifier(): identifier == null");
			return null;
		}

		ICommaTypeIdentifier commaTypeIdentifier = commaTypeIdentifier();
		if (commaTypeIdentifier == null) {
			printSyntaxError("typeIdentifier(): commaTypeIdentifier == null");
			return null;
		}

		return new TypeIdentifier1(type, identifier, commaTypeIdentifier);
	}

	private ICommaTypeIdentifier commaTypeIdentifier() {
		// CommaTypeIdentifier ::= “,” Type Identifier CommaTypeIdentifier | λ

		if (!lookahead.getValue().equals(",")) {
			return new CommaTypeIdentifier2();
		}
		nextToken();

		IType type = type();
		if (type == null) {
			printSyntaxError("commaTypeIdentifier(): type == null");
			return null;
		}

		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("commaTypeIdentifier(): identifier == null");
			return null;
		}

		ICommaTypeIdentifier commaTypeIdentifier = commaTypeIdentifier();
		if (commaTypeIdentifier == null) {
			printSyntaxError("commaTypeIdentifier(): commaTypeIdentifier == null");
			return null;
		}

		return new CommaTypeIdentifier1(type, identifier, commaTypeIdentifier);
	}

	private IMethodDeclarationPrime methodDeclarationPrime() {
		// MethodDeclaration` ::= MethodDeclaration MethodDeclaration` | λ

		IMethodDeclaration methodDeclaration = methodDeclaration();
		if (methodDeclaration == null) {
			return new MethodDeclarationPrime2();
		}

		IMethodDeclarationPrime methodDeclarationPrime = methodDeclarationPrime();
		if (methodDeclarationPrime == null) {
			printSyntaxError("methodDeclarationPrime(): methodDeclarationPrime == null");
			return null;
		}

		return new MethodDeclarationPrime1(methodDeclaration, methodDeclarationPrime);
	}
	
	private IMethodDeclaration methodDeclaration() {
		/*
		 * MethodDeclaration ::= (“public” | “private | “protected”) Type Identifier “(“
		 * TypeIdentifier “)” “{“ VarDeclaration` Statement` “return” Expression “;” “}”
		 */

		String accessModifier = lookahead.getValue();
		if (!accessModifier.equals("public") && !accessModifier.equals("private")
				&& !accessModifier.equals("protected")) {
			printSyntaxError(lookahead, "Access Modifier");
			return null;
		}
		nextToken();

		IType type = type();
		if (type == null) {
			printSyntaxError("methodDeclaration(): type == null");
			return null;
		}

		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("methodDeclaration(): identifier == null");
			return null;
		}

		if (!lookahead.getValue().equals("(")) {
			printSyntaxError(lookahead, "(");
			return null;
		}
		nextToken();

		ITypeIdentifier typeIdentifier = typeIdentifier();
		if (typeIdentifier == null) {
			printSyntaxError("methodDeclaration(): typeIdentifier == null");
			return null;
		}

		if (!lookahead.getValue().equals(")")) {
			printSyntaxError(lookahead, ")");
			return null;
		}
		nextToken();

		if (!lookahead.getValue().equals("{")) {
			printSyntaxError(lookahead, "{");
			return null;
		}
		nextToken();

		IVarDeclarationPrime varDeclarationPrime = varDeclarationPrime();
		if (varDeclarationPrime == null) {
			printSyntaxError("methodDeclaration(): varDeclarationPrime == null");
			return null;
		}

		IStatmentPrime statmentPrime = statmentPrime();
		if (statmentPrime == null) {
			printSyntaxError("methodDeclaration(): statmentPrime == null");
			return null;
		}

		if (!lookahead.getValue().equals("return")) {
			printSyntaxError(lookahead, "return");
			return null;
		}
		nextToken();

		IExpression expression = expression();
		if (expression == null) {
			printSyntaxError("methodDeclaration(): expression == null");
			return null;
		}

		if (!lookahead.getValue().equals(";")) {
			printSyntaxError(lookahead, ";");
			return null;
		}
		nextToken();

		if (!lookahead.getValue().equals("}")) {
			printSyntaxError(lookahead, "}");
			return null;
		}
		nextToken();

		if (accessModifier.equals("public"))
			return new MethodDeclaration1(type, identifier, typeIdentifier, varDeclarationPrime, statmentPrime,
					expression);
		if (accessModifier.equals("private"))
			return new MethodDeclaration2(type, identifier, typeIdentifier, varDeclarationPrime, statmentPrime,
					expression);
		return new MethodDeclaration3(type, identifier, typeIdentifier, varDeclarationPrime, statmentPrime, expression);

	}

	// Abdallah: The problem is here
	private IExpression expression() {
		// Expression ::= (<INTEGER_LITERAL> | <FLOAT_LITERAL>
		// | “true” | “false” | Identifier | “this” | “new” TypeOrIdentifier
		// | “!” Expression | “(“ Expression “)” ) Expression`

		if (lookahead.getType().equals("INTEGRAL_LITERAL")) {
			nextToken();
			IExpressionPrime expressionPrime = expressionPrime();
			if (expressionPrime == null) {
				printSyntaxError("expression(): expressionPrime == null");
				return null;
			}
			return new Expression1(expressionPrime);
		}

		else if (lookahead.getType().equals("FLOAT_LITERAL")) {
			nextToken();
			IExpressionPrime expressionPrime = expressionPrime();
			if (expressionPrime == null) {
				printSyntaxError("expression(): expressionPrime == null");
				return null;
			}
			return new Expression2(expressionPrime);
		}

		else if (lookahead.getValue().equals("true")) {
			nextToken();
			IExpressionPrime expressionPrime = expressionPrime();
			if (expressionPrime == null) {
				printSyntaxError("expression(): expressionPrime == null");
				return null;
			}
			return new Expression3(expressionPrime);
		}

		else if (lookahead.getValue().equals("false")) {
			nextToken();
			IExpressionPrime expressionPrime = expressionPrime();
			if (expressionPrime == null) {
				printSyntaxError("expression(): expressionPrime == null");
				return null;
			}
			return new Expression4(expressionPrime);
		}

		else if (lookahead.getValue().equals("this")) {
			nextToken();
			IExpressionPrime expressionPrime = expressionPrime();
			if (expressionPrime == null) {
				printSyntaxError("expression(): expressionPrime == null");
				return null;
			}
			return new Expression6(expressionPrime);
		}

		else if (lookahead.getValue().equals("new")) {
			nextToken();

			ITypeOrIdentifier typeOrIdentifier = typeOrIdentifier();
			if (typeOrIdentifier == null) {
				printSyntaxError("expression(): typeOrIdentifier == null");
				return null;
			}

			IExpressionPrime expressionPrime = expressionPrime();
			if (expressionPrime == null) {
				printSyntaxError("expression(): expressionPrime == null");
				return null;
			}

			return new Expression7(expressionPrime, typeOrIdentifier);
		}

		else if (lookahead.getValue().equals("!")) {
			nextToken();

			IExpression expression = expression();
			if (expression == null) {
				printSyntaxError("expression(): expression == null");
				return null;
			}

			IExpressionPrime expressionPrime = expressionPrime();
			if (expressionPrime == null) {
				printSyntaxError("expression(): expressionPrime == null");
				return null;
			}

			return new Expression8(expression, expressionPrime);
		}

		else if (lookahead.getValue().equals("(")) {
			nextToken();

			IExpression expression = expression();
			if (expression == null) {
				printSyntaxError("expression(): expression == null");
				return null;
			}

			if (lookahead.getValue().equals(")")) {
				printSyntaxError(lookahead, ")");
				return null;
			}
			nextToken();

			IExpressionPrime expressionPrime = expressionPrime();
			if (expressionPrime == null) {
				printSyntaxError("expression(): expressionPrime == null");
				return null;
			}

			return new Expression9(expression, expressionPrime);
		}

		// here is the problem, I don't know how to fix it :(
		// this same problem occurs again in many places in code
		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("expression(): identifier == null");
			return null;
		}

		IExpressionPrime expressionPrime = expressionPrime();
		if (expressionPrime == null) {
			printSyntaxError("expression(): expressionPrime == null");
			return null;
		}

		return new Expression5(expressionPrime, identifier);
	}

	private ITypeOrIdentifier typeOrIdentifier() {
		// TypeOrIdentifier ::= (“int” | “float” | “String” | “char” | “boolean”)
		// “[“Expression “]”
		// | Identifier “(“ ExpressionArgu “)”

		String dataType = lookahead.getValue();
		
		// Abdallah: replaced || with &&
		if (!dataType.equals("int") && !dataType.equals("boolean") && !dataType.equals("float")
				&& !dataType.equals("String") && !dataType.equals("char")) {

			IIdentifier identifier = identifier();
			if (identifier == null) {
				printSyntaxError("typeOrIdentifier(): identifier == null");
				return null;
			}

			if (!lookahead.getValue().equals("(")) {
				printSyntaxError(lookahead, "(");
				return null;
			}
			nextToken();

			IExpressionArgu expressionArgu = expressionArgu();
			if (expressionArgu == null) {
				printSyntaxError("typeOrIdentifier(): expressionArgu == null");
				return null;
			}

			if (!lookahead.getValue().equals(")")) {
				printSyntaxError(lookahead, ")");
				return null;
			}
			nextToken();

			return new TypeOrIdentifier6(identifier, expressionArgu);
		}
		nextToken();

		if (!lookahead.getValue().equals("[")) {
			printSyntaxError(lookahead, "[");
			return null;
		}
		nextToken();

		IExpression expression = expression();
		if (expression == null) {
			printSyntaxError("typeOrIdentifier(): expression == null");
			return null;
		}

		if (!lookahead.getValue().equals("]")) {
			printSyntaxError(lookahead, "]");
		}
		nextToken();

		if (dataType.equals("int"))
			return new TypeOrIdentifier1(expression);
		if (dataType.equals("float"))
			return new TypeOrIdentifier2(expression);
		if (dataType.equals("String"))
			return new TypeOrIdentifier3(expression);
		if (dataType.equals("char"))
			return new TypeOrIdentifier4(expression);
		if (dataType.equals("boolean"))
			return new TypeOrIdentifier5(expression);
		return null;
	}

	private IExpressionArgu expressionArgu() {
		// ExpressionArgu ::= Expression CommaExpressionArgu | λ

		IExpression expression = expression();
		if (expression == null) {
			return new ExpressionArgu2();
		}

		ICommaExpressionArgu commaExpressionArgu = commaExpressionArgu();
		if (commaExpressionArgu == null) {
			printSyntaxError("expressionArgu(): commaExpressionArgu == null");
			return null;
		}

		return new ExpressionArgu1(expression, commaExpressionArgu);
	}

	private ICommaExpressionArgu commaExpressionArgu() {
		// CommaExpressionArgu ::= “,” Expression CommaExpressionArgu | λ

		if (!lookahead.getValue().equals(",")) {
			return new CommaExpressionArgu2();
		}
		nextToken();

		IExpression expression = expression();
		if (expression == null) {
			printSyntaxError("commaExpressionArgu(): expression == null");
			return null;
		}

		ICommaExpressionArgu commaExpressionArgu = commaExpressionArgu();
		if (commaExpressionArgu == null) {
			printSyntaxError("commaExpressionArgu(): commaExpressionArgu == null");
			return null;
		}

		return new CommaExpressionArgu1(expression, commaExpressionArgu);
	}

	private IStatement statement() {
		// Statement ::= “{“ Statement` “}”
		// | “if” “(“ Expression “)” Statement Else
		// | “while” “(“ Expression “)” Statement
		// | “System.out.println” “(“ Expression “)” “;”
		// | Identifier EqualOrBracketExpression

		if (lookahead.getValue().equals("{")) {
			// “{“ Statement` “}”
			nextToken();

			IStatmentPrime statmentPrime = statmentPrime();
			if (statmentPrime == null) {
				printSyntaxError("statement(): statment == null");
				return null;
			}

			if (!lookahead.getValue().equals("}")) {
				printSyntaxError(lookahead, "}");
				return null;
			}
			nextToken();

			return new Statement1(statmentPrime);
		}
		else if (lookahead.getValue().equals("if")) {
			// “if” “(“ Expression “)” Statement Else
			nextToken();

			if (!lookahead.getValue().equals("(")) {
				printSyntaxError(lookahead, "(");
				return null;
			}
			nextToken();

			IExpression expression = expression();
			if (expression == null) {
				printSyntaxError("statement(): expression == null");
				return null;
			}

			if (!lookahead.getValue().equals(")")) {
				printSyntaxError(lookahead, ")");
				return null;
			}
			nextToken();

			IStatement statement = statement();
			if (statement == null) {
				printSyntaxError("statement(): statement == null");
				return null;
			}

			IElse else1 = else1();
			if (else1 == null) {
				printSyntaxError("statement(): else == null");
				return null;
			}

			return new Statement2(expression, statement, else1);
		}
		else if (lookahead.getValue().equals("while")) {
			// “while” “(“ Expression “)” Statement
			nextToken();

			if (!lookahead.getValue().equals("(")) {
				printSyntaxError(lookahead, "(");
				return null;
			}
			nextToken();

			IExpression expression = expression();
			if (expression == null) {
				printSyntaxError("statement(): expression == null");
				return null;
			}

			if (!lookahead.getValue().equals(")")) {
				printSyntaxError(lookahead, ")");
				return null;
			}
			nextToken();

			IStatement statement = statement();
			if (statement == null) {
				printSyntaxError("statement(): statement == null");
				return null;
			}

			return new Statement3(expression, statement);

		}
		else if (lookahead.type.equals(Token.SYSOUT_TOKEN_TYPE)) {
			// “System.out.println” “(“ Expression “)” “;”
			nextToken();

			if (!lookahead.getValue().equals("(")) {
				printSyntaxError(lookahead, "(");
				return null;
			}
			nextToken();

			IExpression expression = expression();
			if (expression == null) {
				printSyntaxError("statement(): expression == null");
				return null;
			}

			if (!lookahead.getValue().equals(")")) {
				printSyntaxError(lookahead, ")");
				return null;
			}
			nextToken();

			if (!lookahead.getValue().equals(";")) {
				printSyntaxError(lookahead, ";");
				return null;
			}
			nextToken();

			return new Statement4(expression);

		}
		else {
			// Identifier EqualOrBracketExpression
			IIdentifier identifier = identifier();
			if (!(identifier == null)) {
				IEqualOrBracketExpression equalOrBracketExpression = equalOrBracketExpression();
				if (equalOrBracketExpression == null) {
					printSyntaxError("statement(): equalOrBracketExpression == null");
					return null;
				}
				return new Statement5(identifier, equalOrBracketExpression);
			}
			printSyntaxError("statement(): identifier == null");
			return null;
		}		
	}

	private IEqualOrBracketExpression equalOrBracketExpression() {
		// EqualOrBracketExpression ::= “=” Expression “;”
		// | “[“ Expression “]” “=” Expression “;”

		if (lookahead.getValue().equals("=")) {
			nextToken();

			IExpression expression = expression();
			if (expression == null) {
				printSyntaxError("equalOrBracketExpression(): expression == null");
				return null;
			}

			if (!lookahead.getValue().equals(";")) {
				printSyntaxError(lookahead, ";");
				return null;
			}
			nextToken();

			return new EqualOrBracketExpression1(expression);
		}

		else if (lookahead.getValue().equals("[")) {
			nextToken();

			IExpression expression1 = expression();
			if (expression1 == null) {
				printSyntaxError("equalOrBracketExpression(): expression1 == null");
				return null;
			}

			if (!lookahead.getValue().equals("]")) {
				printSyntaxError(lookahead, "]");
				return null;
			}
			nextToken();

			if (!lookahead.getValue().equals("=")) {
				printSyntaxError(lookahead, "=");
				return null;
			}
			nextToken();

			IExpression expression2 = expression();
			if (expression2 == null) {
				printSyntaxError("equalOrBracketExpression(): expression2 == null");
				return null;
			}

			if (!lookahead.getValue().equals(";")) {
				printSyntaxError(lookahead, ";");
				return null;
			}
			nextToken();

			return new EqualOrBracketExpression2(expression1, expression2);
		}
		else {
			printSyntaxError("equalOrBracketExpression():");
			return null;
		}
	}

	private IElse else1() {
		// Else ::= “else” Statement | λ

		if (!lookahead.getValue().equals("else")) {
			return new Else2();
		}
		nextToken();

		IStatement statement = statement();
		if (statement == null) {
			printSyntaxError("else1(): statement == null");
			return null;
		}

		return new Else1(statement);
	}

	private IExpressionPrime expressionPrime() {
		// Expression` ::= ExpressionItems Expression’ | λ

		IExpressionItems expressionItems = expressionItems();
		if (expressionItems == null) {
			return new ExpressionPrime2();
		}

		IExpressionPrime expressionPrime = expressionPrime();
		if (expressionPrime == null) {
			printSyntaxError("expressionPrime(): expressionPrime == null");
			return null;
		}

		return new ExpressionPrime1(expressionItems, expressionPrime);
	}

	private IExpressionItems expressionItems() {
		// ExpressionItems::= ("&&" | "||" | "==" | "!=" | ">" | "<" | "<=" | ">=" | "+" | "-" | "*" | "/" ) Expression
		// | "[" Expression "]"
		// | “.” AfterDot

		if (lookahead.getValue().equals("[")) {
			// "[" Expression "]"
			nextToken();

			IExpression expression = expression();
			if (expression == null) {
				printSyntaxError("expressionItems(): expression == null");
				return null;
			}

			if (!lookahead.getValue().equals("]")) {
				printSyntaxError(lookahead, "]");
				return null;
			}
			nextToken();

			return new ExpressionItems13(expression);
		}
		else if (lookahead.getValue().equals(".")) {
			// “.” AfterDot
			nextToken();

			IAfterDot afterDot = afterDot();
			if (afterDot == null) {
				printSyntaxError("expressionItems(): afterDot == null");
				return null;
			}

			return new ExpressionItems14(afterDot);
		}
		else {
			String operator = lookahead.getValue();
			// Abdallah: replaced || with &&
			if (!operator.equals("&&") && !operator.equals("||") && !operator.equals("==") && !operator.equals("!=")
					&& !operator.equals(">") && !operator.equals("<") && !operator.equals("<=") && !operator.equals(">=")
					&& !operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
				printSyntaxError("expressionItems(): No Operator");
				return null;
			}
			nextToken();

			IExpression expression = expression();
			if (expression == null) {
				printSyntaxError("expressionItems(): expression == null");
				return null;
			}

			if (operator.equals("&&"))
				return new ExpressionItems1(expression);
			if (operator.equals("||"))
				return new ExpressionItems2(expression);
			if (operator.equals("=="))
				return new ExpressionItems3(expression);
			if (operator.equals("!="))
				return new ExpressionItems4(expression);
			if (operator.equals(">"))
				return new ExpressionItems5(expression);
			if (operator.equals("<"))
				return new ExpressionItems6(expression);
			if (operator.equals("<="))
				return new ExpressionItems7(expression);
			if (operator.equals(">="))
				return new ExpressionItems8(expression);
			if (operator.equals("+"))
				return new ExpressionItems9(expression);
			if (operator.equals("-"))
				return new ExpressionItems10(expression);
			if (operator.equals("*"))
				return new ExpressionItems11(expression);
			if (operator.equals("/"))
				return new ExpressionItems12(expression);
			return null;
		}
	}

	private IAfterDot afterDot() {
		// AfterDot ::= "length"
		// | Identifier "(" ExpressionArgu ")"

		if (lookahead.getValue().equals("length")) {
			nextToken();
			return new AfterDot1();
		}
		// Abdallah: Why the nextToken() below??
//		nextToken();

		IIdentifier identifier = identifier();
		if (identifier == null) {
			printSyntaxError("afterDot(): identifier == null");
			return null;
		}

		if (lookahead.getValue().equals("(")) {
			printSyntaxError(lookahead, "(");
			return null;
		}
		nextToken();

		IExpressionArgu expressionArgu = expressionArgu();
		if (expressionArgu == null) {
			printSyntaxError("afterDot(): expressionArgu == null");
			return null;
		}

		if (lookahead.getValue().equals(")")) {
			printSyntaxError(lookahead, ")");
			return null;
		}
		nextToken();

		return new AfterDot2(identifier, expressionArgu);
	}

	private IIdentifier identifier() {
		// Identifier ::= <IDENTIFIER>
		if (!lookahead.type.equals("ID")) {
			printSyntaxError(lookahead, "<IDENTIFIER>");
			return null;
		}
		nextToken();

		return new Identifier();
	}
}
