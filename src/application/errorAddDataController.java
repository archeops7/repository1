package application;
import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
public class errorAddDataController {
	@FXML private Button buttonReturn;
	@FXML private Label errorMSG;
	public static String eKm ="";
	public static String eLitter ="";
	public static String ePrice ="";
	public static String eMemo ="";
	
	public static void seteMsg(String km ,String litter ,String price ,String memo) {
		if(km == "-") {
			eKm = " ";
		}else {
			eKm = " " + km;
		}
		
		if(litter == "-") {
			eLitter = " ";
		}else {
			eLitter = " " + litter;
		}
		
		if(price == "-") {
			ePrice = " ";
		}else {
			ePrice = " " + price;
		}
		
		if(memo == "-") {
			eMemo = " ";
		}else {
			eMemo = " " + memo;
		}
	}
	
	public void initialize() {
		errorMSG.setText(eKm + eLitter + ePrice + eMemo);
	}
	
	@FXML public void buttonClickedReturn(Event eveReturn) {
		Scene re = ((Node)eveReturn.getSource()).getScene();
		Window window = re.getWindow();
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
}
