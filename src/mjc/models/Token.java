package mjc.models;

public class Token {
	
	// Token types constants
	public static final String UNKNOWN_TOKEN_TYPE = "Unknown Token";
	public static final String EOF_TOKEN_TYPE = "<EOF>";
	
	public static final String EOL_TOKEN_TYPE = "EOL";
	public static final String SYSOUT_TOKEN_TYPE = "SYSTEM.OUT.PRINTLN";
//	public static final String LEFT_CURLY_B_TOKEN_TYPE = "LEFT_CURLY_B";
//	public static final String RIGHT_CURLY_B_TOKEN_TYPE = "RIGHT_CURLY_B";
//	public static final String LEFT_SQUARE_B_TOKEN_TYPE = "LEFT_SQUARE_B";
//	public static final String RIGHT_SQUARE_B_TOKEN_TYPE = "RIGHT_SQUARE_B";
//	public static final String LEFT_ROUND_B_TOKEN_TYPE = "LEFT_ROUND_B";
//	public static final String RIGHT_ROUND_B_TOKEN_TYPE = "RIGHT_ROUND_B";
//	public static final String COMMA_TOKEN_TYPE = "COMMA";
//	public static final String SEMICOLON_TOKEN_TYPE = "SEMICOLON";
//	public static final String NOT_TOKEN_TYPE = "NOT";
//	public static final String ASSIGNMENT_TOKEN_TYPE = "ASSIGNMENT";
//	public static final String EQUAL_TOKEN_TYPE = "EQUAL";
//	public static final String NOT_EQUAL_TOKEN_TYPE = "NOT_EQUAL";
//	public static final String AND_TOKEN_TYPE = "AND";
//	public static final String OR_TOKEN_TYPE = "OR";
//	public static final String MINUS_TOKEN_TYPE = "MINUS";
//	public static final String MULTIPLY_TOKEN_TYPE = "MULTIPLY";
//	public static final String DIV_TOKEN_TYPE = "DIV";
//	public static final String MOD_TOKEN_TYPE = "MOD";
//	public static final String LESSTHAN_TOKEN_TYPE = "LESSTHAN";
//	public static final String GREATERTHAN_TOKEN_TYPE = "GREATERTHAN";
//	public static final String LESS_EQ_TOKEN_TYPE = "LESS_EQ";
//	public static final String GREATER_EQ_TOKEN_TYPE = "GREATER_EQ";
//	public static final String IF_TOKEN_TYPE = "IF";
//	public static final String INT_TOKEN_TYPE = "INT";
//	public static final String ELSE_TOKEN_TYPE = "ELSE";
//	public static final String MAIN_TOKEN_TYPE = "MAIN";
//	public static final String THIS_TOKEN_TYPE = "THIS";
//	public static final String TRUE_TOKEN_TYPE = "TRUE";
//	public static final String VOID_TOKEN_TYPE = "VOID";
//	public static final String CLASS_TOKEN_TYPE = "CLASS";
//	public static final String FALSE_TOKEN_TYPE = "FALSE";
//	public static final String WHILE_TOKEN_TYPE = "WHILE";
//	public static final String LENGTH_TOKEN_TYPE = "LENGTH";
//	public static final String PUBLIC_TOKEN_TYPE = "PUBLIC";
//	public static final String PRIVATE_TOKEN_TYPE = "PRIVATE";
//	public static final String PROTECTED_TOKEN_TYPE = "PROTECTED";
//	public static final String RETURN_TOKEN_TYPE = "RETURN";
//	public static final String STATIC_TOKEN_TYPE = "STATIC";
//	public static final String NEW_TOKEN_TYPE = "NEW";
//	public static final String STRING_TOKEN_TYPE = "STRING";
//	public static final String FLOAT_TOKEN_TYPE = "FLOAT";
	
	public String type;
	public String value;
	public int startIndex;
	public int endIndex;
    
    public Token(){
    	// no parameters constructor        
    }
    
    public Token(String type, String value, int startIndex, int endIndex) {
        this.type = type;
        this.value = value;
        this.startIndex = startIndex;
        this.endIndex =endIndex;
    }
    
    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	@Override
    public String toString() {
    	return "Type: " + type + "  Value: " + value + "  Start Index: " + startIndex
    	+ "  End Index: " + endIndex + "\n";
    }
    
}
