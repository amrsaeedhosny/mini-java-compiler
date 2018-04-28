package mjc.core;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mjc.models.Token;
import mjc.models.TokenRegex;

public final class LexicalAnalyzer {
	
	private LexicalAnalyzer() {
		// private constructor prevents the class from being instantiated
	}
	
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
		sortMatchedTokens(matchedTokens); // the sort method puts the longest match first
		
		//remove duplicate
		for(int i = 0 ; i < matchedTokens.size()-1 ; i++)
		{
			if(matchedTokens.get(i).getStartIndex()==matchedTokens.get(i+1).getStartIndex()) {
				matchedTokens.remove(i+1);
				i--;
			}
		}
		//remove sub-tokens	
		for(int i = 0 ; i < matchedTokens.size() ; i++){
			for(int j = i+1 ; j < matchedTokens.size() ; j++){
				if(matchedTokens.get(j).getStartIndex()>matchedTokens.get(i).getStartIndex()&&
					matchedTokens.get(j).getStartIndex()<matchedTokens.get(i).getEndIndex()	
						){
					matchedTokens.remove(j);
					j--;
				}
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
			if (currentTokenEndIndex != nextTokenStartIndex) {
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
		
		sortMatchedTokens(matchedTokens);
		
		for(int i = 0 ; i < matchedTokens.size() ; i++) {
			
			boolean space=false;
			for(int j = 0 ; j < matchedTokens.get(i).getValue().length() ; j++)
			{
				if(matchedTokens.get(i).getValue().charAt(j)!=32 &&  matchedTokens.get(i).getValue().charAt(j) !=9)
				{
					space=true;
					break;
				}
			}
			if(!space){
				matchedTokens.remove(i);
				i--;
			}
		}
		
		
		/* Save the matched tokens to file */
		saveTokensToFile(matchedTokens, "lexical-analysis-output.txt");

		return matchedTokens;
	}
	
	/**
	 * Sorts tokens in the list by their start position.
	 * If the start position is the same it sorts them by their end position
	 * such that the longest match will be put first.
	 * 
	 * @param list A list of tokens
	 */
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
				else if (t1.getStartIndex() == t2.getStartIndex()) {
					if (t1.getEndIndex() > t2.getEndIndex()) {
						return -1;
					}
					else if (t1.getEndIndex() < t2.getEndIndex()) {
						return 1;
					}
				}
				
				return 0;
		    }
		});
	}
	
	private static void saveTokensToFile(ArrayList<Token> tokensList, String outputFileName) {
		try (PrintWriter writer = new PrintWriter(outputFileName, "UTF-8")) {
			for (Token token: tokensList) {
				String tokenLabel = token.getType();
				String tokenValue = token.getValue();
				if (tokenLabel.equals("EOL")) {
					tokenValue = "ENDOFLINE";
				}
				if (tokenLabel.equals(Token.UNKNOWN_TOKEN_TYPE)) {
					writer.println("ERROR "+ "< "+ tokenLabel +" > : " +" '" + tokenValue + "' This token did not match any RE @ index " + token.getStartIndex());
				}
				else {
					writer.println("< "+ tokenLabel +" > : "+ "-" + tokenValue + "-");
				}
				
			}
			System.out.println("--> Lexical Analyzer: Matched tokens saved to \"" + outputFileName + "\"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
