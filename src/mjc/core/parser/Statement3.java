package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IStatement;

public class Statement3 implements IStatement{

	//Statement ::= “while” “(“ Expression “)” Statement
	
	IExpression expression;
	IStatement statement;
	
	public Statement3(IExpression expression, IStatement statement) {
		super();
		this.expression = expression;
		this.statement = statement;
	}

	@Override
	public String getValue() {
		return "\nwhile " + "(" 
			+ (expression!=null? expression.getValue(): "expression==null")+ ")" 
			+ (statement!=null? statement.getValue(): "statement==null");
	}

}
