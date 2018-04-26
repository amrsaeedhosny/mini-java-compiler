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
		// TODO Auto-generated method stub
		return identifier.getValue() + expressionPrime.getValue();
	}
}
