package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;
import mjc.core.parser.interfaces.IType;

public class Type1 implements IType{
	//Type ::="int" Brackets
	
	IBrackets brackets;
	
	public Type1(IBrackets brackets) {
		super();
		this.brackets = brackets;
	}

	@Override
	public String getValue() {
		return "int "+(brackets!=null? brackets.getValue(): "brackets==null");
	}

}
