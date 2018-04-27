package mjc.core.parser;

import mjc.core.parser.interfaces.ICommaExpressionArgu;
import mjc.core.parser.interfaces.IExpression;

public class CommaExpressionArgu1 implements ICommaExpressionArgu{
	//CommaExpressionArgu ::= “,” Expression CommaExpressionArgu
	
	IExpression expression;
	ICommaExpressionArgu commaExpressionArgu;
	
	public CommaExpressionArgu1(IExpression expression, ICommaExpressionArgu commaExpressionArgu) {
		super();
		this.expression = expression;
		this.commaExpressionArgu = commaExpressionArgu;
	}

	@Override
	public String getValue() {
		return ", "+(expression!=null? expression.getValue(): "expression==null")
			+ (commaExpressionArgu!=null? commaExpressionArgu.getValue(): "commaExpressionArgu==null");
	}

}
