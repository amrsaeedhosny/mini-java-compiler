package mjc.core.parser;

import mjc.core.parser.interfaces.IClassDeclarationPrime;
import mjc.core.parser.interfaces.IGoal;
import mjc.core.parser.interfaces.IMainClass;
import mjc.models.Token;

public class Goal implements IGoal {
	// Goal ::= MainClass ClassDeclaration` <EOF>
	
	IMainClass mainClass;
	IClassDeclarationPrime classDeclarationPrime;
	
	public Goal(IMainClass mainClass, IClassDeclarationPrime classDeclarationPrime) {
		this.mainClass = mainClass;
		this.classDeclarationPrime = classDeclarationPrime;
	}

	@Override
	public String getValue() {
		return (mainClass!=null? mainClass.getValue(): "mainClass==null") 
				+ (classDeclarationPrime!=null? classDeclarationPrime.getValue(): "classDeclarationPrime==null") 
				+ "\n" + Token.EOF_TOKEN_TYPE;
	}

}
