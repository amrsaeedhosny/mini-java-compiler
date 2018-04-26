package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IIdentifier;

public class Expression5 implements IExpression {
	// Expression ::= Identifier Expression
	IExpression expression;
	IIdentifier identifier;

	public Expression5(IExpression expression, IIdentifier identifier) {
		this.expression = expression;
		this.identifier = identifier;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return identifier.getValue() + expression.getValue();
	}
}
