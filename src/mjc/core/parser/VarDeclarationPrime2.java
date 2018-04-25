package mjc.core.parser;

import mjc.core.parser.interfaces.IVarDeclarationPrime;

public class VarDeclarationPrime2 implements IVarDeclarationPrime {

	//VarDeclaration` ::= lambda
	
	@Override
	public String getValue()
	{
		return Lambda.getValue();
	}
}
