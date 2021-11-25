package application;

public class Encrypt {
	private String encryptedText;
	private int pTextLength, kLength;
	
	final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public Encrypt() {
	
	}
	
	public String ceasar(String plaintext, int key) {
		int holder = 0;
		encryptedText = "";
		pTextLength = plaintext.length();
		plaintext = plaintext.toUpperCase();

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
	
	public String vigenere(String plaintext, String key) {
		encryptedText = "";

		pTextLength = plaintext.length();
		kLength = key.length();

		plaintext = plaintext.toUpperCase();

		int offset, pos;

		for(int i = 0; i < pTextLength; i++){
			offset = plaintext.charAt(i) - 65;
			
			pos =  (ALPHABET.indexOf(key.charAt( i % kLength )) + offset) % 26;

			encryptedText += ALPHABET.charAt(pos);

		}
		return encryptedText;
		
	}

	public String railfence(String plaintext, int rails){
		



		if (rails < 2 || rails >=  plaintext.length())
			throw new IllegalArgumentException("Invalid number of rails: Must be greater than 2 and less than the number of letters");

		String curr = "";
		
		int buckets = pTextLength;
		String[][] holder = new String[rails + 1][buckets + 1];
		encryptedText = "";
			
		plaintext = plaintext.replaceAll("\\s+","");
		pTextLength =	plaintext.length();

		int countY = 0;
		int countX = 0;

		boolean dir_isDown = false;

		//FIX ME
		for(int i = 0; i < pTextLength; i++){
			curr = Character.toString(plaintext.charAt(i));
			System.out.println("Count X: " + countX);
			System.out.println("Count Y: " + countY );
			System.out.println("i: " + i + "\n\n");

			if(countX == 0 || countX == rails - 1){
				countY++;
				dir_isDown = !dir_isDown;
			}

			holder[countX][countY] = curr;

			if  (dir_isDown) 
				countX++; 
			else
				countX--;


			

		}
	for(int y = 0; y < rails; y++){
		for (int x = 0; x < buckets; x++){
				curr = holder[y][x] ;
				if (!(curr == null))
					encryptedText += curr + "";
			}
		}

		return encryptedText.toUpperCase();
			
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
	
	public String autokey(String plaintext, String key){
		
		encryptedText = "";
		pTextLength = plaintext.length();
		String runningKey = key +  plaintext; //FIX ME
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
