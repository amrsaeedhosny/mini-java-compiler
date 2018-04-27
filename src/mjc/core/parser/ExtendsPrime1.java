package mjc.core.parser;

import mjc.core.parser.interfaces.IExtendsPrime;
import mjc.core.parser.interfaces.IIdentifier;

public class ExtendsPrime1 implements IExtendsPrime {
	// Extends` ::= “extends” Identifier
	
	IIdentifier identifier;

	public ExtendsPrime1(IIdentifier identifier) {
		this.identifier = identifier;
	}

	@Override
	public String getValue() {
		return "extends " + (identifier!=null? identifier.getValue():"identifier==null");
	}

}
