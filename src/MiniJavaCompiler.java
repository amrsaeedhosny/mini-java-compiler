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
		for(int i = 0 ; i < matchedTokens.size() ; i++) {
			String tokenLabel = matchedTokens.get(i).getType();
			String tokenValue = matchedTokens.get(i).getValue();
			if (tokenLabel.equals("EOL")) {
				tokenValue = "ENDOFLINE";
			}
			System.out.println("< "+ tokenLabel +" > : "+ "-" + tokenValue + "-");
		}
		System.out.println("---------------------------------------------------------");
		
	}

}
