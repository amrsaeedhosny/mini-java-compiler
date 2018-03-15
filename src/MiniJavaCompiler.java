import items.TokenRegex;
import items.Token;
import java.util.ArrayList;
import readers.CodeReader;
import readers.RegexTableReader;

public class MiniJavaCompiler {

	public static ArrayList<TokenRegex> regexTable;
	public static String miniJavaCode;
	public static ArrayList<Token> matchedTokens;

	public static void main(String[] args) {
		regexTable = RegexTableReader.readRegexTable("regex_table.txt");
		miniJavaCode = CodeReader.readCode("mini_java_code.txt");
		matchedTokens = LexicalAnalyzer.analyze(miniJavaCode, regexTable);
		
		// testing
		System.out.println("---------------------------------------------------------");
		for (Token token: matchedTokens) {
			String tokenLabel = token.getType();
			String tokenValue = token.getValue();
			if (tokenLabel.equals("EOL")) {
				tokenValue = "ENDOFLINE";
			}
			if (tokenLabel.equals(Token.UNKNOWN_TOKEN_TYPE)) {
				System.out.println("ERROR "+ "<"+ tokenLabel +"> : " +" '" + tokenValue + "' This token did not match any RE @ index " + token.getStartIndex());
			}
			else {
				System.out.println("<"+ tokenLabel +"> : "+ "-" + tokenValue + "-");
			}
			
		}
		System.out.println("---------------------------------------------------------");
		
	}

}
