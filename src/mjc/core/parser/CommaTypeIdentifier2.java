package mjc.core.parser;

import mjc.core.parser.interfaces.ICommaTypeIdentifier;

public class CommaTypeIdentifier2 implements ICommaTypeIdentifier{

	//CommaTypeIdentifier ::=lambda
	
	@Override
	public String gtValue() {
		return Lambda.getValue();
	}

}
