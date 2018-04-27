package mjc.core.parser;

import mjc.core.parser.interfaces.IConstructorDeclaration;
import mjc.core.parser.interfaces.IConstructorDeclarationPrime;

public class ConstructorDeclarationPrime1 implements IConstructorDeclarationPrime{
	//ConstructorDeclaration` ::= ConstructorDeclaration ConstructorDeclaration`
	
	IConstructorDeclarationPrime constructorDeclarationPrime;
	IConstructorDeclaration constructorDeclaration;
	
	
	public ConstructorDeclarationPrime1(IConstructorDeclarationPrime constructorDeclarationPrime,
			IConstructorDeclaration constructorDeclaration) {
		this.constructorDeclarationPrime = constructorDeclarationPrime;
		this.constructorDeclaration = constructorDeclaration;
	}


	@Override
	public String getValue() {
		return (constructorDeclaration!=null? constructorDeclaration.getValue(): "constructorDeclaration==null") 
				+ (constructorDeclarationPrime!=null? constructorDeclarationPrime.getValue(): "constructorDeclarationPrime==null");
	}

}
