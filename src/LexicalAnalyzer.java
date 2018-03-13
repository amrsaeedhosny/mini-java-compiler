import items.Token;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import items.TokenRegex;

public class LexicalAnalyzer {
	
	
	public static ArrayList<Token> analyze(String code, ArrayList<TokenRegex> regexTable) {
		ArrayList<Token> matchedTokens = new ArrayList<>();
		
		for (TokenRegex tokenRegex: regexTable) {
			Pattern pattern = Pattern.compile(tokenRegex.getRegex());
			Matcher matcher = pattern.matcher(code);
			
			String tokenType = tokenRegex.getLabel();
			while (matcher.find())
			{
				// found a token
				String foundToken = matcher.group();
				int startIndex = matcher.start();
				int endIndex = matcher.end();
				
				Token token = new Token(tokenType, foundToken, startIndex, endIndex);
				matchedTokens.add(token);
								
			}

			
		}
		
		// sort the found token by their startIndex
		sortMatchedTokens(matchedTokens);
		
		
		/* Check for unknown (Error) tokens */
		// check for unknown token in the middle of the code
		for (int i = 0; i<matchedTokens.size()-2; i++) { 
			Token currentToken = matchedTokens.get(i);
			Token nextToken = matchedTokens.get(i+1);
	
			int currentTokenEndIndex = currentToken.getEndIndex();
			int nextTokenStartIndex = nextToken.getStartIndex();
			
			if (currentTokenEndIndex != nextTokenStartIndex) {
				// caught an error
				Token unknownToken = new Token(Token.UNKNOWN_TOKEN_TYPE, 
						code.substring(currentTokenEndIndex, nextTokenStartIndex),
						currentTokenEndIndex, nextTokenStartIndex);
				matchedTokens.add(unknownToken);
			}

		}
		
		// check for unknown token at the very beginning & at the very end
		// Again, sort the found token by their startIndex
		sortMatchedTokens(matchedTokens);
		Token unknownToken1 = null;
		Token unknownToken2 = null;
		if (matchedTokens.get(0).getStartIndex() != 0) { 
			// caught an error
			unknownToken1 = new Token(Token.UNKNOWN_TOKEN_TYPE, 
					code.substring(0, matchedTokens.get(0).getStartIndex()),
					0, matchedTokens.get(0).getStartIndex());
		}
		if ( (matchedTokens.get(matchedTokens.size()-1).getEndIndex()) != (code.length()) ) {
			// caught an error
			unknownToken2 = new Token(Token.UNKNOWN_TOKEN_TYPE, 
					code.substring(matchedTokens.get(matchedTokens.size()-1).getEndIndex(), code.length())
							,matchedTokens.get(matchedTokens.size()-1).getEndIndex() ,code.length());
		}
		if (unknownToken1 != null) matchedTokens.add(unknownToken1);
		if (unknownToken2 != null) matchedTokens.add(unknownToken2);
		
		// Again, sort the found token by their startIndex
		sortMatchedTokens(matchedTokens);
		
		System.out.println(matchedTokens);
		return matchedTokens;
	}
	
	private static void sortMatchedTokens(ArrayList<Token> list) {
		Collections.sort(list, new Comparator<Token>() {
			@Override
		    public int compare(Token t1, Token t2) {
				if (t1.getStartIndex() > t2.getStartIndex()) {
					return 1;
				}
				else if (t1.getStartIndex() < t2.getStartIndex()) {
					return -1;
				}
		        return 0;
		    }
		});
	}

}
