package mjc.core.parser;

import mjc.core.parser.interfaces.IAfterDot;
import mjc.core.parser.interfaces.IExpressionArgu;
import mjc.core.parser.interfaces.IIdentifier;

public class AfterDot2 implements IAfterDot {
	// AfterDot ::= Identifier "(" ExpressionArgu ")"
	IIdentifier identifier;
	IExpressionArgu expressionArgu;
	@Override
	public String gtValue() {
		// TODO Auto-generated method stub
		return identifier.getValue()+"("+expressionArgu.getValue()+")";
	}
}
