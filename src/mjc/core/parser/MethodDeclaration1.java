package mjc.core.parser;

import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IMethodDeclaration;
import mjc.core.parser.interfaces.IStatmentPrime;
import mjc.core.parser.interfaces.IType;
import mjc.core.parser.interfaces.ITypeIdentifier;
import mjc.core.parser.interfaces.IVarDeclarationPrime;

public class MethodDeclaration1 implements IMethodDeclaration {
//	MethodDeclaration ::= “public” Type Identifier
//			“(“ TypeIdentifier “)”
//			“{“ VarDeclaration` Statement` “return” Expression “;” “}”
	
	IType type;
	IIdentifier identifier;
	ITypeIdentifier typeIdentifier;
	IVarDeclarationPrime varDeclarationPrime;
	IStatmentPrime statmentPrime;
	IExpression expression;
	
	
	public MethodDeclaration1(IType type, IIdentifier identifier, ITypeIdentifier typeIdentifier,
			IVarDeclarationPrime varDeclarationPrime, IStatmentPrime statmentPrime, IExpression expression) {
		super();
		this.type = type;
		this.identifier = identifier;
		this.typeIdentifier = typeIdentifier;
		this.varDeclarationPrime = varDeclarationPrime;
		this.statmentPrime = statmentPrime;
		this.expression = expression;
	}


	@Override
	public String getValue() {
		return "public"+type.getValue()+identifier.getValue()+"("+typeIdentifier.getValue()+
				")"+"{"+varDeclarationPrime.getValue()+statmentPrime.getValue()+
				"return"+expression.getValue()+";"+"}";
	}

}
