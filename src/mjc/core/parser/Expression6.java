package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionPrime;

public class Expression6 implements IExpression {
	// Expression ::= "this" Expression`
	IExpressionPrime expressionPrime;

	public Expression6(IExpressionPrime expressionPrime) {
		this.expressionPrime = expressionPrime;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "this" + expressionPrime.getValue();
	}
}
