package mjc.core.parser;

import mjc.core.parser.interfaces.ICommaExpressionArgu;

public class CommaExpressionArgu2 implements ICommaExpressionArgu{
	//CommaExpressionArgu ::= lambda
	
	@Override
	public String gtValue() {
		// TODO Auto-generated method stub
		return Lambda.getValue();
	}

}
