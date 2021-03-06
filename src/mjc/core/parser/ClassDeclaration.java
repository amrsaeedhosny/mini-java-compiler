package mjc.core.parser;

import mjc.core.parser.interfaces.IClassDeclaration;
import mjc.core.parser.interfaces.IConstructorDeclarationPrime;
import mjc.core.parser.interfaces.IExtendsPrime;
import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IMethodDeclarationPrime;
import mjc.core.parser.interfaces.IVarDeclarationPrime;

public class ClassDeclaration implements IClassDeclaration {
	/*
		ClassDeclaration ::= class Identifier Extends` { VarDeclaration` ConstructorDeclaration`
		MethodDeclaration` }
	 */
	
	IIdentifier identifier;
	IExtendsPrime extendsPrime;
	IVarDeclarationPrime varDeclarationPrime;
	IConstructorDeclarationPrime constructorDeclarationPrime;
	IMethodDeclarationPrime methodDeclarartionPrime;

	public ClassDeclaration(IIdentifier identifier, IExtendsPrime extendsPrime,
			IVarDeclarationPrime varDeclarationPrime, IConstructorDeclarationPrime constructorDeclarationPrime,
			IMethodDeclarationPrime methodDeclarartionPrime) {
		this.identifier = identifier;
		this.extendsPrime = extendsPrime;
		this.varDeclarationPrime = varDeclarationPrime;
		this.constructorDeclarationPrime = constructorDeclarationPrime;
		this.methodDeclarartionPrime = methodDeclarartionPrime;
	}

	@Override
	public String getValue() {
		return "\n\nclass " + (identifier!=null? identifier.getValue(): "identifier==null") + " "
				+ (extendsPrime!=null? extendsPrime.getValue(): "extendsPrime==null") 
				+ "{" 
				+ (varDeclarationPrime!=null? varDeclarationPrime.getValue(): "varDeclarationPrime==null") 
				+ (constructorDeclarationPrime!=null? constructorDeclarationPrime.getValue(): "constructorDeclarationPrime==null") 
				+ (methodDeclarartionPrime!=null? methodDeclarartionPrime.getValue(): "methodDeclarartionPrime==null") + "\n}";
	}

}
