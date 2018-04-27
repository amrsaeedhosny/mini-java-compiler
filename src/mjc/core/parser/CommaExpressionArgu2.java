package mjc.core.parser;

import mjc.core.parser.interfaces.ICommaExpressionArgu;

public class CommaExpressionArgu2 implements ICommaExpressionArgu {
	// CommaExpressionArgu ::= lambda

	public CommaExpressionArgu2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return Lambda.getValue();
	}

}
