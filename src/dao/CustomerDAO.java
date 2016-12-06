package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.webService.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.DealerPojo;
import model.Engine;
import model.Transmission;
import model.Vehicle;

public class CustomerDAO {
	Connection conn=null;
	PreparedStatement preparedStatement=null, preparedStatement1=null;
	ResultSet resultSet=null;
	ResultSet resultSet1=null;
	ArrayList<DealerPojo> vehicleList=null;
	public ResultSet viewVechicles()
	{
		conn=DatabaseConnection.getConnection();
		try
		{
			String sql="select DISTINCT car_name from chevy;";
			preparedStatement=conn.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return resultSet;
		
	}
	public ResultSet getStyle(String s) {
		// TODO Auto-generated method stub
		conn=DatabaseConnection.getConnection();
		try
		{
			String sql="select distinct car_style from chevy where car_name=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, s);
			resultSet=preparedStatement.executeQuery();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return resultSet;
		
	}
	public ResultSet getYear(String s) {
		// TODO Auto-generated method stub
		conn=DatabaseConnection.getConnection();
		try
		{
			String sql="select distinct production_year from chevy where car_style=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, s);
			resultSet=preparedStatement.executeQuery();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return resultSet;
	}
	public Engine getEngineDetails(Vehicle vehicle) {
		// TODO Auto-generated method stub
		conn=DatabaseConnection.getConnection();
		Engine engine=new Engine();
		try{
		
			String sql="select style_id from chevy where car_name=? and car_style=? and production_year=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, vehicle.getCarName());
			preparedStatement.setString(2, vehicle.getStyleName());
			preparedStatement.setString(3, vehicle.getYear());
			resultSet=preparedStatement.executeQuery();
			//String s=resultSet.getString("style_id");
			try{
				while(resultSet.next()){
					int i=1;
					//String optional="optional";
					System.out.println("inside 2 ");
					String sql1="select availability, cylinder, size, configuration, fuelType,horsepower,torque, type, code from engine where style_id=? and availability <> ?";
					preparedStatement1=conn.prepareStatement(sql1);
					System.out.println(resultSet.getString(i));
					preparedStatement1.setString(1, resultSet.getString(i));
					preparedStatement1.setString(2, "optional");
					resultSet1=preparedStatement1.executeQuery();
					try{
					while(resultSet1.next()){
					engine.setAvailability(resultSet1.getString("availability"));
					System.out.println("sdf"+engine.getAvailability());
					engine.setCylinder(resultSet1.getInt("cylinder"));
					engine.setSize(resultSet1.getInt("size"));
					engine.setConfiguration(resultSet1.getString("configuration"));
					engine.setFuelType(resultSet1.getString("fuelType"));
					engine.setHorsepower(resultSet1.getInt("horsepower"));
					engine.setTorque(resultSet1.getInt("torque"));
					engine.setType(resultSet1.getString("type"));
					engine.setCode(resultSet1.getString("code"));
					}}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
					i++;
	
		}}catch(Exception ex)
			{
			ex.printStackTrace();
				}
			}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return engine;
	}
	public Transmission getTransmissionDetails(Vehicle vehicle) {
		// TODO Auto-generated method stub
		conn=DatabaseConnection.getConnection();
		Transmission transmission=new Transmission();
		try
		{
			String sql="select style_id from chevy where car_name=? and car_style=? and production_year=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, vehicle.getCarName());
			preparedStatement.setString(2, vehicle.getStyleName());
			preparedStatement.setString(3, vehicle.getYear());
			resultSet=preparedStatement.executeQuery();
			try{
				while(resultSet.next()){
					String i=resultSet.getString("style_id");
					String sql1="select name, availability, transmissionType, numberofSpeeds from transmission where style_id=?";
					preparedStatement1=conn.prepareStatement(sql1);
					preparedStatement1.setString(1, i);
					resultSet1=preparedStatement1.executeQuery();
					try{
						while(resultSet1.next())
						{
							transmission.setName(resultSet1.getString("name"));
							transmission.setAvailability(resultSet1.getString("availability"));
							transmission.setTransmissionType(resultSet1.getString("transmissionType"));
							transmission.setNumberOfSpeeds(resultSet1.getString("numberofSpeeds"));	
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return transmission;
	}
	public ArrayList<String> getColorDetails(Vehicle vehicle){
		conn=DatabaseConnection.getConnection();
		ArrayList<String> colorList=new ArrayList<String>();
		try{
			String sql="select style_id from chevy where car_name=? and car_style=? and production_year=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, vehicle.getCarName());
			preparedStatement.setString(2, vehicle.getStyleName());
			preparedStatement.setString(3, vehicle.getYear());
			resultSet=preparedStatement.executeQuery();
			try{
				while(resultSet.next()){
					int i=1;
					String sql1="Select distinct color_name, category from color_data where style_id=?";
					preparedStatement1=conn.prepareStatement(sql1);
					preparedStatement1.setInt(1, resultSet.getInt(i));
					resultSet1=preparedStatement1.executeQuery();
					try{
						while(resultSet1.next()){
							int i1=1;
							colorList.add(resultSet1.getString(i1));
							i++;
						}
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
					i++;
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return colorList;
	}
	public ResultSet getColor(String styleValue, String year) {
		// TODO Auto-generated method stub
		conn=DatabaseConnection.getConnection();
		try
		{
			String sql="select distinct style_id from chevy where car_style=? and production_year=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, styleValue);
			preparedStatement.setString(2, year);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				int i=1;
				String sql1="select color_name from color_data where style_id=?";
				preparedStatement=conn.prepareStatement(sql1);
				preparedStatement.setInt(1, resultSet.getInt(i));
				resultSet1=preparedStatement.executeQuery();
				i++;
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return resultSet1;
		
		
	}
	public ResultSet findCars(Vehicle vehicle) {
		// TODO Auto-generated method stub
		conn=DatabaseConnection.getConnection();
		vehicleList=new ArrayList<DealerPojo>();
		try{
			String sql="select make, model, year, color from dealer where make=? and model=? and year=?";
			preparedStatement=conn.prepareStatement(sql);
			System.out.println(vehicle.getCarName()+"dao");
			preparedStatement.setString(1, vehicle.getCarName());
			preparedStatement.setString(2, vehicle.getStyleName());
			preparedStatement.setString(3, vehicle.getYear());
			resultSet=preparedStatement.executeQuery();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return resultSet;
	}
	public String addtofav(String username, DealerPojo dealerPojo) {
		// TODO Auto-generated method stub
		String result="fail";
		conn=DatabaseConnection.getConnection();
		try{
			String sql="insert into favorites (make, model, year, color, customerId) values (?, ?, ?, ?, ?)";
			preparedStatement=conn.prepareStatement(sql);
		preparedStatement.setString(1, dealerPojo.getModel());
				preparedStatement.setString(2, dealerPojo.getStyle());
			preparedStatement.setInt(3, dealerPojo.getYear());
			preparedStatement.setString(4, dealerPojo.getColor());
			preparedStatement.setString(5, username);
			preparedStatement.executeUpdate();
			result="success";
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The car has successfully been added to the favorites");
			alert.showAndWait();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		return result;
	}
	public ResultSet getCustomerFav(String userName) {
		// TODO Auto-generated method stub
		conn=DatabaseConnection.getConnection();
		try{
			String sql="select make,model, year, color from favorites where customerId=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			resultSet=preparedStatement.executeQuery();
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return resultSet;
	}

}
