package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionPrime;
import mjc.core.parser.interfaces.IIdentifier;

public class Expression5 implements IExpression {
	// Expression ::= Identifier Expression`
	IExpressionPrime expressionPrime;
	IIdentifier identifier;

	public Expression5(IExpressionPrime expressionPrime, IIdentifier identifier) {
		this.expressionPrime = expressionPrime;
		this.identifier = identifier;
	}

	@Override
	public String getValue() {
		return " " + (identifier!=null? identifier.getValue(): "identifier==null") + " "
			+ (expressionPrime!=null? expressionPrime.getValue(): "expressionPrime==null");
	}
}
