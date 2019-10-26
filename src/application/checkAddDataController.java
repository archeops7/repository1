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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
public class checkAddDataController {
	@FXML private Label label_km;
	@FXML private Label label_litter;
	@FXML private Label label_mileage;
	@FXML private Label label_price;
	@FXML private Label label_cost;
	@FXML private Label label_totalKm;
	@FXML private Label label_totalLitter;
	@FXML private Label label_aveMileage;
	@FXML private Label label_totalCost;
	@FXML private Label label_memo;
	@FXML private Button buttonAdd;
	@FXML private Button buttonReturn;
	
	public static double sqlKm =0;
	public static double sqlLitter =0;
	public static double sqlMileage =0;
	public static int sqlPrice =0;
	public static int sqlCost =0;
	public static String sqlMemo ="";
	
	public static void addData(double km ,double litter ,int price ,String memo) {
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
	}

		
		public void initialize() {
		
		label_km.setText(String.format("%.2f" ,sqlKm));
		label_litter.setText(String.format("%.2f" ,sqlLitter));
		label_mileage.setText(String.format("%.2f" ,sqlMileage));
		label_price.setText(String.valueOf(sqlPrice));
		label_cost.setText(String.valueOf(sqlCost));
		label_totalKm.setText(String.format("%.2f" ,sql.totalKm));
		label_totalLitter.setText(String.format("%.2f" ,sql.totalLitter));
		label_aveMileage.setText(String.format("%.2f" ,sql.aveMileage));
		label_totalCost.setText(String.valueOf(sql.totalCost));
		label_memo.setText(sqlMemo);
		
	}
	
	@FXML public void buttonClickedAdd(Event eveAdd) {
		sql.addition(sqlKm, sql.totalKm, sqlLitter, sql.totalLitter, sqlMileage, sql.aveMileage, sqlPrice, sqlCost, sql.totalCost, sqlMemo);
		Scene s = ((Node)eveAdd.getSource()).getScene();
		Window window = s.getWindow();
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
	@FXML public void buttonClickedReturn(Event eveReturn) {
		Scene re = ((Node)eveReturn.getSource()).getScene();
		Window window = re.getWindow();
		window.hide();
		try {
				Parent parent = FXMLLoader.load(getClass().getResource("addData.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("NewData");
				stage.show();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
}
