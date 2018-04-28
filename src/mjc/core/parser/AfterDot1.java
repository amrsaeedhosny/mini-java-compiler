package mjc.core.parser;

import mjc.core.parser.interfaces.IAfterDot;

public class AfterDot1 implements IAfterDot{
	//AfterDot ::= "length"
	
	@Override
	public String gtValue() {
		return "length";
	}

}
