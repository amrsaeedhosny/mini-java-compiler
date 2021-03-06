package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class TypeOrIdentifier1 implements ITypeOrIdentifier {
	// Expression ::= "int" �[� Expression �]�
	
	IExpression expression;

	public TypeOrIdentifier1(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String getValue() {
		return "int " +"["+ (expression!=null? expression.getValue(): "expression==null") + "]";
	}
}
