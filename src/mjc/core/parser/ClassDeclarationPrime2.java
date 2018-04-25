package mjc.core.parser;

import mjc.core.parser.interfaces.IClassDeclarationPrime;

public class ClassDeclarationPrime2 implements IClassDeclarationPrime {
	// ClassDeclaration` ::= lambda
	
	@Override
	public String getValue() {
		return Lambda.getValue();
	}

}
