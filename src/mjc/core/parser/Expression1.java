package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionPrime;

public class Expression1 implements IExpression {
	// Expression ::= <INTEGER_LITERAL> Expression`
	IExpressionPrime expressionPrime;

	public Expression1(IExpressionPrime expressionPrime) {
		this.expressionPrime = expressionPrime;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "<INTEGER_LITERAL>" + (expressionPrime!=null? expressionPrime.getValue(): "expressionPrime==null");

	}

}
