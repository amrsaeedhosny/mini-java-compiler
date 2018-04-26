package mjc.core;

import java.util.ArrayList;

import mjc.core.parser.RecursiveDescentParser;
import mjc.core.parser.interfaces.IGoal;
import mjc.models.Token;

public final class SyntaxAnalyzer {
	
	private SyntaxAnalyzer() {
		// private constructor prevents the class from being instantiated
	}
	
	public static IGoal analyze(ArrayList<Token> codeTokens) {
		RecursiveDescentParser parser = new RecursiveDescentParser(codeTokens);
		return parser.parse();
	}

}
