package mjc.core.parser;

import mjc.core.parser.interfaces.IClassDeclaration;
import mjc.core.parser.interfaces.IClassDeclarationPrime;

public class ClassDeclarationPrime1 implements IClassDeclarationPrime {
	// ClassDeclaration` ::= ClassDeclaration ClassDeclaration`
	IClassDeclaration classDeclaration;
	IClassDeclarationPrime classDeclarationPrime;

	public ClassDeclarationPrime1(IClassDeclaration classDeclaration,
			IClassDeclarationPrime classDeclarationPrime) {
		this.classDeclaration = classDeclaration;
		this.classDeclarationPrime = classDeclarationPrime;
	}

	@Override
	public String getValue() {
		return (classDeclaration!=null? classDeclaration.getValue(): "classDeclaration==null") + (classDeclarationPrime!=null? classDeclarationPrime.getValue():"classDeclarationPrime==null");
	}

}
