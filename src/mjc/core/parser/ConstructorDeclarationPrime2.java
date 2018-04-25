package mjc.core.parser;

import mjc.core.parser.interfaces.IConstructorDeclarationPrime;

public class ConstructorDeclarationPrime2 implements IConstructorDeclarationPrime{

	//ConstructorDeclaration` ::= lambda
	
	@Override
	public String getValue() {
		return Lambda.getValue();
	}

}
