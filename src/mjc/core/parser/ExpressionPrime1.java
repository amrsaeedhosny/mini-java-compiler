package mjc.core.parser;

import mjc.core.parser.interfaces.IExpressionItems;
import mjc.core.parser.interfaces.IExpressionPrime;

public class ExpressionPrime1 implements IExpressionPrime{
	//Expression` ::= ExpressionItems Expression’
	IExpressionItems expressionItems;
	IExpressionPrime expressionPrime;
	
	
	public ExpressionPrime1(IExpressionItems expressionItems, IExpressionPrime expressionPrime) {
		super();
		this.expressionItems = expressionItems;
		this.expressionPrime = expressionPrime;
	}


	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return (expressionItems!=null? expressionItems.getValue(): "expressionItems==null")+(expressionPrime!=null? expressionPrime.getValue(): "expressionPrime==null");
	}

}
