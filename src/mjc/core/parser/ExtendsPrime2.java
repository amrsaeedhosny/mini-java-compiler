package mjc.core.parser;

import mjc.core.parser.interfaces.IExtendsPrime;

public class ExtendsPrime2 implements IExtendsPrime {
	// Extends` ::= lambda

	@Override
	public String getValue() {
		return Lambda.getValue();
	}

}
