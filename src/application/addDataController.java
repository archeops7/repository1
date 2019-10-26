package application;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
public class addDataController {
	@FXML private TextField fieldKm;
	@FXML private TextField fieldLitter;
	@FXML private TextField fieldPrice;
	@FXML private TextArea fieldMemo ;
	@FXML private Button buttonCheck ;
	@FXML private Button buttonReturn ;
	
	@FXML public void buttonClickedCheck(Event eveNew){
	
		String checkKm = "";
		String checkLitter = "";
		String checkPrice = "";
		String checkMemo = "";
		double km = 0;
		double litter = 0;
		int price = 0;
		String memo = "";
	
	while(true) {
		try {
			while(true) {
				
				if(Double.parseDouble(fieldKm.getText()) >0 ) {
					double kmRaw = Double.parseDouble(fieldKm.getText());
					BigDecimal KmBD = new BigDecimal(kmRaw);
					BigDecimal KmHU = KmBD.setScale(1, RoundingMode.HALF_UP);
					km = KmHU.doubleValue();
					checkKm = "-";
					break;
				}else {
					checkKm = "走行距離"; 
					break;
				}
			}
			break;
		}catch (Exception e){ 
			checkKm = "走行距離";
			System.out.println(e);
			break;
		}
	}
	
	while(true) {
		try {
			while(true) {
				
				if(Double.parseDouble(fieldLitter.getText()) >0 ) {
					double litterRaw = Double.parseDouble(fieldLitter.getText());
					BigDecimal litterBD = new BigDecimal(litterRaw);
					BigDecimal litterHU = litterBD.setScale(1, RoundingMode.HALF_UP);
					litter = litterHU.doubleValue();
					checkLitter = "-";
					break;
				}else {
					checkLitter = "給油量"; 
					break;
				}
			}
			break;
		}catch (Exception e){ 
			checkLitter = "給油量";
			System.out.println(e);
			break;
		}
	}
	
	while(true) {
		try {
			while(true) {
				
				if(Integer.parseInt(fieldPrice.getText()) >0 ) {
					price = Integer.parseInt(fieldPrice.getText());
					checkPrice = "-";
					break;
				}else {
					checkPrice = "ガソリン単価";
					break;
				}
			}
			break;
		}catch (Exception e){ 
			checkPrice = "ガソリン単価"; 
			System.out.println(e);
			break;
		}
	}
	
	while(true) {
		try {
			while(true) {
				memo = fieldMemo.getText();
				if(memo.length() <= 100 ) {
					checkMemo = "-";
					break;
				}else {
					checkMemo = "メモ"; 
					break;
				}
			}
			break;
		}catch (Exception e){ 
			checkMemo = "メモ"; 
			System.out.println(e);
			break;
		}
	}
	
	if(checkKm == "-" && checkLitter == "-" && checkPrice == "-" && checkMemo == "-"){
		//SampleController3.addDateに引数で値パス、そっちでSQL処理も
		checkAddDataController.addData(km ,litter ,price ,memo);
		Scene ok = ((Node)eveNew.getSource()).getScene();
    	Window window = ok.getWindow();
    	window.hide();
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("checkAddData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("CheckNewData");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}else {
		errorAddDataController.seteMsg(checkKm, checkLitter, checkPrice, checkMemo);
		Scene error = ((Node)eveNew.getSource()).getScene();
    	Window window = error.getWindow();
    	window.hide();
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("errorAddData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("CheckError");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	}
	@FXML public void buttonClickedReturn(Event eveRe){
		Scene second = ((Node)eveRe.getSource()).getScene();
		Window window = second.getWindow();
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
