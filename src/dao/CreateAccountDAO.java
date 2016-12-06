package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.webService.DatabaseConnection;
import model.Customer;

public class CreateAccountDAO {
	Connection conn=null;
	PreparedStatement preparedStatement=null, preparedStatement2=null;
	ResultSet resultSet=null, resultSet2=null;
	public void createaccount(Customer customer)
	{
		try
		{
			int i=0, j=0;
			conn=DatabaseConnection.getConnection();
			String sql="insert into customer_horsepower(first_name, last_name, phone_number, emailid) values(?, ?, ?, ? )";
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getPhoneNumber());
			preparedStatement.setString(4, customer.getemailAddress());
			preparedStatement.executeUpdate();
			System.out.println(i);
			String sql2="insert into user_horsepower(user_id, password, accessType) values(?,?,?)";
			preparedStatement2=conn.prepareStatement(sql2);
			preparedStatement2.setString(1, customer.getemailAddress());
			preparedStatement2.setString(2, customer.getPassword());
			preparedStatement2.setString(3, "s");
			preparedStatement2.executeUpdate();
			System.out.println(j);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}
}
	