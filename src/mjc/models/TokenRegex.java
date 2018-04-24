package mjc.models;

public class TokenRegex {
	String label;
	String regex;

	public TokenRegex() {
	}

	public TokenRegex(String label, String regex) {
		this.label = label;
		this.regex = regex;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

}
