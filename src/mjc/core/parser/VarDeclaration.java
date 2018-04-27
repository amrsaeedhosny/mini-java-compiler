package mjc.core.parser;

import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IType;
import mjc.core.parser.interfaces.IVarDeclaration;

public class VarDeclaration implements IVarDeclaration{

	//VarDeclaration ::= Type Identifier “;”
	IIdentifier identifier;
	IType type;
	
	public VarDeclaration(IIdentifier identifier, IType type) {
		super();
		this.identifier = identifier;
		this.type = type;
	}

	@Override
	public String getValue() {
		return (type!=null? type.getValue(): "type==null")+(identifier!=null? identifier.getValue(): "identifier==null")+";";
	}
}
