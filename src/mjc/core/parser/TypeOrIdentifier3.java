package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.ITypeOrIdentifier;

public class TypeOrIdentifier3 implements ITypeOrIdentifier{

	// Expression ::= "String" “[“ Expression “]”
		IExpression expression;

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return "String" + "[" + expression.getValue() + "]";
		}
}
