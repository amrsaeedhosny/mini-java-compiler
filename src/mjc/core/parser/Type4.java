package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;
import mjc.core.parser.interfaces.IType;

public class Type4 implements IType{

	//Type ::="String" Brackets
	
	IBrackets brackets;
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "String"+brackets.getValue();
	}

}
