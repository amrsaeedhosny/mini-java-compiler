package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionPrime;

public class Expression2 implements IExpression {
	// Expression ::= <FLOAT_LITERAL> Expression`

	IExpressionPrime expressionPrime;

	public Expression2(IExpressionPrime expressionPrime) {
		this.expressionPrime = expressionPrime;
	}

	@Override
	public String getValue() {
		return " <FLOAT_LITERAL> " + (expressionPrime!=null? expressionPrime.getValue(): "expressionPrime==null");
	}

}
