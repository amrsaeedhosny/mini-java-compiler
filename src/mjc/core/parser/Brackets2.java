package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;

public class Brackets2 implements IBrackets{
	//Brackets ::=lambda
	
	@Override
	public String getValue() {
		return Lambda.getValue();
	}

}
