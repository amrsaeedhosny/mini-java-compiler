package mjc.core.parser;

import mjc.core.parser.interfaces.IClassDeclarationPrime;
import mjc.core.parser.interfaces.IGoal;
import mjc.core.parser.interfaces.IMainClass;

public class Goal implements IGoal {
	// Goal ::= MainClass ClassDeclaration` <EOF>
	
	IMainClass mainClass;
	IClassDeclarationPrime classDeclarationPrime;
	
	
	@Override
	public String getValue() {
		
		return mainClass.getValue() + classDeclarationPrime.getValue() + "<EOF>";
	}

}
