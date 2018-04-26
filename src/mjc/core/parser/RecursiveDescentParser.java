package mjc.core.parser;

import java.util.ArrayList;

import mjc.core.parser.interfaces.*;
import mjc.models.Token;

public class RecursiveDescentParser {

	private ArrayList<Token> codeTokens;
	private Token lookahead;
	private IGoal goalRootNode;

	public RecursiveDescentParser(ArrayList<Token> codeTokens) {
		this.codeTokens = codeTokens;
		lookahead = this.codeTokens.get(0); // references first token in list
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
		System.out.println("Unexpected token found at: " + errorLocation.getValue() + 
				", expected: " + expected);
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
		return new ClassDeclarationPrime1(classDeclaration, classDeclarationPrime);
	}

	private IClassDeclaration classDeclaration() {
		/*
		 	“class” Identifier Extends` “{“ VarDeclaration` ConstructorDeclaration`
			MethodDeclaration` “}”
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
		
		return new ClassDeclaration(identifier, extendsPrime, varDeclarationPrime,
				constructorDeclarationPrime, methodDeclarationPrime);
	}

	private IMethodDeclarationPrime methodDeclarationPrime() {
		// TODO Auto-generated method stub
		return null;
	}

	private IConstructorDeclarationPrime constructorDeclarationPrime() {
		// TODO Auto-generated method stub
		return null;
	}

	private IVarDeclarationPrime varDeclarationPrime() {
		// TODO Auto-generated method stub
		return null;
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
		 MainClass ::= “class” Identifier “{“ “public” “static” “void” “main” “(“ “String” “[“ “]”
		Identifier “)” “{“ Statement “}” “}”
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
			printSyntaxError(lookahead , "(");
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

	private IStatement statement() {
		// TODO Auto-generated method stub
		return null;
	}

	private IIdentifier identifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
