package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertConfigs {

	public Alert invalidStringKey = makeAlert(
			"Invalid Key",
			"Unable to perform encryption. Key must be a series of letters only.",
			AlertType.ERROR 
			);
			
	public Alert invalidIntKey = makeAlert(
			"Invalid Key", 
			"Unable to perform encryption. Key must be a positive number",
			AlertType.ERROR
			);
	
	public Alert invalidRailKey = makeAlert(
			"Invalid Key", 
			"Unable to perform encryption. Key must be a number greater than 1",
			AlertType.ERROR
			);
	
	public Alert invalidPlayfairKey = makeAlert(
			"Invalid Key",
			"Key cannot contain consecutive letters.",
			AlertType.ERROR
			);
	
	public Alert invalidPlainText = makeAlert(
			"Invalid Plain Text",
			"Plain Text can only include letters.",
			AlertType.ERROR
			);
	public Alert invalidPlayFairPText = makeAlert(
			"Invalid Plain Text",
			"Plain Text can only include letters witht the exception of J",
			AlertType.ERROR
			);
			
	
	public Alert makeAlert(String heading, String message, AlertType type) {
		Alert holder = new Alert(type);
		holder. setTitle(heading);
		holder.setContentText(message);
		
		return holder;
	}
}
