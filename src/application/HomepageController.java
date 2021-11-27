package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;


public class HomepageController implements Initializable {

	@FXML
	private RadioButton ceasarOption, vigenreOption, railfenceOption, beufortOption, autokeyOption, playfairOption; 

	@FXML
	private TextField plaintext, encryptedtext;
	
	ArrayList<RadioButton> allOptions;
	private Encrypt crypter;
	private PlayFair playfairCipher;
	//TextInputDialog ceasarOffset, railfenceRails, regularStringKey, playfairKey;
	KeyInputDialogs keyInputDialogs;
	private String pTextHolder;
	private String keyHolder;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		crypter = new Encrypt();
		playfairCipher = new PlayFair();
		keyInputDialogs = new KeyInputDialogs();
		
		/*
		allOptions.add(ceasarOption);
		allOptions.add(beufortOption);
		allOptions.add(railfenceOption);
		allOptions.add(vigenreOption);
		allOptions.add(autokeyOption);
		allOptions.add(playfairOption);
				*/
	}
	
	public HomepageController() {
		 
	}
	
	public void encrypt(ActionEvent e) {
		
		setPlaintextHolder();
		if(pTextHolder.equals(""))
			return;

		if (ceasarOption.isSelected())
			runCeasar();
		else if (beufortOption.isSelected())
			runBeufort();
	
		else if (vigenreOption.isSelected())
			runVigenre();
		else if (autokeyOption.isSelected())
			runAutokey();
		else if (railfenceOption.isSelected())
			runRailfence();
		else 
			runPlayfair();
	
	}
	
	private boolean isValidGeneralKey() {
		return keyHolder.matches("[a-zA-Z]+");
	}
	

	private void setPlaintextHolder(){

		try{
			if(this.isValidPlaintext())
				this.pTextHolder =  plaintext.getText();
		}
		catch(Exception e){
			pTextHolder = "";
			System.out.println("Invalid plaintext given.");
			//Do instance of for the different cases. 
		}
	}

	
	
	private void setEncryptedTextDisplay(String eText) {
		encryptedtext.setText(eText);
	}

	private boolean isValidPlaintext() throws IllegalArgumentException{
		if( plaintext.getText().equals(""))
			throw new IllegalArgumentException("Input Is Empty");
		
		//Throw different exceptions for examples where the plaintext contains a number. 
		return true;
	}	

	private void runCeasar() {

		keyInputDialogs.ceasarOffset.showAndWait();
		String offset =  keyInputDialogs.ceasarOffset.getResult();
		int intOffset = 0;
		
		try {
			intOffset = Integer.parseInt(offset);
		}
		catch(Exception e){
			if(e instanceof NumberFormatException)
				System.out.println("Invalid Key Input");	
		}

		setEncryptedTextDisplay(crypter.ceasar(pTextHolder, intOffset));
	}
	
	private void runBeufort() {
		keyInputDialogs.generalDialog.showAndWait();
		keyHolder = keyInputDialogs.generalDialog.getResult();
	
		
		if (isValidGeneralKey())
			setEncryptedTextDisplay(crypter.beufort(pTextHolder, keyHolder));
		else
			System.out.println("Invalid Key for Beufort Cipher");
	}
	
	private void runVigenre() {
		keyInputDialogs.generalDialog.showAndWait();
		keyHolder = keyInputDialogs.generalDialog.getResult();
		
		
		if (isValidGeneralKey())
			setEncryptedTextDisplay(crypter.vigenere(pTextHolder, keyHolder));
		else
			System.out.println("Invalid Key for Vigenre Cipher");
		
	}
	
	private void runAutokey() {
		keyInputDialogs.generalDialog.showAndWait();
		keyHolder = keyInputDialogs.generalDialog.getResult();
		
		if(isValidGeneralKey())
			setEncryptedTextDisplay(crypter.autokey(pTextHolder, keyHolder));
		else
			System.out.println("Invalid Key for Auto-Key Cipher");
	}
	
	private void runRailfence() {
		keyInputDialogs.railfenceRails.showAndWait();
		keyHolder = keyInputDialogs.railfenceRails.getResult();
		int intKey = 2;
		
		try {
			intKey = Integer.parseInt(keyHolder);
			setEncryptedTextDisplay(crypter.railfence(pTextHolder, intKey));
		}
		catch(Exception e) {
			if(e.getMessage().equals("Invalid number of rails: Must be greater than 2."))
				System.out.println(e.getMessage());
			else
				System.out.println("Invalid Key for Railfence Cipher");
		}
				
	}
	
	private void runPlayfair() {
		keyInputDialogs.generalDialog.showAndWait();
		keyHolder = keyInputDialogs.generalDialog.getResult();
	
		try {
		if(isValidGeneralKey() && !keyHolder.toUpperCase().contains("J"))
			setEncryptedTextDisplay(playfairCipher.encrypt(pTextHolder, keyHolder));
		
		}catch(Exception e) {
			//System.out.println("Invalid Key for Playfair Cipher. Must not contain the letter J");
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		
	}
	
}
