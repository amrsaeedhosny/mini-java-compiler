package mjc.core.parser;

import mjc.core.parser.interfaces.ICommaTypeIdentifier;
import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IType;
import mjc.core.parser.interfaces.ITypeIdentifier;

public class TypeIdentifier1 implements ITypeIdentifier{

	//TypeIdentifier ::= Type Identifier CommaTypeIdentifier
	IType type;
	IIdentifier identifier;
	ICommaTypeIdentifier commaTypeIdentifier;
	
	@Override
	public String getValue() {
		return type.getValue()+identifier.getValue()+commaTypeIdentifier.gtValue();
	}

	
	
	
}
