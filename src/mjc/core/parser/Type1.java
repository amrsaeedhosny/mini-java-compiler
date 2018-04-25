package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;
import mjc.core.parser.interfaces.IType;

public class Type1 implements IType{

	//Type ::="int" Brackets
	
	IBrackets brackets;
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "int"+brackets.getValue();
	}

}
