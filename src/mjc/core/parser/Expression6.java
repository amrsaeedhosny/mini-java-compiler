package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;

public class Expression6 implements IExpression {

	// Expression ::= "this" Expression
	IExpression expression;

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "this" + expression.getValue();
	}
}
