package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.Favourites;

public class ViewFavController implements Initializable {
	
	String userName=LoginController.userName;
	CustomerDAO customerDao=new CustomerDAO();
	ResultSet rs=null;
	Favourites favourities=null;
	List<Favourites> fav=new ArrayList<Favourites>();
	@FXML private TableView<Favourites> viewFav;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub\
		rs=customerDao.getCustomerFav(userName);
		try{
			while(rs.next()){
				favourities=new Favourites();
				favourities.setMake(rs.getString("make"));
				favourities.setModel(rs.getString("model"));
				favourities.setColor(rs.getString("color"));
				favourities.setYear(rs.getString("year"));
				fav.add(favourities);	
			}
			viewFav.setItems(FXCollections.observableArrayList(fav));
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void cancel() {
		// TODO Auto-generated method stub
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Customer.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
			Main.stage.setTitle("Login");
			Main.stage.setScene(scene);
			
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}
		
	}
	
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
