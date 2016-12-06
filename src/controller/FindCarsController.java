package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.DealerPojo;
import model.Vehicle;

public class FindCarsController implements Initializable {
	Vehicle vehicle=null;
	CustomerDAO customerDao=new CustomerDAO();
	ArrayList<DealerPojo> vehicleList=null;
	@FXML private TableView<DealerPojo> vehicles;
	DealerPojo dealerPojo=null;
	DealerPojo dealerPojo1=new DealerPojo();
	ResultSet rs=null;
	@FXML private Button addtoFav;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		vehicle=new Vehicle();
		vehicleList=new ArrayList<DealerPojo>();
		vehicle.setCarName(CustomerController.modelValue);
		vehicle.setStyleName(CustomerController.styleValue);
		vehicle.setYear(CustomerController.yearValue);
		rs=customerDao.findCars(vehicle);
		try{
		while(rs.next()){
			dealerPojo= new DealerPojo();
			dealerPojo.setModel(rs.getString("make"));
			dealerPojo.setStyle(rs.getString("model"));
			dealerPojo.setYear(rs.getInt("year"));
			dealerPojo.setColor(rs.getString("color"));
			vehicleList.add(dealerPojo);
		}
		vehicles.setItems(FXCollections.observableArrayList(vehicleList));
		addtoFav.setOnAction((event)->{
			dealerPojo1=vehicles.getSelectionModel().getSelectedItem();
			System.out.println(dealerPojo1.getColor()+"color");
			addToFavorties(dealerPojo1);
		});
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}
	public void addToFavorties(DealerPojo dealerPojo){
		String result="fail";
		String username=LoginController.userName;
		result=customerDao.addtofav(username, dealerPojo);
		System.out.println(result);
		
	}
	public void back() {
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
