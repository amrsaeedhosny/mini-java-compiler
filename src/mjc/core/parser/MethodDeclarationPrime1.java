package mjc.core.parser;

import mjc.core.parser.interfaces.IMethodDeclaration;
import mjc.core.parser.interfaces.IMethodDeclarationPrime;

public class MethodDeclarationPrime1 implements IMethodDeclarationPrime {

	//MethodDeclaration` ::= MethodDeclaration MethodDeclaration`
	IMethodDeclaration methodDeclaration;
	IMethodDeclarationPrime methodDeclarationPrime;
	
	public MethodDeclarationPrime1(IMethodDeclaration methodDeclaration,
			IMethodDeclarationPrime methodDeclarationPrime) {
		super();
		this.methodDeclaration = methodDeclaration;
		this.methodDeclarationPrime = methodDeclarationPrime;
	}

	@Override
	public String getValue() {
		return (methodDeclaration!=null? methodDeclaration.getValue(): "methodDeclaration==null")
			+ (methodDeclarationPrime!=null? methodDeclarationPrime.getValue(): "methodDeclarationPrime==null");
	}

}
