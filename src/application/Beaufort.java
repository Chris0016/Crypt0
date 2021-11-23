package application;

public class Beaufort{
	private String plaintext;
	private String key;
	private String encryptedText;
	final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int pTextLength, kLength;


	public Beaufort(String plaintext, String key){
		this.plaintext = plaintext.toUpperCase();
		this.key = key;
		this.encryptedText = "";
		this.pTextLength = plaintext.length();
		this.kLength = key.length();
	}

	public void encrypt(){
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
	}

	//--------------Getters and Setters------------

	public String getPlaintext(){
		return this.plaintext;
	}

	public String getKey(){
		return this.key;
	}

	public String getEncryptedText(){
		return this.encryptedText;
	}

	public void setPlaintext(String plaintext){
		this.plaintext = plaintext.toUpperCase();;
		this.pTextLength = plaintext.length();
		this.encryptedText = "";
	}

	public void setKey(String key){
		this.key = key;
		this.kLength = key.length();
	}



}