package application;
import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainMenuController {
	 @FXML private Button button_New;
	    @FXML private Button button_Record;
	    @FXML private Button button_Exit;

	    @FXML public void buttonClickedNew(Event eve1) {
	    	Scene s = ((Node)eve1.getSource()).getScene();
	    	Window window = s.getWindow();
	    	window.hide();
	    	try {
				Parent parent = FXMLLoader.load(getClass().getResource("addData.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("addData");
				stage.show();
			}catch(IOException e) {
				e.printStackTrace();
			}
	    }
	    	 @FXML public void buttonClickedRecord(Event eve2) {
	    		 Scene s2 = ((Node)eve2.getSource()).getScene();
	 	    	Window window2 = s2.getWindow();
	 	    	window2.hide();
	 	    	try {
	 				Parent parent = FXMLLoader.load(getClass().getResource("recordMenu.fxml"));
	 				Scene scene = new Scene(parent);
	 				Stage stage = new Stage();
	 				stage.setScene(scene);
	 				stage.setTitle("recordMenu");
	 				stage.show();
	 			}catch(IOException e) {
	 				e.printStackTrace();
	 			}
	    		 
	    	 }
	    	 @FXML public void buttonClickedExit(Event eve3) {
	    		 Scene s2 = ((Node)eve3.getSource()).getScene();
	 	    	Window window2 = s2.getWindow();
	 	    	window2.hide();
	 	    	
	    		 
	    	 }


	    
}
