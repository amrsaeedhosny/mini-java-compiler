package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IStatement;

public class Statement3 implements IStatement{

	//Statement ::= “while” “(“ Expression “)” Statement
	
	IExpression expression;
	IStatement statement;
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "while"+"("+expression.getValue()+")"+statement.getValue();
	}

}
