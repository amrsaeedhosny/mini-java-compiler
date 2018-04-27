package mjc.core.parser;

import mjc.core.parser.interfaces.ICommaExpressionArgu;
import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionArgu;

public class ExpressionArgu1 implements IExpressionArgu{

	//ExpressionArgu ::= Expression CommaExpressionArgu
	IExpression expression;
	ICommaExpressionArgu commaExpressionArgu;
	
	public ExpressionArgu1(IExpression expression, ICommaExpressionArgu commaExpressionArgu) {
		super();
		this.expression = expression;
		this.commaExpressionArgu = commaExpressionArgu;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return (expression!=null? expression.getValue(): "expression=null")+(
				commaExpressionArgu!=null? commaExpressionArgu.getValue(): "commaExpressionArgu==null");
	}

}
