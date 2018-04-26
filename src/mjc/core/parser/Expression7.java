package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.ITypeIdentifier;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class Expression7 implements IExpression {

	// Expression ::= “new” TypeOrIdentifier Expression
	IExpression expression;
	ITypeOrIdentifier typeOrIdentifier;
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "new"+typeOrIdentifier.getValue() + expression.getValue();
	}
}
