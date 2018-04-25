package mjc.core.parser;

import mjc.core.parser.interfaces.IStatement;
import mjc.core.parser.interfaces.IStatmentPrime;

public class StatementPrime1 implements IStatmentPrime{

	//Statement` ::= Statement Statement`
	
	IStatement statment;
	IStatmentPrime statementPrime;
	
	@Override
	public String getValue() {
		return statment.getValue()+statementPrime.getValue();
	}

}
