import items.Token;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import items.TokenRegex;

public class LexicalAnalyzer {
	public static ArrayList<Token> analyze(String code, ArrayList<TokenRegex> regexTable) {
		ArrayList<String> codeTokens = tokenizeCode(code);
		System.out.println(codeTokens);
		
		ArrayList<Token> matchedTokens = new ArrayList<>();
		

		/*
		 * 
		 * 
		 * WRITE YOUR CODE HERE TO MATCH TOKENS AGAINST REGEX
		 * 
		 * 
		 */

		return matchedTokens;
	}
	
	
	public static ArrayList<String> tokenizeCode(String code) {
		ArrayList<String> codeTokens = new ArrayList<>();
		String tokensDelimiter = 
				"\\(\\+|-|\\{|\\}|\\[|\\]|\\(|\\)|,|;|!|=|==|!=|&&|\\|\\||\\*|\\/|%|<|>|<=|>=|\\r\\n|\\r|\\n\\)"; // stopped at IF in regex_table.txt
		Pattern delimiterPattern = Pattern.compile(tokensDelimiter);
		
		Matcher delimiterMatcher = delimiterPattern.matcher(code);
		int codeIndex = 0;
		while (delimiterMatcher.find(codeIndex))
		{
			// found a delimiter
			String delimiter = delimiterMatcher.group();
			int delimiterStartPosition = delimiterMatcher.start();
			int delimiterEndPosition = delimiterMatcher.end();
				
			String codeToken = code.substring(codeIndex, delimiterStartPosition);
			codeTokens.add(codeToken);
			if (!delimiter.equals(" ")) {
				codeToken = code.substring(delimiterStartPosition, delimiterEndPosition);
				codeTokens.add(codeToken);
			}
			codeIndex = delimiterEndPosition;
		}
		
		if (codeIndex != code.length()) { // handles last token unfound
			String codeToken = code.substring(codeIndex, code.length());
			codeTokens.add(codeToken);
		}

		return codeTokens;
	}

}
