package mjc.core.parser;

import mjc.core.parser.interfaces.ICommaTypeIdentifier;
import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IType;

public class CommaTypeIdentifier1 implements ICommaTypeIdentifier{

	//CommaTypeIdentifier ::= “,” Type Identifier CommaTypeIdentifier
	IType type;
	IIdentifier identifier;
	ICommaTypeIdentifier commaTypeIdentifier;
	
	@Override
	public String gtValue() {
		return ","+type.getValue()+identifier.getValue()+commaTypeIdentifier.gtValue();
	}

}
