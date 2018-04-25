package mjc.core.parser;

import mjc.core.parser.interfaces.ICommaExpressionArgu;
import mjc.core.parser.interfaces.IExpression;

public class CommaExpressionArgu1 implements ICommaExpressionArgu{

	//CommaExpressionArgu ::= “,” Expression CommaExpressionArgu
	IExpression expression;
	ICommaExpressionArgu commaExpressionArgu;
	@Override
	public String gtValue() {
		// TODO Auto-generated method stub
		return ","+expression.getValue()+commaExpressionArgu.gtValue();
	}

}
