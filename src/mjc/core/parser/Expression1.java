package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;

public class Expression1 implements IExpression{

	//Expression ::= <INTEGER_LITERAL> Expression
	IExpression expression;
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "<INTEGER_LITERAL>"+expression.getValue();
		
	}

}
