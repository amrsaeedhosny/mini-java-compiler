package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionItems;

public class ExpressionItems2 implements IExpressionItems {

	// ExpressionItems::= "||" Expression
	IExpression expression;

	public ExpressionItems2(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "||" + (expression!=null? expression.getValue(): "expression==null");
	}
}
