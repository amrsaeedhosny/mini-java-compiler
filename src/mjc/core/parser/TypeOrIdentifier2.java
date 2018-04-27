package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class TypeOrIdentifier2 implements ITypeOrIdentifier {

	// Expression ::= "float" “[“ Expression “]”
	IExpression expression;

	public TypeOrIdentifier2(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String getValue() {
		return "float " + "[" + (expression!=null? expression.getValue(): "expression==null") + "]";
	}

}
