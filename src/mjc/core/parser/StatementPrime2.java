package mjc.core.parser;

import mjc.core.parser.interfaces.IStatmentPrime;

public class StatementPrime2 implements IStatmentPrime{
	//Statement` ::=lambda
	@Override
	public String getValue() {
		return Lambda.getValue();
	}

}
