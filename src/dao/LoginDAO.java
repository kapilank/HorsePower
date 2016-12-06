package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.webService.DatabaseConnection;

import controller.LoginController;
import model.User;

public class LoginDAO {
	LoginController loginController=new LoginController();
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	
	public String login(String userName, String password)
	{
		String result="FAIL";
		
		try
		{
			conn=DatabaseConnection.getConnection();
			String sql="select * from user_horsepower where user_id=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet==null)
			{
				loginController.loginError();
			}
			else if(resultSet.next())
			{
				User user=new User();
				user.setPassword(resultSet.getString("password"));
				if(user.getPassword().equalsIgnoreCase(password))
				{
					result="success";
					
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return result;
	}
	public String CheckLoginType(String username)
	{	
		String userType=null;
		try
		{
			conn=DatabaseConnection.getConnection();
			String sql="Select accessType from user_horsepower where user_id=?";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			userType=resultSet.getString("accessType");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return userType;
	}

}
