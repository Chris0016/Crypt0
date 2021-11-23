package application;

public class AutoKey{
	private String plaintext;
	private String key;
	private String encryptedText;
	final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int kLength;
	private int pTextLength;

	public AutoKey(String plaintext, String key){

		this.plaintext = plaintext.toUpperCase().trim();
		this.key = key.toUpperCase().trim();
		this.encryptedText = "";

		this.pTextLength = plaintext.length();
		this.kLength = key.length();
	
	}


	public void encrypt(){

		String runningKey = key +  plaintext;
		runningKey = runningKey.substring(0, pTextLength);

		int offset, pos;

		for(int i = 0; i < pTextLength; i++){
			offset = plaintext.charAt(i) - 65;

			pos =  (ALPHABET.indexOf(runningKey.charAt(i)) + offset) % 25;

			encryptedText += ALPHABET.charAt(pos);

		}	


	}


	//--------------Getters and Setters------------

	public String getPlaintext(){
		return this.plaintext;
	}

	public String getKey(){
		return  this.key;
	}

	public String getEncryptedText(){
		return this.encryptedText;
	}

	public void setPlaintext(String plaintext){
		this.plaintext = plaintext.toUpperCase().trim();
		this.pTextLength = plaintext.length();

		this.encryptedText = "";
	}

	public void setKey(String key){
		this.key = key.toUpperCase().trim();
	}




}