package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.webService.DatabaseConnection;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.DealerPojo;
import model.Vehicle;

public class DealerDAO {
	Connection conn=null;
	PreparedStatement preparedStatement=null, preparedStatement1=null, preparedStatement2=null;
	ResultSet resultSet=null;
	ResultSet resultSet1=null;
	ResultSet rs=null;
	
	public void add(Vehicle vehicle)
	{
		try
		{
			
			conn=DatabaseConnection.getConnection();
			String sql1="select style_id from chevy where car_name=? and car_style=? and production_year=?";
			preparedStatement=conn.prepareStatement(sql1);
			preparedStatement.setString(1, vehicle.getCarName());
			preparedStatement.setString(2, vehicle.getStyleName());
			preparedStatement.setString(3, vehicle.getYear());
			rs=preparedStatement.executeQuery();
			try{
				while(rs.next()){
					int i=1;
					String sql="insert into dealer (make, model, color, year, style_id) values (?, ?, ?, ?, ?)";
					preparedStatement2=conn.prepareStatement(sql);
					preparedStatement2.setString(1, vehicle.getCarName());
					preparedStatement2.setString(2, vehicle.getStyleName());
					preparedStatement2.setString(3, vehicle.getColor());
					preparedStatement2.setString(4, vehicle.getYear());
					preparedStatement2.setInt(5, rs.getInt(i));
					preparedStatement2.executeUpdate();
					i++;
					
				}
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("The car has successfully been added to the Dealer Database");

				alert.showAndWait();
					
				}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
		
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}

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
			String sql="select production_year from chevy where car_style=?";
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

	public ArrayList<DealerPojo> viewAllCars() {
		// TODO Auto-generated method stub
		conn=DatabaseConnection.getConnection();
		ArrayList<DealerPojo> vehicleList=new ArrayList<DealerPojo>();
		try{
			String sql="select * from dealer";
			preparedStatement=conn.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				DealerPojo dealerPojo=new DealerPojo();
				dealerPojo.setModel(resultSet.getString("make"));
				dealerPojo.setStyle(resultSet.getString("model"));
				dealerPojo.setColor(resultSet.getString("color"));
				dealerPojo.setYear(resultSet.getInt("year"));
				vehicleList.add(dealerPojo);
				}
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return vehicleList;
	}
	public void deletecar(DealerPojo dealerPojo) {
		// TODO Auto-generated method stub
		
		conn=DatabaseConnection.getConnection();
		try{
			String sql="delete from dealer where make=? and model=? and year=? and color=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, dealerPojo.getModel());
			preparedStatement.setString(2, dealerPojo.getStyle());
			preparedStatement.setInt(3, dealerPojo.getYear());
			preparedStatement.setString(4, dealerPojo.getColor());
			preparedStatement.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The car has successfully been deleted from the Dealer Database");

			alert.showAndWait();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		
	}
	

}
