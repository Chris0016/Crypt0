package application;

import javafx.scene.control.TextInputDialog;

public class KeyInputDialogs {

	
	public TextInputDialog ceasarOffset = makeInputDialog( 
			"Ceasar Offset", 
			"Input the offset number for encryption",
			"Offset : "
			);
	public TextInputDialog railfenceRails = makeInputDialog(
			"Railfence Rails", 
			"Input the number of rails used for RailFence Encryption.",
			"Rails: "
			);
	public TextInputDialog generalDialog = makeInputDialog(
		"Cipher Key",
		"Input a set of letters for the cipher" ,
		"Key:"
		);


		
	

	public TextInputDialog makeInputDialog(String title, String message, String label) {
		TextInputDialog holder = new TextInputDialog();
		holder.setTitle(title);
		holder.setHeaderText(message);
		holder.setContentText(label);
		return holder;
	}
}
