package mjc.core.parser;

import mjc.core.parser.interfaces.IIdentifier;

public class Identifier implements IIdentifier{
	//Identifier ::= <IDENTIFIER>
	
	public Identifier() {
	}
	
	@Override
	public String getValue() {
		return "<IDENTIFIER>";
	}

}
