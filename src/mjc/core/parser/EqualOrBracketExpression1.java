package mjc.core.parser;

import mjc.core.parser.interfaces.IEqualOrBracketExpression;
import mjc.core.parser.interfaces.IExpression;

public class EqualOrBracketExpression1 implements IEqualOrBracketExpression{
	//EqualOrBracketExpression ::= “=” Expression “;”
	IExpression expression;
	
	public EqualOrBracketExpression1(IExpression expression) {
		this.expression = expression;
	}

	@Override
	public String getValue() {
		return " = "+(expression!=null? expression.getValue(): "expression==null")+";";
	}

}
