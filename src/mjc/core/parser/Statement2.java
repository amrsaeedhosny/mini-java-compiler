package mjc.core.parser;

import mjc.core.parser.interfaces.IElse;
import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IStatement;

public class Statement2 implements IStatement{

	//Statement ::= “if” “(“ Expression “)” Statement Else

	IExpression expression;
	IStatement statement;
	IElse Else;
	
	public Statement2(IExpression expression, IStatement statement, IElse else1) {
		super();
		this.expression = expression;
		this.statement = statement;
		Else = else1;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "if"+"("+expression.getValue()+")"+statement.getValue()+Else.geetValue();
	}

}
