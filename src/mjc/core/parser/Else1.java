package mjc.core.parser;

import mjc.core.parser.interfaces.IElse;
import mjc.core.parser.interfaces.IStatement;

public class Else1 implements IElse{
	//Else ::= “else” Statement
	IStatement statement;
	
	
	public Else1(IStatement statement) {
		this.statement = statement;
	}

	@Override
	public String geetValue() {
		// TODO Auto-generated method stub
		return "else"+statement.getValue();
	}

}
