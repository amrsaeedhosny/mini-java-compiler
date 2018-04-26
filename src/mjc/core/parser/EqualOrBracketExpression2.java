package mjc.core.parser;

import mjc.core.parser.interfaces.IEqualOrBracketExpression;
import mjc.core.parser.interfaces.IExpression;

public class EqualOrBracketExpression2 implements IEqualOrBracketExpression{

	//EqualOrBracketExpression ::= “[“ Expression “]” “=” Expression “;”
	
	IExpression expression;
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "["+expression.getValue()+"]"+"="+expression.getValue()+";";
	}

}
