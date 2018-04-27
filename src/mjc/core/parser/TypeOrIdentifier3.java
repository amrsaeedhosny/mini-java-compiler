package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class TypeOrIdentifier3 implements ITypeOrIdentifier {

	// Expression ::= "String" “[“ Expression “]”
	IExpression expression;

	public TypeOrIdentifier3(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String getValue() {
		return "String " + "[" + (expression!=null? expression.getValue(): "expression==null") + "]";
	}
}
