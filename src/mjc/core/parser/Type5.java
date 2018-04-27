package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;
import mjc.core.parser.interfaces.IType;

public class Type5 implements IType{

	//Type ::="char" Brackets
	
	IBrackets brackets;
	
	public Type5(IBrackets brackets) {
		super();
		this.brackets = brackets;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "char"+(brackets!=null? brackets.getValue(): "brackets==null");
	}

}
