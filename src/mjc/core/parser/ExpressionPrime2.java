package mjc.core.parser;

import mjc.core.parser.interfaces.IExpressionPrime;

public class ExpressionPrime2 implements IExpressionPrime{
	////Expression` ::= lambda
	@Override
	public String getValue() {
		return Lambda.getValue();
	}

}
