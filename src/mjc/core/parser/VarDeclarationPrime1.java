package mjc.core.parser;

import mjc.core.parser.interfaces.IVarDeclaration;
import mjc.core.parser.interfaces.IVarDeclarationPrime;

public class VarDeclarationPrime1 implements IVarDeclarationPrime{

	//VarDeclaration` ::= VarDeclaration VarDeclaration`
	IVarDeclaration varDeclaration;
	IVarDeclarationPrime varDeclarationPrime;
	
	public VarDeclarationPrime1(IVarDeclaration varDeclaration, IVarDeclarationPrime varDeclarationPrime) {
		super();
		this.varDeclaration = varDeclaration;
		this.varDeclarationPrime = varDeclarationPrime;
	}

	@Override
	public String getValue() {
		return varDeclaration.getValue()+varDeclarationPrime.getValue();
	}
}
