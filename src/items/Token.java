package items;

public class Token {
	
	public static final String UNKNOWN_TOKEN_TYPE = "Unknown Token";
	
    String type;
    String value;
    int startIndex;
    int endIndex;
    
    public Token(){
        
    }
    
    public Token(String type, String value, int startIndex, int endIndex) {
        this.type = type;
        this.value = value;
        this.startIndex = startIndex;
        this.endIndex =endIndex;
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
    	+ "  End Index: " + endIndex;
    }
    
}
