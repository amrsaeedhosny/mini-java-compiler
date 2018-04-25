package mjc.core.parser;

import mjc.core.parser.interfaces.IIdentifier;
import mjc.core.parser.interfaces.IMainClass;
import mjc.core.parser.interfaces.IStatement;

public class MainClass implements IMainClass {
	/*
	 	MainClass ::= “class” Identifier “{“ “public” “static” “void” “main” “(“ “String” “[“ “]”
		Identifier “)” “{“ Statement “}” “}”
	 * */
	
	IIdentifier identifierClassName;
	IIdentifier identifierMainArg;
	IStatement statement;
	

	@Override
	public String getValue() {
		return "class" + identifierClassName.getValue() + 
				"{public static void main(String []" + identifierMainArg.getValue()
				+ ") {" + statement.getValue() + "} }";
	}

}
