
public class Functions {
	private char[] characterTable = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
	private int salt;
	
	public Functions(int salt) {		
		this.salt = salt % 27;
		if (this.salt < 0) this.salt += 27;
	}
	
	public void toText(String bin) {
	    String correctBin = bin;
	    while (correctBin.length() % 5 > 0) {
	        correctBin += "0";
	    }
	    String result = "";
	    int current = 0;
	    while (current < correctBin.length()) {
	        int value = 0;
	        for (int i = 4; i >= 0; i--) {
	        	char charac = correctBin.charAt(current);
	            value += (int)Math.pow(2, i) * Character.getNumericValue(charac);
	            current++;
	        }
	        if (value - salt < 0) value = (value - salt) + 27;
	        else value = (value - salt) % 27;
	        result += characterTable[value];
	    }
	    System.out.println(result);
	}
	
	public void toBin(String text) {
		String converted = "";
		for (int pos = 0; pos < text.length(); pos++) {
			char character = text.charAt(pos);
			int value = 0;
			for (int i = 0; i < characterTable.length; i++) {
				if (character == characterTable[i]) {
					value = i;
				    break;
				}
			}
			int current = (value + salt) % 27;
			String nieuw = "";
			while (current > 0) {
				nieuw = current % 2 + nieuw;
				current = current / 2;
			}
			while (nieuw.length() < 5) nieuw = "0" + nieuw;
			converted += nieuw;
		}
		System.out.println(converted);
	}
}
