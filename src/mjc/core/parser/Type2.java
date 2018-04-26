package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;
import mjc.core.parser.interfaces.IType;

public class Type2 implements IType{

	//Type ::="boolean" Brackets
	
	IBrackets brackets;
	
	public Type2(IBrackets brackets) {
		super();
		this.brackets = brackets;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "boolean"+brackets.getValue();
	}

}
