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
		return "\n" + (identifier!=null? identifier.getValue(): "identifier=null") 
			+ (equalOrBracketExpression!=null? equalOrBracketExpression.getValue(): "equalOrBracketExpression==null");
	}

}
