package mjc.file_utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import mjc.models.Token;

/**
 * Reads the code tokens directly from a file.
 */
public final class CodeTokensReader {

	private CodeTokensReader() {
		// private constructor prevents the class from being instantiated
	}

	public static ArrayList<Token> read(String path) {
		String line = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			StringBuilder sb = new StringBuilder();
			line = br.readLine();

			while (line != null) {
				sb.append(line.trim());
				sb.append(":");
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			line = sb.toString();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[] stringTokens = line.split(":");
		ArrayList<Token> tokens = new ArrayList<Token>();
		for (int i = 0; i < stringTokens.length - 1; i += 2) {
			String type = stringTokens[i]
					.substring(3, stringTokens[i].length()-2)
					.trim();

			String value = stringTokens[i + 1]
					.substring(2, stringTokens[i + 1].length()-1)
					.trim();
			Token token = new Token(type, value);
			tokens.add(token);
		}
		return tokens;
	}
	
	public static void printCodeTokens(ArrayList<Token> matchedTokens) {
	System.out.println("----------------------------- Code Tokens ----------------------------");
	for (Token token : matchedTokens) {
		String tokenLabel = token.getType();
		String tokenValue = token.getValue();
		if (tokenLabel.equals("EOL")) {
			tokenValue = "ENDOFLINE";
		}
		if (tokenLabel.equals(Token.UNKNOWN_TOKEN_TYPE)) {
			System.out.println("ERROR " + "< " + tokenLabel + " > : " + " '" + tokenValue
					+ "' This token did not match any RE @ index " + token.getStartIndex());
		} else {
			System.out.println("< " + tokenLabel + " > : " + "-" + tokenValue + "-");
		}

	}
	System.out.println("---------------------------------------------------------------------");
}


}
