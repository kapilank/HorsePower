package controller;

import java.util.Optional;

import dao.CreateAccountDAO;
import dao.LoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.User;
import model.Customer;

public class LoginController implements login {
	@FXML private TextField username;
	@FXML private TextField password;
	public static String userName=null;
	Customer customer=new Customer();
	CreateAccountDAO createDAO=new CreateAccountDAO();
	@FXML private TextField firstname;
	@FXML private TextField lastname;
	@FXML private TextField phonenumber;
	@FXML private TextField emailid;
	@FXML private TextField password1;
	User user= new User();

	@Override
	public void loginAction() {
		// TODO Auto-generated method stub
		String result="fail";
		String userType=null;
		System.err.println("Inside Login");
		String username=this.username.getText();
		
		String pass=this.password.getText();
		LoginDAO loginDao=new LoginDAO();
		result=loginDao.login(username, pass);
		if(result.equalsIgnoreCase("success"))
		{
			userType=loginDao.CheckLoginType(username);
			if(!(userType.equals(null)))
			{
				if(userType.equalsIgnoreCase("s"))
				{
					userName=username;
					System.out.println("Student Login");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Welcome Customer !");

					alert.showAndWait();
					try
					{
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Customer.fxml"));
					AnchorPane root = (AnchorPane) loader.load();
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
					Main.stage.setScene(scene);
					Main.stage.setTitle("Customer");
					//CustomerController controller =  loader.getController();
					
					}
					
				
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				else
				{
					System.out.println("Dealer Login");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Welcome Dealer !");

					alert.showAndWait();
					try{
						AnchorPane root;
						root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Dealer.fxml"));
						Scene scene = new Scene(root);
						scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
						Main.stage.setScene(scene);
						Main.stage.setTitle("Admi");
						}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
			else
			{
				loginError();
			}
			
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("Incorrect Username or Password !");

			alert.showAndWait();
		}
		
	}
	public void loginError()
	{
		System.out.println("Error Login");
	}
	

	@Override
	public void createAccoutAction() {
		// TODO Auto-generated method stub
		System.out.println("Create Account ");
		try{
			
			
			AnchorPane root;
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/CreateAccount.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.setTitle("Customer");
			}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void createAccountAction1() {
		// TODO Auto-generated method stub
		customer= new Customer();
		if(emailid.getText().isEmpty()){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Missing Values");
			alert.setContentText("Please enter the correct username value");
			Optional<ButtonType> result = alert.showAndWait();
		}
		else if(password1.getText().isEmpty()){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Password error");
			alert.setContentText("Password should greater that 6 characters");
			Optional<ButtonType> result = alert.showAndWait();
		}
		else{
		customer.setFirstName(firstname.getText());
		customer.setLastName(lastname.getText());
		customer.setPhoneNumber(phonenumber.getText());
		customer.setemailAddress(emailid.getText());
		customer.setPassword(password1.getText());
		
		createDAO.createaccount(customer);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Success");
		alert.setHeaderText("Account Created");
		alert.setContentText("Please click on OK button to login");
		Optional<ButtonType> result = alert.showAndWait();
		
		try{
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
			Main.stage.setTitle("Login");
			Main.stage.setScene(scene);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	}


	

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
			Main.stage.setTitle("Login");
			Main.stage.setScene(scene);
			
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}
		
	}
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
			Main.stage.setTitle("Login");
			Main.stage.setScene(scene);
			
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}
		
	}

}

