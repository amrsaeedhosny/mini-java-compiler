package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;

public class Expression9 implements IExpression {

	// Expression ::= “(“ Expression “)” Expression
	IExpression expression;

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "(" + expression.getValue()+ ")" + expression.getValue();
	}
}
