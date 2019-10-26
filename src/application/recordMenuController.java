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

public class recordMenuController {
	@FXML private Button buttonReturn;
	@FXML private Button buttonAllData;
	@FXML private Button buttonLatestData;
	@FXML private Button buttonSerchMemo;
	@FXML private Button buttonEditData;
	
	@FXML public void buttonClickedAllData(Event eveAllData) {
		Scene all = ((Node)eveAllData.getSource()).getScene();
		Window window = all.getWindow();
		window.hide();
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("showAllData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void buttonClickedLatestData(Event eveLatestData) {
		Scene la = ((Node)eveLatestData.getSource()).getScene();
		Window window = la.getWindow();
		window.hide();
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("showLatestData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
		}
	
	@FXML public void buttonClickedSerchMemo(Event eveSerchMemo) {
		Scene se = ((Node)eveSerchMemo.getSource()).getScene();
		Window window = se.getWindow();
		window.hide();
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("SerchData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
		}
	
	@FXML public void buttonClickedEditData(Event eveEditData) {
		Scene ed = ((Node)eveEditData.getSource()).getScene();
		Window window = ed.getWindow();
		window.hide();
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("editData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
		}
	
	@FXML public void buttonClickedReturn(Event eveReturn) {
		Scene re = ((Node)eveReturn.getSource()).getScene();
		Window window = re.getWindow();
		window.hide();
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
