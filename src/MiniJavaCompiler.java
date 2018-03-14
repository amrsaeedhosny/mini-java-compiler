import items.TokenRegex;
import items.Token;
import java.util.ArrayList;
import readers.CodeReader;
import readers.RegexTableReader;

public class MiniJavaCompiler {

	public static ArrayList<TokenRegex> regexTable;
	public static String miniJavaCode;
	public static ArrayList<Token> codeTokens;

	public static void main(String[] args) {
		regexTable = RegexTableReader.readRegexTable("regex_table.txt");
		miniJavaCode = CodeReader.readCode("mini_java_code.txt");
		codeTokens = LexicalAnalyzer.analyze(miniJavaCode, regexTable);
	}

}
