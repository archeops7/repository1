package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class showLatestDataController implements Initializable {
	@FXML private Button buttonReturn;
	@FXML private TableView<data1> tableLatestData1;
	@FXML private TableView<data1> tableLatestData2;
	@FXML private TableView<data1> tableLatestData3;
	@FXML private TableColumn<data1, Double> km;
	@FXML private TableColumn<data1, Double> totalKm;
	@FXML private TableColumn<data1, Double> litter;
	@FXML private TableColumn<data1, Double> totalLitter;
	@FXML private TableColumn<data1, Double> mileage;
	@FXML private TableColumn<data1, Double> aveMileage;
	@FXML private TableColumn<data1, Integer> price;
	@FXML private TableColumn<data1, Integer> cost;
	@FXML private TableColumn<data1, Integer> totalCost;
	@FXML private TableColumn<data1, String> memo;
	@FXML private TableColumn<data1, String> date;
	private ObservableList<data1> data1;
	
	@Override
	  public void initialize(java.net.URL url, java.util.ResourceBundle bundle) {
	    data1 = FXCollections.observableArrayList();
	    tableLatestData1.itemsProperty().setValue(data1);
	    tableLatestData1.setItems(data1);
	    tableLatestData2.itemsProperty().setValue(data1);
	    tableLatestData2.setItems(data1);
	    tableLatestData3.itemsProperty().setValue(data1);
	    tableLatestData3.setItems(data1);
	    date.setCellValueFactory(new PropertyValueFactory<data1, String>("date"));
	    km.setCellValueFactory(new PropertyValueFactory<data1, Double>("km"));
	    totalKm.setCellValueFactory(new PropertyValueFactory<data1, Double>("totalKm"));
	    litter.setCellValueFactory(new PropertyValueFactory<data1, Double>("litter"));
	    totalLitter.setCellValueFactory(new PropertyValueFactory<data1, Double>("totalLitter"));
	    mileage.setCellValueFactory(new PropertyValueFactory<data1, Double>("mileage"));
	    aveMileage.setCellValueFactory(new PropertyValueFactory<data1, Double>("aveMileage"));
	    price.setCellValueFactory(new PropertyValueFactory<data1, Integer>("price"));
	    cost.setCellValueFactory(new PropertyValueFactory<data1, Integer>("cost"));
	    totalCost.setCellValueFactory(new PropertyValueFactory<data1, Integer>("totalCost"));
	    memo.setCellValueFactory(new PropertyValueFactory<data1, String>("memo"));
	    
	    
	    try (Connection db = DriverManager.getConnection(
	    		 "jdbc:mysql://localhost/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=JST", "root", "mysql")) {
		      
	      PreparedStatement ps = db.prepareStatement("SELECT * FROM record where updated_at=(select max(updated_at) from record)");
	      ResultSet rs = ps.executeQuery();
	      
	      while (rs.next()) {
	    	 data1.add(new data1 (new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("updated_at")) ,rs.getDouble("km") ,rs.getDouble("totalKm") ,rs.getDouble("litter") ,rs.getDouble("totalLitter") ,rs.getDouble("mileage") ,rs.getDouble("totalMileage") ,rs.getInt("price") ,rs.getInt("cost") ,rs.getInt("totalCost") ,rs.getString("memo")));
	    	 tableLatestData1.setItems(data1);
	      }
	      while (rs.next()) {
		    	 data1.add(new data1 (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rs.getDate("updated_at")) ,rs.getDouble("km") ,rs.getDouble("totalKm") ,rs.getDouble("litter") ,rs.getDouble("totalLitter") ,rs.getDouble("mileage") ,rs.getDouble("totalMileage") ,rs.getInt("price") ,rs.getInt("cost") ,rs.getInt("totalCost") ,rs.getString("memo")));
		    	 tableLatestData2.setItems(data1);
		      }
	      while (rs.next()) {
		    	 data1.add(new data1 (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rs.getDate("updated_at")) ,rs.getDouble("km") ,rs.getDouble("totalKm") ,rs.getDouble("litter") ,rs.getDouble("totalLitter") ,rs.getDouble("mileage") ,rs.getDouble("totalMileage") ,rs.getInt("price") ,rs.getInt("cost") ,rs.getInt("totalCost") ,rs.getString("memo")));
		    	 tableLatestData3.setItems(data1);
		      }
	      ps.close();
        rs.close();
	    } catch (SQLException e) {
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


