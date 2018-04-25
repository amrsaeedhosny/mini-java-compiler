package mjc.file_utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import mjc.models.Token;

/**
 * Reads the code tokens directly from a file.
 */
public final class CodeTokensReader {

	private CodeTokensReader() {
		// private constructor prevents the class from being instantiated
	}

	private static Scanner input;

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
			Token token = new Token(stringTokens[i].replace("<", "").replace(">", "").trim(),
					stringTokens[i + 1].replaceAll("-", "").trim());
			tokens.add(token);
		}
		return tokens;
	}

}
