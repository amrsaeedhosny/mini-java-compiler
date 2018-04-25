package mjc.core.parser;

import mjc.core.parser.interfaces.IClassDeclaration;
import mjc.core.parser.interfaces.IConstructorDeclarationPrime;
import mjc.core.parser.interfaces.IExtendsPrime;
import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IMethodDeclarationPrime;
import mjc.core.parser.interfaces.IVarDeclarationPrime;

public class ClassDeclaration implements IClassDeclaration {
	/*
		ClassDeclaration ::= “class” Identifier Extends` “{“ VarDeclaration` ConstructorDeclaration`
		MethodDeclaration` “}”
	 */
	
	IIdentifier identifier;
	IExtendsPrime extendsPrime;
	IVarDeclarationPrime varDeclarationPrime;
	IConstructorDeclarationPrime constructorDeclarationPrime;
	IMethodDeclarationPrime methodDeclarartionPrime;
	

	@Override
	public String getValue() {
		return "class" + identifier.getValue() + extendsPrime.getValue() + "{" +
				varDeclarationPrime.getValue() + constructorDeclarationPrime.getValue() + 
				methodDeclarartionPrime.getValue() + "}";
	}

}
