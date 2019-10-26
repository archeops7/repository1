package application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class data {
	private DoubleProperty km;
	private DoubleProperty litter;
	private DoubleProperty mileage;
	private DoubleProperty totalKm;
	private DoubleProperty totalLitter ;
	private DoubleProperty aveMileage ;
	private IntegerProperty price ;
	private IntegerProperty cost ;
	private IntegerProperty totalCost ;
	private StringProperty memo ;
	private StringProperty date ;
	
	public data(double km, double totalKm, double litter, double totalLitter, double mileage, double aveMileage, int price, int cost, int totalCost, String memo, String date) {
		this.km = new SimpleDoubleProperty(km);
		this.totalKm = new SimpleDoubleProperty(totalKm);
		this.litter = new SimpleDoubleProperty(litter);
		this.totalLitter = new SimpleDoubleProperty(totalLitter);
		this.mileage = new SimpleDoubleProperty(mileage);
		this.aveMileage = new SimpleDoubleProperty(aveMileage);
		this.price = new SimpleIntegerProperty(price);
		this.cost = new SimpleIntegerProperty(cost);
		this.totalCost = new SimpleIntegerProperty(totalCost);
		this.memo = new SimpleStringProperty(memo);
		this.date = new SimpleStringProperty(date);
	}
	
	public DoubleProperty kmProperty() {
	 return km;
	}
	 public DoubleProperty totalKmProperty() {
		 return totalKm;
	 }
	 public DoubleProperty litterProperty() {
		 return litter;
	 }
	 public DoubleProperty totalLitterProperty() {
		 return totalLitter;
	 }
	 public DoubleProperty mileageProperty() {
		 return mileage;
	 }
	 public DoubleProperty aveMileageProperty() {
		 return aveMileage;
	 }
	 public IntegerProperty priceProperty() {
		 return price;
	 }
	 public IntegerProperty costProperty() {
		 return cost;
	 }
	 public IntegerProperty totalCostProperty() {
		 return totalCost;
	 }
	 public StringProperty memoProperty() {
		 return memo;
	 }
	 public StringProperty dateProperty() {
		 return date;
	 }
	  
	  public void setKm(double km) {
		  this.km = new SimpleDoubleProperty(km);
	  }
	  public void setTotalKm(double totalKm) {
		  this.totalKm = new SimpleDoubleProperty(totalKm);
	  }
	  public void setLitter(double litter) {
		  this.litter = new SimpleDoubleProperty(litter);
	  }
	  public void setTotalLitter(double totalLitter) {
		  this.totalLitter = new SimpleDoubleProperty(totalLitter);
	  }
	  public void setMileage(double mileage) {
		  this.mileage = new SimpleDoubleProperty(mileage);
	  }
	  public void setAveMileage(double aveMileage) {
		  this.aveMileage = new SimpleDoubleProperty(aveMileage);
	  }
	  public void setPrice(int price) {
		  this.price = new SimpleIntegerProperty(price);
	  }
	  public void setCost(int cost) {
		  this.cost = new SimpleIntegerProperty(cost);
	  }
	  public void setTotalCost(int totalCost) {
		  this.totalCost = new SimpleIntegerProperty(totalCost);
	  }
	  public void setMemo(String memo) {
		  this.memo = new SimpleStringProperty(memo);
	  }
	  public void setDate(String date ) {
		  this.date = new SimpleStringProperty(date);
	  }

}
