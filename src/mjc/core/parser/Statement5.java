package mjc.core.parser;

import mjc.core.parser.interfaces.IEqualOrBracketExpression;
import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IStatement;

public class Statement5 implements IStatement{

	//Statement ::= Identifier EqualOrBracketExpression
	
	IIdentifier identifier;
	IEqualOrBracketExpression equalOrBracketExpression;
	
	public Statement5(IIdentifier identifier, IEqualOrBracketExpression equalOrBracketExpression) {
		super();
		this.identifier = identifier;
		this.equalOrBracketExpression = equalOrBracketExpression;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return identifier.getValue()+equalOrBracketExpression.getValue();
	}

}
