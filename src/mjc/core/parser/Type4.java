package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;
import mjc.core.parser.interfaces.IType;

public class Type4 implements IType{

	//Type ::="String" Brackets
	
	IBrackets brackets;
	
	public Type4(IBrackets brackets) {
		super();
		this.brackets = brackets;
	}

	@Override
	public String getValue() {
		return "String "+(brackets!=null? brackets.getValue(): "brackets==null");
	}

}
