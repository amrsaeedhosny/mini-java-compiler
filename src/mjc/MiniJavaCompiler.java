package mjc;
import mjc.core.LexicalAnalyzer;
import mjc.core.SyntaxAnalyzer;
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

	public static void main(String[] args) {
		regexTable = RegexTableReader.readRegexTable("regex_table.txt");
		miniJavaCode = CodeReader.readCode("mini_java_code.txt");
		
		/* Lexical Analysis */
		matchedTokens = LexicalAnalyzer.analyze(miniJavaCode, regexTable);
		// testing
		printCodeTokens();
		
		/* Syntax Analysis */
		int inputMethod = 1; // 1 -> memory , 2 -> file
		if (inputMethod == 1) {
			// take input from memory (output of lexical analysis)
			SyntaxAnalyzer.analyze(matchedTokens);
		}
		else if (inputMethod == 2) {
			// take input from file
			ArrayList<Token> codeTokens = 
					CodeTokensReader.read("code_tokens.txt");
			SyntaxAnalyzer.analyze(codeTokens);			
		}
		
		
	}

	private static void printCodeTokens() {
		System.out.println("---------------------------------------------------------");
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
		System.out.println("---------------------------------------------------------");
	}

}
