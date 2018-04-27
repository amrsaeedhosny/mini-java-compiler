package mjc;
import mjc.core.LexicalAnalyzer;
import mjc.core.SyntaxAnalyzer;
import mjc.core.parser.interfaces.IGoal;
import mjc.file_utils.CodeReader;
import mjc.file_utils.CodeTokensReader;
import mjc.file_utils.RegexTableReader;
import mjc.models.Token;
import mjc.models.TokenRegex;

import java.util.ArrayList;

public class MiniJavaCompiler {

	public static ArrayList<TokenRegex> regexTable;
	public static String miniJavaCode;
	public static ArrayList<Token> matchedTokens;
	public static IGoal goalParseTree;

	public static void main(String[] args) {
		regexTable = RegexTableReader.readRegexTable("regex_table.txt");
		miniJavaCode = CodeReader.readCode("mini_java_code.txt");
		
		/* Lexical Analysis */
		matchedTokens = LexicalAnalyzer.analyze(miniJavaCode, regexTable);
		printCodeTokens(); // testing
		
		/* Syntax Analysis */
		int inputMethod = 1; // 1 -> memory , 2 -> file
		if (inputMethod == 1) {
			// take input from memory (output of lexical analysis)
			goalParseTree = SyntaxAnalyzer.analyze(matchedTokens);
		}
		else if (inputMethod == 2) {
			// take input tokens from file
			ArrayList<Token> codeTokens = 
					CodeTokensReader.read("code_tokens.txt");
			goalParseTree = SyntaxAnalyzer.analyze(codeTokens);			
		}
		printParseTree(); // testing
		
	}

	private static void printParseTree() {
		System.out.println("----------------------------- Parse Tree ----------------------------");
		System.out.println(
				(goalParseTree != null)? goalParseTree.getValue(): "goalParseTree == null"
					);
		System.out.println("---------------------------------------------------------------------");
	}

	private static void printCodeTokens() {
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

}
