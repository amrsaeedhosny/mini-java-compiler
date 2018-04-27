package mjc.core.parser;

import mjc.core.parser.interfaces.IBrackets;
import mjc.core.parser.interfaces.IType;

public class Type3 implements IType{

	//Type ::="float" Brackets
	
	IBrackets brackets;
	
	public Type3(IBrackets brackets) {
		super();
		this.brackets = brackets;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return "float"+(brackets!=null? brackets.getValue(): "brackets==null");
	}

}
