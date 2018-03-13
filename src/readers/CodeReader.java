package readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CodeReader {
	private static Scanner input;

	public static String readCode(String path) {
		String miniJavaCode = null;
		try {
			input = new Scanner(new File(path));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		miniJavaCode = input.useDelimiter("\\Z").next();

		return miniJavaCode;
	}
}
