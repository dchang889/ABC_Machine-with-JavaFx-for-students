
public class Word {

	private String word;
	private int value=0;
	
	public Word() {
		this.word = "";
	}
	
	
	public Word(String s) {
		word = CheckValidity(s);
	}


	private String CheckValidity(String s) {
		if (s.matches("\\w+")) //One word only filter
		{
			value = doValue(s);
			return s;
		}
		else 
		{
			return "Your word had a space, symbol, and or number in it ¯\\_(~.~)_/¯";
		}
	}


	private int doValue(String s) {
		String temp = s.toLowerCase();
		for (int i = 0; i<temp.length(); i++){
			char c = temp.charAt(i);
			value += (c-96);
		}
		return value;
	}


	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}
}
