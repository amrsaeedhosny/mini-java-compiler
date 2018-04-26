package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;

public class Expression2 implements IExpression{
	//Expression ::= <FLOAT_LITERAL> Expression
	
	IExpression expression;
	
	public Expression2(IExpression expression) {
		this.expression = expression;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "<FLOAT_LITERAL>"+expression.getValue();
	}

}
