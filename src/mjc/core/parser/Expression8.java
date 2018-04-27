package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionPrime;

public class Expression8 implements IExpression {
	// Expression ::= “!” Expression Expression`
	IExpression expression;
	IExpressionPrime expressionPrime;

	public Expression8(IExpression expression, IExpressionPrime expressionPrime) {
		this.expression = expression;
		this.expressionPrime = expressionPrime;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "!" + (expression!=null? expression.getValue(): "expression==null") + (expressionPrime!=null? expressionPrime.getValue(): "expressionPrime==null");
	}
}
