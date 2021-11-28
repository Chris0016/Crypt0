package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class HomepageController implements Initializable {

	@FXML
	private RadioButton ceasarOption, vigenreOption, railfenceOption, beufortOption, autokeyOption, playfairOption; 

	@FXML
	private TextField plaintext, encryptedtext;
	
	ArrayList<RadioButton> allOptions;
	private Encrypt crypter;
	private PlayFair playfairCipher;
	private AlertConfigs alertConfigs;
	private KeyInputDialogs keyInputDialogs;
	private String pTextHolder;
	private String keyHolder;
	private int intKeyHolder;
	private final int DEFAULTRAILFENCEKEY = 2;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		crypter = new Encrypt();
		playfairCipher = new PlayFair();
		alertConfigs = new AlertConfigs();
		keyInputDialogs = new KeyInputDialogs();
		
	}
	
	public HomepageController() {
		 
	}
	
	
	public void encrypt(ActionEvent e) {
		
		setPlaintextHolder();
		if(pTextHolder.equals("")  )
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
		else {
			runPlayfair();
		}
			
	
	}


	private void runCeasar() {

		keyInputDialogs.ceasarOffset.showAndWait();
		keyHolder =  keyInputDialogs.ceasarOffset.getResult();
		
		if(userCancelledEncryption()) 		
			return;
		System.out.println("Still running");
		
		int intOffset = 0;
		
		try {
				intOffset = Integer.parseInt(keyHolder);
		}
		catch(Exception e){
			if(e instanceof NumberFormatException)
				System.out.println("Invalid Key Input");
				alertConfigs.invalidIntKey.showAndWait();
				return;
		}

		setEncryptedTextDisplay(crypter.ceasar(pTextHolder, intOffset));
	}
	
	private void runBeufort() {
		keyInputDialogs.generalDialog.showAndWait();
		keyHolder = keyInputDialogs.generalDialog.getResult();
	
		if(userCancelledEncryption()) 
			return;
		
		if (isValidGeneralKey())
			setEncryptedTextDisplay(crypter.beufort(pTextHolder, keyHolder));
		else {
			System.out.println("Invalid Key for Beufort Cipher");
			alertConfigs.invalidStringKey.showAndWait();
		}
	}
	
	private void runVigenre() {
		keyInputDialogs.generalDialog.showAndWait();
		keyHolder = keyInputDialogs.generalDialog.getResult();
		
		if(userCancelledEncryption()) 
			return;
		
		if (isValidGeneralKey())
			setEncryptedTextDisplay(crypter.vigenere(pTextHolder, keyHolder));
		else {
			System.out.println("Invalid Key for Vigenre Cipher");
			alertConfigs.invalidStringKey.showAndWait();
		}
	}
	
	private void runAutokey() {
		keyInputDialogs.generalDialog.showAndWait();
		keyHolder = keyInputDialogs.generalDialog.getResult();
		
		if(userCancelledEncryption()) 
			return;
		
		if(isValidGeneralKey())
			setEncryptedTextDisplay(crypter.autokey(pTextHolder, keyHolder));
		else {
			System.out.println("Invalid Key for Auto-Key Cipher");
			alertConfigs.invalidStringKey.showAndWait();
		}
	}
	
	private void runRailfence() {
		keyInputDialogs.railfenceRails.showAndWait();
		keyHolder = keyInputDialogs.railfenceRails.getResult();
		
		if(userCancelledEncryption()) 
			return;
		
		int intKey = DEFAULTRAILFENCEKEY;
		
		try {
			
			
			intKey = Integer.parseInt(keyHolder);
			setEncryptedTextDisplay(crypter.railfence(pTextHolder, intKey));
		}
		catch(Exception e) {
			if(e.getMessage().equals("Invalid number of rails: Must be greater than 2.")) {
				System.out.println(e.getMessage());
				alertConfigs.invalidIntKey.showAndWait();
			}
			else {
				System.out.println("Invalid Key for Railfence Cipher");
				alertConfigs.invalidRailKey.showAndWait();
			}
				
		}
				
	}
	
	private void runPlayfair() {
		keyInputDialogs.generalDialog.showAndWait();
		keyHolder = keyInputDialogs.generalDialog.getResult();
	
		if(userCancelledEncryption()) 
			return;
		
		try {
		if(isValidGeneralKey() )
			setEncryptedTextDisplay(playfairCipher.encrypt(pTextHolder, keyHolder));
		else 
			alertConfigs.invalidStringKey.showAndWait();
		
		}catch(Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);
			
			if (e instanceof IllegalArgumentException)
				if (msg.equals("Invalid plaintext: Contains letter J"))
					alertConfigs.invalidPlayFairPText.showAndWait();
				else
					alertConfigs.invalidPlayfairKey.showAndWait();
				
			
		}
		
	}
	
	
	private boolean userCancelledEncryption() {
		return this.keyHolder == null;
	}
	
	private boolean isValidGeneralKey() {
		return keyHolder != null && keyHolder.matches("[a-zA-Z]+") ;
	}

	private void setPlaintextHolder(){

		try{
			if(this.isValidPlaintext())
				this.pTextHolder =  plaintext.getText();
		}
		catch(Exception e){
			pTextHolder = "";
			System.out.println("Invalid plaintext given.");
			alertConfigs.invalidPlainText.showAndWait();
			
		}
	}

	private boolean isValidPlaintext() throws IllegalArgumentException{
		if( plaintext == null || plaintext.getText().equals(""))
			throw new IllegalArgumentException("Input Is Empty");
		
		//Throw different exceptions for examples where the plaintext contains a number. 
		return true;
	}	
	
	private void setEncryptedTextDisplay(String eText) {
		encryptedtext.setText(eText);
	}
	
	
}
