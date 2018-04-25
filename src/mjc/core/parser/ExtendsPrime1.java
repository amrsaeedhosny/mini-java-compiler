package mjc.core.parser;

import mjc.core.parser.interfaces.IExtendsPrime;
import mjc.core.parser.interfaces.IIdentifier;

public class ExtendsPrime1 implements IExtendsPrime {
	// Extends` ::= “extends” Identifier
	
	IIdentifier identifier;

	@Override
	public String getValue() {
		return "extends" + identifier.getValue();
	}

}
