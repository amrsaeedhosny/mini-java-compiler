package mjc.core.parser;

import mjc.core.parser.interfaces.IConstructorDeclaration;
import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IStatmentPrime;
import mjc.core.parser.interfaces.ITypeIdentifier;
import mjc.core.parser.interfaces.IVarDeclarationPrime;

public class ConstructorDeclaration implements IConstructorDeclaration{
//  ConstructorDeclatation ::= Identifier
//	“(“ TypeIdentifier “)”
//	“{“ VarDeclaration` Statement` “}”
	
	IIdentifier identifier;
	ITypeIdentifier typeIdentifier;
	IVarDeclarationPrime varDeclarationPrime;
	IStatmentPrime statmentPrime;
	
	
	public ConstructorDeclaration(IIdentifier identifier, ITypeIdentifier typeIdentifier,
			IVarDeclarationPrime varDeclarationPrime, IStatmentPrime statmentPrime) {
		this.identifier = identifier;
		this.typeIdentifier = typeIdentifier;
		this.varDeclarationPrime = varDeclarationPrime;
		this.statmentPrime = statmentPrime;
	}


	@Override
	public String getValue() {
		return "\n" 
			+ (identifier!=null? identifier.getValue(): "identifier==null")
			+ "("+(typeIdentifier!=null? typeIdentifier.getValue(): "typeIdentifier==null")
			+ ")" + "{\n"
			+ (varDeclarationPrime!=null? varDeclarationPrime.getValue(): "varDeclarationPrime==null")
			+ "\n" +(statmentPrime!=null? statmentPrime.getValue(): "statmentPrime==null")+"\n}";
	}

}
