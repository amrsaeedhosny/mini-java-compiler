package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;

public class Expression3 implements IExpression {

	// Expression ::= "true" Expression
	IExpression expression;

	public Expression3(IExpression expression) {
		this.expression = expression;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "true" + expression.getValue();
	}

}
