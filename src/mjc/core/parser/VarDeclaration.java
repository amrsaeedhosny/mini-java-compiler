package mjc.core.parser;

import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IType;
import mjc.core.parser.interfaces.IVarDeclaration;

public class VarDeclaration implements IVarDeclaration{

	//VarDeclaration ::= Type Identifier “;”
	IIdentifier identifier;
	IType type;
	@Override
	public String getValue() {
		return type.getValue()+identifier.getValue()+";";
	}
}
