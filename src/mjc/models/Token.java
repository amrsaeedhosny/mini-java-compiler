package mjc.models;

public class Token {
	
	// Token types constants
	public static final String UNKNOWN_TOKEN_TYPE = "Unknown Token";
	public static final String EOF_TOKEN_TYPE = "<EOF>";
	
	public static final String EOL_TOKEN_TYPE = "EOL";
	public static final String SYSOUT_TOKEN_TYPE = "SYSTEM.OUT.PRINTLN";
	public static final String S_COMMENTS_TOKEN_TYPE = "S_COMMENTS";
	public static final String M_COMMENTS_TOKEN_TYPE = "M_COMMENTS";
	
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
