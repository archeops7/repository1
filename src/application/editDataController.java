package application;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
public class editDataController {
	@FXML private TextField fieldKm;
	@FXML private TextField fieldLitter;
	@FXML private TextField fieldPrice;
	@FXML private TextArea fieldMemo ;
	
	@FXML private Button buttonReturn ;
	@FXML private Button buttonDelete ;
	@FXML private Button buttonEdit ;
	@FXML private Label labelTotalKm;
	@FXML private Label labelTotalLitter;
	@FXML private Label labelMileage;
	@FXML private Label labelAveMileage;
	@FXML private Label labelCost;
	@FXML private Label labelTotalCost;
	public static double sqlKm =0;
	public static double sqlLitter =0;
	public static double sqlMileage =0;
	public static int sqlPrice =0;
	public static int sqlCost =0;
	public static String sqlMemo ="";
	
	@FXML public void initialize() {
		try (Connection db = DriverManager.getConnection(
	    		 "jdbc:mysql://localhost/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=JST", "root", "mysql")) {
	      PreparedStatement ps = db.prepareStatement("SELECT * FROM record where updated_at=(select max(updated_at) from record)");
	      
	      ResultSet rs = ps.executeQuery();
	      while (rs.next()) {
	    			
	    			fieldKm.setText(String.format("%.2f" ,rs.getDouble("km")));
	    			fieldLitter.setText(String.format("%.2f" ,rs.getDouble("litter")));
	    			fieldPrice.setText(String.valueOf(rs.getInt("price")));
	    			fieldMemo.setText(rs.getString("memo"));
	    			labelMileage.setText(String.format("%.2f" ,rs.getDouble("mileage")));
	    			labelCost.setText(String.valueOf(rs.getInt("cost")));
	    			labelTotalKm.setText(String.format("%.2f" ,rs.getDouble("totalKm")));
	    			labelTotalLitter.setText(String.format("%.2f" ,rs.getDouble("totalLitter")));
	    			labelAveMileage.setText(String.format("%.2f" ,rs.getDouble("totalMileage")));
	    			labelTotalCost.setText(String.valueOf(rs.getInt("totalCost")));
	    			
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	}
	

	@FXML public void buttonClickedEdit(Event eveEdit) {
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
		sql.delete();
		double mileageRaw = km / litter;
		BigDecimal mileageBD = new BigDecimal(mileageRaw);
		BigDecimal mileageHU = mileageBD.setScale(1, RoundingMode.HALF_UP);
		double mileage = mileageHU.doubleValue();
		int cost = (int)Math.abs(litter * price) ;
		
		sql.selectLatestData(null);
		sql.totalKm += km ;
		sql.totalLitter += litter;
		double avgMileageRaw = sql.totalKm / sql.totalLitter;
		BigDecimal avgMileageBD = new BigDecimal(avgMileageRaw);
		BigDecimal avgMileageHU = avgMileageBD.setScale(1, RoundingMode.HALF_UP); 
		sql.aveMileage =avgMileageHU.doubleValue();
		sql.totalCost += cost;
		
		sqlKm = km;
		sqlLitter = litter;
		sqlMileage = mileage;
		sqlPrice = price;
		sqlCost = cost;
		sqlMemo = memo;
		sql.addition(sqlKm, sql.totalKm, sqlLitter, sql.totalLitter, sqlMileage, sql.aveMileage, sqlPrice, sqlCost, sql.totalCost, sqlMemo);
		try {
			Scene re = ((Node)eveEdit.getSource()).getScene();
			Window window = re.getWindow();
			window.hide();
			Parent parent = FXMLLoader.load(getClass().getResource("editData.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("CheckEditData");
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}else {
		errorAddDataController.seteMsg(checkKm, checkLitter, checkPrice, checkMemo);
		Scene error = ((Node)eveEdit.getSource()).getScene();
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
	
	
	
	@FXML public void buttonClickedDelete(Event eveDelete) {
		try {
			Scene re = ((Node)eveDelete.getSource()).getScene();
			Window window = re.getWindow();
			window.hide();
			Parent parent = FXMLLoader.load(getClass().getResource("checkDelete.fxml"));
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
			Parent parent = FXMLLoader.load(getClass().getResource("recordMenu.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
