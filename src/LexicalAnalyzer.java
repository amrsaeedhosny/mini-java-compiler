import items.Token;
import java.util.ArrayList;
import java.util.regex.Pattern;

import items.TokenRegex;

public class LexicalAnalyzer {
	
	public static ArrayList<Token> analyze(String code, ArrayList<TokenRegex> regexTable) {
		ArrayList<String> codeTokens = tokenizeCode(code);
		ArrayList<Token> matchedTokens = new ArrayList<>();
		
		/**
		 * Write the matching code here
		 */
		
		return matchedTokens;
	}
	
	
	public static ArrayList<String> tokenizeCode(String code) {
		ArrayList<String> codeTokens = new ArrayList<>();
		
		String delimiterRegex = "";
		Pattern delimiterPattern = Pattern.compile(delimiterRegex);
		
		// splitting to lines just to break the bigger problem to smaller problems no more.
		String lines[] = code.split("\\r?\\n"); /*
												\r = CR (Carriage Return) : Used as a new line character in Mac OS before X
												\n = LF (Line Feed) : Used as a new line character in Unix/Mac OS X
												\r\n = CR + LF : Used as a new line character in Windows
												*/
		
		
		
		

		return codeTokens;
	}

}
