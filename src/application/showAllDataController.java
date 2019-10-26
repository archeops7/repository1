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

public class showAllDataController implements Initializable {
	@FXML private Button buttonReturn;
	@FXML private TableView<data> TableAllData;
	@FXML private TableColumn<data, Double> km;
	@FXML private TableColumn<data, Double> totalKm;
	@FXML private TableColumn<data, Double> litter;
	@FXML private TableColumn<data, Double> totalLitter;
	@FXML private TableColumn<data, Double> mileage;
	@FXML private TableColumn<data, Double> aveMileage;
	@FXML private TableColumn<data, Integer> price;
	@FXML private TableColumn<data, Integer> cost;
	@FXML private TableColumn<data, Integer> totalCost;
	@FXML private TableColumn<data, String> memo;
	@FXML private TableColumn<data, String> date;
	private ObservableList<data> data;
	
	@Override
	  public void initialize(java.net.URL url, java.util.ResourceBundle bundle) {
	    data = FXCollections.observableArrayList();
	    TableAllData.itemsProperty().setValue(data);
	    TableAllData.setItems(data);
	    
	    km.setCellValueFactory(new PropertyValueFactory<data, Double>("km"));
	    totalKm.setCellValueFactory(new PropertyValueFactory<data, Double>("totalKm"));
	    litter.setCellValueFactory(new PropertyValueFactory<data, Double>("litter"));
	    totalLitter.setCellValueFactory(new PropertyValueFactory<data, Double>("totalLitter"));
	    mileage.setCellValueFactory(new PropertyValueFactory<data, Double>("mileage"));
	    aveMileage.setCellValueFactory(new PropertyValueFactory<data, Double>("aveMileage"));
	    price.setCellValueFactory(new PropertyValueFactory<data, Integer>("price"));
	    cost.setCellValueFactory(new PropertyValueFactory<data, Integer>("cost"));
	    totalCost.setCellValueFactory(new PropertyValueFactory<data, Integer>("totalCost"));
	    memo.setCellValueFactory(new PropertyValueFactory<data, String>("memo"));
	    date.setCellValueFactory(new PropertyValueFactory<data, String>("date"));
	    
	    try (Connection db = DriverManager.getConnection(
	    		 "jdbc:mysql://localhost/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=JST", "root", "mysql")) {
		      
	      PreparedStatement ps = db.prepareStatement("SELECT * FROM record");
	      ResultSet rs = ps.executeQuery();
	      
	      while (rs.next()) {
	    	 data.add( new data(rs.getDouble("km") ,rs.getDouble("totalKm") ,rs.getDouble("litter") ,rs.getDouble("totalLitter") ,rs.getDouble("mileage") ,rs.getDouble("totalMileage") ,rs.getInt("price") ,rs.getInt("cost") ,rs.getInt("totalCost") ,rs.getString("memo") ,new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("updated_at")) ));
	    	 TableAllData.setItems(data);
	      }  ps.close();
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
