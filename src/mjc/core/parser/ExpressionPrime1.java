package mjc.core.parser;

import mjc.core.parser.interfaces.IExpressionItems;
import mjc.core.parser.interfaces.IExpressionPrime;

public class ExpressionPrime1 implements IExpressionPrime{
	//Expression` ::= ExpressionItems Expression’
	IExpressionItems expressionItems;
	IExpressionPrime expressionPrime;
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return expressionItems.getValue()+expressionPrime.getValue();
	}

}
