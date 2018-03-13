package readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import items.TokenRegex;

public class RegexTableReader {
	private static Scanner input;

	public static ArrayList<TokenRegex> readRegexTable(String path) {
		ArrayList<TokenRegex> regexTable = new ArrayList<>();
		try {
			input = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (input.hasNext()) {
			regexTable.add(new TokenRegex(input.next(), input.next()));
		}

		return regexTable;
	}
}
