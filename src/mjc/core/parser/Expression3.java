package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionPrime;

public class Expression3 implements IExpression {

	// Expression ::= "true" Expression`
	IExpressionPrime expressionPrime;

	public Expression3(IExpressionPrime expressionPrime) {
		this.expressionPrime = expressionPrime;
	}

	@Override
	public String getValue() {
		return " true " 
			+ (expressionPrime!=null? expressionPrime.getValue(): "expressionPrime==null");
	}

}
