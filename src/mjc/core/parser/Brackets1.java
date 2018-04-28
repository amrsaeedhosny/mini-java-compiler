package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;

public class Brackets1 implements IBrackets{
	//Brackets ::= “[“ “]”
	
	@Override
	public String getValue() {
		return "["+"]";
	}

}
