package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IStatement;

public class Statement4 implements IStatement{

	//Statement ::= “System.out.println” “(“ Expression “)” “;”
	
	IExpression expression;
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "System.out.println"+"("+expression.getValue()+")"+";";
	}

}
