package mjc.core.parser;

import mjc.core.parser.interfaces.IMethodDeclaration;
import mjc.core.parser.interfaces.IMethodDeclarationPrime;

public class MethodDeclarationPrime1 implements IMethodDeclarationPrime {

	//MethodDeclaration` ::= MethodDeclaration MethodDeclaration`
	IMethodDeclaration methodDeclaration;
	IMethodDeclarationPrime methodDeclarationPrime;
	
	@Override
	public String getValue() {
		return methodDeclaration.getValue()+methodDeclarationPrime.getValue();
	}

}
