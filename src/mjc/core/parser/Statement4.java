package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IStatement;

public class Statement4 implements IStatement{

	//Statement ::= “System.out.println” “(“ Expression “)” “;”
	
	IExpression expression;
	
	public Statement4(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String getValue() {
		return "\nSystem.out.println" + "(" 
			+ (expression!=null? expression.getValue(): "expression==null")+")"+";\n";
	}

}
