package mjc.core.parser;

import mjc.core.parser.interfaces.ITypeIdentifier;

public class TypeIdentifier2 implements ITypeIdentifier{

	//TypeIdentifier ::=lambda
	
	@Override
	public String getValue() {
		return Lambda.getValue();
		
	}

}
