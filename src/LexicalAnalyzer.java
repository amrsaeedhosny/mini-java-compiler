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
				boolean check =false;
				for(int i = 0 ; i < matchedTokens.size() ; i++){
					if(startIndex>=matchedTokens.get(i).getStartIndex()&&endIndex<=matchedTokens.get(i).getEndIndex())
					{
						check=true;
						break;
					}
				}
				if(!check) matchedTokens.add(token);
								
			}

			
		}
		
		// sort the found token by their startIndex
		sortMatchedTokens(matchedTokens);
		
		//remove duplicate
		for(int i = 0 ; i < matchedTokens.size()-1 ; i++)
		{
			if(matchedTokens.get(i).getStartIndex()==matchedTokens.get(i+1).getStartIndex())
			{
				matchedTokens.remove(i+1);
				i--;
			}
		}
		
		
		
		
		/* Check for unknown (Error) tokens */
		// check for unknown token in the middle of the code
		ArrayList<Token> temp = new ArrayList<>();
		for (int i = 0; i<matchedTokens.size()-1; i++) {
			Token currentToken = matchedTokens.get(i);
			Token nextToken = matchedTokens.get(i+1);
	
			int currentTokenEndIndex = currentToken.getEndIndex();
			int nextTokenStartIndex = nextToken.getStartIndex();
			if (currentTokenEndIndex != nextTokenStartIndex && currentTokenEndIndex!=nextTokenStartIndex-1) {
				// caught an error
				Token unknownToken = new Token(Token.UNKNOWN_TOKEN_TYPE, 
						code.substring(currentTokenEndIndex, nextTokenStartIndex),
						currentTokenEndIndex, nextTokenStartIndex);
				temp.add(unknownToken);
			}

		}
		for(int i = 0 ; i < temp.size() ; i++){
			matchedTokens.add(temp.get(i));
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
		if ( (matchedTokens.get(matchedTokens.size()-1).getEndIndex())-1 != (code.length()-1) ) {
			// caught an error
			unknownToken2 = new Token(Token.UNKNOWN_TOKEN_TYPE, 
					code.substring(matchedTokens.get(matchedTokens.size()-1).getEndIndex()-1, code.length()-1)
							,matchedTokens.get(matchedTokens.size()-1).getEndIndex()-1 ,code.length());
		}
		if (unknownToken1 != null) matchedTokens.add(unknownToken1);
		if (unknownToken2 != null) matchedTokens.add(unknownToken2);
		
		// Again, sort the found token by their startIndex
		sortMatchedTokens(matchedTokens);
		
		//System.out.println(matchedTokens);
		for(int i = 0 ; i < matchedTokens.size() ; i++)
		{
			System.out.println("<"+matchedTokens.get(i).getType()+"> : "+" "+matchedTokens.get(i).getValue());
		}
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
