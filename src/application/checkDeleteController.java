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

public class checkDeleteController {
	@FXML private Button buttonReturn ;
	@FXML private Button buttonEnter ;
	
	@FXML public void buttonClickedEnter(Event eveReturn) {
		sql.delete();
		try {
			Scene re = ((Node)eveReturn.getSource()).getScene();
			Window window = re.getWindow();
			window.hide();
			Parent parent;
			parent = FXMLLoader.load(getClass().getResource("editData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	@FXML public void buttonClickedReturn(Event eveReturn) {
		try {
			Scene re = ((Node)eveReturn.getSource()).getScene();
			Window window = re.getWindow();
			window.hide();
			Parent parent;
			parent = FXMLLoader.load(getClass().getResource("editData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
}
