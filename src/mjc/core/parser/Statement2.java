package mjc.core.parser;

import mjc.core.parser.interfaces.IElse;
import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IStatement;

public class Statement2 implements IStatement{

	//Statement ::= “if” “(“ Expression “)” Statement Else

	IExpression expression;
	IStatement statement;
	IElse Else;
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "if"+"("+expression.getValue()+")"+statement.getValue()+Else.geetValue();
	}

}
