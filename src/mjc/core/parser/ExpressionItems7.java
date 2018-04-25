package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionItems;

public class ExpressionItems7 implements IExpressionItems {
	// ExpressionItems::= "<=" Expression
	IExpression expression;

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "<=" + expression.getValue();
	}
}
