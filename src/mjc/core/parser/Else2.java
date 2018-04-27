package mjc.core.parser;

import mjc.core.parser.interfaces.IElse;

public class Else2 implements IElse{

	//Else ::= lambda
	@Override
	public String getValue() {
		return Lambda.getValue();
	}

}
