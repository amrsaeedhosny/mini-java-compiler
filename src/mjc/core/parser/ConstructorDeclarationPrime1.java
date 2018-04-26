package mjc.core.parser;

import mjc.core.parser.interfaces.IConstructorDeclaration;
import mjc.core.parser.interfaces.IConstructorDeclarationPrime;

public class ConstructorDeclarationPrime1 implements IConstructorDeclarationPrime{

	//ConstructorDeclaration` ::= ConstructorDeclaration ConstructorDeclaration`
	IConstructorDeclarationPrime constructorDeclarationPrime;
	IConstructorDeclaration constructorDeclaration;
	
	@Override
	public String getValue() {
		return constructorDeclaration.getValue() + constructorDeclarationPrime.getValue();
	}

}
