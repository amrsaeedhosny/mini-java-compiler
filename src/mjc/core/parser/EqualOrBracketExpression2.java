package mjc.core.parser;

import mjc.core.parser.interfaces.IEqualOrBracketExpression;
import mjc.core.parser.interfaces.IExpression;

public class EqualOrBracketExpression2 implements IEqualOrBracketExpression {
	// EqualOrBracketExpression ::= “[“ Expression “]” “=” Expression “;”

	IExpression expression1;
	IExpression expression2;

	public EqualOrBracketExpression2(IExpression expression1, IExpression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "[" + expression1.getValue() + "]" + "=" + expression2.getValue() + ";";
	}

}
