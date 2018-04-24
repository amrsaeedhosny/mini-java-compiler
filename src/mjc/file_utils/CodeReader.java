package mjc.file_utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Utility class to read mini-java code from file.
 */
public final class CodeReader {
	
	
	private CodeReader() {
		// private constructor prevents the class from being instantiated		
	}
	
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
