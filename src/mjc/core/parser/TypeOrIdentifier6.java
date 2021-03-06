package mjc.core.parser;

import mjc.core.parser.interfaces.IExpressionArgu;
import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class TypeOrIdentifier6 implements ITypeOrIdentifier {

	// TypeOrIdentifier ::= Identifier �(� ExpressionArgu �)�

	IIdentifier identifier;
	IExpressionArgu expressionArgu;

	public TypeOrIdentifier6(IIdentifier identifier, IExpressionArgu expressionArgu) {
		super();
		this.identifier = identifier;
		this.expressionArgu = expressionArgu;
	}

	@Override
	public String getValue() {
		return (identifier!=null? identifier.getValue(): "identifier==null") + "(" + (expressionArgu!=null? expressionArgu.getValue(): "expressionArgu==null") + ")";
	}

}
