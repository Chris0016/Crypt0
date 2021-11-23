package application;

public class Encrypt {
	private String encryptedText;
	private int pTextLength, kLength;
	
	final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public Encrypt() {
	
	}
	
	public String ceasarEncrypt(String plaintext, int key) {
		int holder = 0;
		encryptedText = "";
		pTextLength = plaintext.length();
		for (int i = 0; i < pTextLength; i++) {
			char curr = plaintext.charAt(i);
			if (curr == ' '){
				encryptedText += " ";
			}else{
				holder = ( curr + key) % 91;
				if (holder < 65 )
					holder += 65;
				encryptedText += Character.toString(holder);
			}	
		}
		return encryptedText;
	}
	
	public String vigenereEncrypt(String plaintext, String key) {
		encryptedText = "";
		pTextLength = plaintext.length();
		kLength = key.length();
		
		int holder;

		for (int i = 0; i < pTextLength; i++) {
			//char1 =  (key.charAt(i%(kLength)) - 65);
			//plain1 = (plaintext.charAt(i) - 65);
			// Why 130; ascii starts the letters at 65 and we have two letters
			holder = ((key.charAt(i%(kLength)  + plaintext.charAt(i))) - 130) % 26;		
			encryptedText += ALPHABET.substring(holder , holder+1);				
		}
		return encryptedText;
		
	}
	public String railFenceEncrypt(String plaintext){
		String part1 = "";
		String part2 = "";
		String curr = "";
		
		
		encryptedText = "";
		pTextLength = plaintext.length();		
		plaintext = plaintext.replaceAll("\\s+","");

		
		for(int i = 0; i < pTextLength; i++){
			curr = Character.toString(plaintext.charAt(i));
			if (i % 2 != 0 || i == 1 )
				part2 += curr;
			else 
				part1 += curr;
		}

		encryptedText = part1 + " " +  part2;
		return encryptedText;
	}
	
	public String beufort(String plaintext, String key){
		encryptedText = "";
		pTextLength = plaintext.length();
		kLength = key.length();
		plaintext = plaintext.toUpperCase();
		
		int offset, pos;
		int count = 0;
		for(int i = 0; i < pTextLength; i++ ){
			offset = plaintext.charAt(i) - 64;
		
			pos =   ALPHABET.indexOf(key.charAt(count)) - offset  ;

			pos = (pos < 0)?   (27 + pos) % 26 : (pos + 1) % 26;

			encryptedText += ALPHABET.charAt(pos) ;

			count++;
			if(count == kLength)
				count = 0;
		}
		return encryptedText;
	}
	
	public String encrypt(String plaintext, String key){
		
		encryptedText = "";
		pTextLength = plaintext.length();
		String runningKey = key +  plaintext;
		runningKey = runningKey.substring(0, pTextLength);

		int offset, pos;

		for(int i = 0; i < pTextLength; i++){
			offset = plaintext.charAt(i) - 65;

			pos =  (ALPHABET.indexOf(runningKey.charAt(i)) + offset) % 25;

			encryptedText += ALPHABET.charAt(pos);

		}
		return encryptedText;
	}

	public boolean isEncrypted() {
		return !encryptedText.equals("");
	}
	
}
