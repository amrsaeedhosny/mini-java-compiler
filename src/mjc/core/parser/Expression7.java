package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionPrime;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class Expression7 implements IExpression {

	// Expression ::= “new” TypeOrIdentifier Expression`
	IExpressionPrime expressionPrime;
	ITypeOrIdentifier typeOrIdentifier;
	
	public Expression7(IExpressionPrime expressionPrime, ITypeOrIdentifier typeOrIdentifier) {
		this.expressionPrime = expressionPrime;
		this.typeOrIdentifier = typeOrIdentifier;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "new"+typeOrIdentifier.getValue() + expressionPrime.getValue();
	}
}
