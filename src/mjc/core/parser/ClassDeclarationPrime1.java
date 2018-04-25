package mjc.core.parser;

import mjc.core.parser.interfaces.IClassDeclaration;
import mjc.core.parser.interfaces.IClassDeclarationPrime;

public class ClassDeclarationPrime1 implements IClassDeclarationPrime {
	// ClassDeclaration` ::= ClassDeclaration ClassDeclaration`
	IClassDeclaration classDeclaration;
	IClassDeclarationPrime classDeclarationPrime;

	@Override
	public String getValue() {
		return classDeclaration.getValue() + classDeclarationPrime.getValue();
	}

}
