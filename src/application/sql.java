package application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
public class sql {
	public static double km =0;
	public static double litter =0;
	public static double mileage =0;
	public static double totalKm =0;
	public static double totalLitter = 0;
	public static double aveMileage = 0;
	public static int price = 0;
	public static int cost = 0;
	public static int totalCost = 0;
	public static String memo = "";
	public static String date = "";
	public static int num = 0;
	
	  public static void addition(double km,double totalKm,double litter,double totalLitter,double mileage,double avgMileage,int price,int cost,int totalCost,String memo) {
		    try (Connection db = DriverManager.getConnection(
		    		 "jdbc:mysql://localhost/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=JST", "root", "mysql")) {
		      PreparedStatement ps = db.prepareStatement(
		        "INSERT INTO record (km,totalKm,litter,totalLitter,mileage,totalMileage,price,cost,totalCost,memo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		      ps.setDouble(1, km);
		      ps.setDouble(2, totalKm);
		      ps.setDouble(3, litter);
		      ps.setDouble(4, totalLitter);
		      ps.setDouble(5, mileage);
		      ps.setDouble(6, avgMileage);
		      ps.setInt(7, price);
		      ps.setInt(8, cost);
		      ps.setInt(9, totalCost);
		      ps.setString(10, memo);
		      int result = ps.executeUpdate();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  public static void select(String[] args) {
		    try (Connection db = DriverManager.getConnection(
		    		 "jdbc:mysql://localhost/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=JST", "root", "mysql")) {
		      PreparedStatement ps = db.prepareStatement("SELECT * FROM record");
		      
		      ResultSet rs = ps.executeQuery();
		      while (rs.next()) {
		    	  km = rs.getDouble("km");
		    	  litter = rs.getDouble("litter");
		    	  mileage = rs.getDouble("mileage");
		    	  price = rs.getInt("price");
		    	  cost = rs.getInt("cost");
		    	  totalKm = rs.getDouble("totalKm");
			      totalLitter = rs.getDouble("totalLitter");
			      aveMileage = rs.getDouble("totalMileage");
			      totalCost = rs.getInt("totalCost");
			      memo = rs.getString("memo");
			      date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rs.getDate("updated_at"));
		    	    System.out.println("更新日は" + rs.getDate("updated_at"));
		    	    System.out.println("走行距離は" + rs.getDouble("km") +"kmです。");
		    	    System.out.println("給油量は" + rs.getDouble("litter") +"Lです。");
		    	    System.out.println("燃費は" + rs.getDouble("mileage") +"km/Lです。");
		    	    System.out.println("ガソリン単価は" + rs.getInt("price") +"円/Lです。");
		    	    System.out.println("ガソリン費は" + rs.getInt("cost") +"円です。");
		    	    System.out.println("累計走行距離は" + rs.getDouble("totalKm") +"kmです。");
			        System.out.println("累計給油量は" + rs.getDouble("totalLitter") + "Lです。");
			        System.out.println("平均燃費は" + rs.getDouble("totalMileage") + "km/Lです。");
			        System.out.println("累計ガソリン費は" + rs.getInt("totalCost") + "円です。");
			        System.out.println("メモは「" + rs.getString("memo") +"」です。");
		            System.out.println("---------------------------------");
		            getKm();
		            getLitter();
		            getMileage();
		            getTotalKm();
		            getTotalLitter();
		            getAveMileage();
		            getPrice();
		            getCost();
		            getTotalCost();
		            getMemo();
		            getDate();
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  public static void selectLatestData(String[] args) {
		    try (Connection db = DriverManager.getConnection(
		    		 "jdbc:mysql://localhost/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=JST", "root", "mysql")) {
		      PreparedStatement ps = db.prepareStatement("SELECT totalKm ,totalLitter ,totalMileage ,totalCost ,updated_at FROM record where updated_at=(select max(updated_at) from record)");
		      
		      ResultSet rs = ps.executeQuery();
		      while (rs.next()) {
		        totalKm = rs.getDouble("totalKm");
		        totalLitter = rs.getDouble("totalLitter");
		        aveMileage = rs.getDouble("totalMileage");
		        totalCost = rs.getInt("totalCost");
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  
	  public static void delete() {
		    try (Connection db = DriverManager.getConnection(
		    		 "jdbc:mysql://localhost/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=JST", "root", "mysql")) {
		      PreparedStatement ps = db.prepareStatement("SELECT id FROM record where updated_at=(select max(updated_at) from record)");
		      ResultSet rs = ps.executeQuery();
		      while (rs.next()) {
		    	  num = rs.getInt("id");
		      }
		      
		    } catch (SQLException e) {
			      e.printStackTrace();
		    }
		    String sql = "DELETE FROM record WHERE id =" + num;
		    try (Connection db = DriverManager.getConnection(
		    		 "jdbc:mysql://localhost/record?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=JST", "root", "mysql")) {
		      PreparedStatement ps2 = db.prepareStatement(sql);
		      int result = ps2.executeUpdate();
		     
		    } catch (SQLException e) {
			      e.printStackTrace();
		    }
	  }
	  
	  
	  public static double getKm() {
		  return km;
	  }
	  public static double getLitter() {
		  return litter;
	  }
	  public static double getMileage() {
		  return mileage;
	  }
	  public static double getTotalKm() {
		  return totalKm;
	  }
	  public static double getTotalLitter() {
		  return totalLitter;
	  }
	  public static double getAveMileage() {
		  return aveMileage;
	  }
	  public static int getPrice() {
		  return price;
	  }
	  public static int getCost() {
		  return cost;
	  }
	  public static int getTotalCost() {
		  return totalCost;
	  }
	  public static String getMemo() {
		  return memo;
	  }
	  public static String getDate() {
		  return date;
	  }

}


