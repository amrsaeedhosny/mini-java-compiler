package mjc.core.parser;

import mjc.core.parser.interfaces.IVarDeclaration;
import mjc.core.parser.interfaces.IVarDeclarationPrime;

public class VarDeclarationPrime1 implements IVarDeclarationPrime{

	//VarDeclaration` ::= VarDeclaration VarDeclaration`
	IVarDeclaration varDeclaration;
	IVarDeclarationPrime varDeclarationPrime;
	
	@Override
	public String getValue() {
		return varDeclaration.getValue()+varDeclarationPrime.getValue();
	}
}
