package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class TypeOrIdentifier5 implements ITypeOrIdentifier {
	// Expression ::= "boolean" “[“ Expression “]”
	IExpression expression;

	public TypeOrIdentifier5(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String getValue() {
		return "boolean " + "[" + (expression!=null? expression.getValue(): "expression==null") + "]";
	}
}
