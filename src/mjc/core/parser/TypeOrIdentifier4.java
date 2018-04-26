package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class TypeOrIdentifier4 implements ITypeOrIdentifier {
	// Expression ::= "char" “[“ Expression “]”
	IExpression expression;

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "char" + "[" + expression.getValue() + "]";
	}
}
