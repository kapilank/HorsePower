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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.Color;
import model.DealerPojo;
import model.Engine;
import model.Transmission;
import model.Vehicle;

public class CustomerController implements Customer, Initializable {
	@FXML private ComboBox<String> make;
	@FXML private ComboBox<String> model;
	@FXML private ComboBox<String> year;
	@FXML private Button searchButton;
	@FXML private TextArea engineDetails;
	@FXML private TextArea transmissionDetails;
	@FXML private TextArea colorDetails;
	@FXML private Button findCars;
	@FXML private Button viewFav;
	List<String> colorList=null;
	List<String> list=new ArrayList<String>();
	List<String> styleList=null;
	List<String> yearList=null;
	List<DealerPojo> vehicleList=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rs2=null;
	static ResultSet rs3=null;
	public static String modelValue=null;
	public static String styleValue=null;
	public static String yearValue=null;
	Vehicle vehicle=null;
	CustomerDAO customerDao=null;
	Engine engine=null;
	Transmission transmission=null;
	Color color=null;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		customerDao=new CustomerDAO();
		rs=customerDao.viewVechicles();
		addCar(rs);
		make.setItems(FXCollections.observableArrayList(list));
		make.setOnAction((event) -> {
			modelValue = make.getSelectionModel().getSelectedItem();
			rs1=customerDao.getStyle(modelValue);
			addStyle(rs1);
			model.getSelectionModel().clearSelection();
			model.setValue(null);
			model.setItems(FXCollections.observableArrayList(styleList));
			model.setOnAction((event1)-> {
				styleValue=model.getSelectionModel().getSelectedItem();
				rs2=customerDao.getYear(styleValue);
				addYear(rs2);
				year.getSelectionModel().clearSelection();
				year.setValue(null);
				year.setItems(FXCollections.observableArrayList(yearList));
				year.setOnAction((event2)->{
					yearValue=year.getSelectionModel().getSelectedItem();
				
				});
				
			});
			
		});
		searchButton.setOnAction((event3)->{
			viewCars();
			engineDetails.setEditable(false);
			transmissionDetails.setEditable(false);
			colorDetails.setEditable(false);
			engineDetails.clear();
			engineDetails.appendText("Availability:"+engine.getAvailability()+"\n");
			engineDetails.appendText("Cylinder:"+engine.getCylinder()+"\n");
			engineDetails.appendText("Size:"+engine.getSize()+"\n");
			engineDetails.appendText("Configuration"+engine.getConfiguration()+"\n");
			engineDetails.appendText("Fuel type:"+engine.getFuelType()+"\n");
			engineDetails.appendText("Horsepower:"+engine.getHorsepower()+"\n");
			engineDetails.appendText("Torque:"+engine.getTorque()+"\n");
			engineDetails.appendText("Type:"+engine.getType()+"\n");
			engineDetails.appendText("Code"+engine.getCode()+"\n");
			transmissionDetails.clear();
			transmissionDetails.appendText("Name:"+transmission.getName()+"\n");
			transmissionDetails.appendText("Availability:"+transmission.getAvailability()+"\n");
			transmissionDetails.appendText("Transmission Type:"+transmission.getTransmissionType()+"\n");
			transmissionDetails.appendText("Number of Speeds:"+transmission.getNumberOfSpeeds()+"\n");
			colorDetails.clear();
			for(String i : colorList){
			colorDetails.appendText(i.toString());
			
			}
		});
		findCars.setOnAction((event4)->{
			try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DealerList.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
			Main.stage.setTitle("Favorites");
			Main.stage.setScene(scene);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		});
		viewFav.setOnAction((event5)->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/viewFav.fxml"));
				AnchorPane root = (AnchorPane) loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
				Main.stage.setTitle("Favorites");
				Main.stage.setScene(scene);
				
				}
				
			catch(Exception e) {
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public void viewCars() {
		// TODO Auto-generated method stub
		try{
		engine=new Engine();
		transmission=new Transmission();
		colorList=new ArrayList<String>();
		color=new Color();
		vehicle=new Vehicle();
		vehicle.setCarName(modelValue);
		vehicle.setStyleName(styleValue);
		vehicle.setYear(yearValue);
		engine=customerDao.getEngineDetails(vehicle);
		transmission=customerDao.getTransmissionDetails(vehicle);
		System.out.println(transmission.getNumberOfSpeeds());
		colorList=customerDao.getColorDetails(vehicle);
		for(String i : colorList){
			System.out.println(i+"Printing List");
		} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
		
	public void addCar(ResultSet rs)
	{
		try{
		while(rs.next())
		{
			int i=1;
			list.add(rs.getString(i));
			i++;
		
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void addStyle(ResultSet rs)
	{
		try
		
		{
			styleList=new ArrayList<String>();
			while(rs.next())
			{
				int i=1;
				
				styleList.add(rs.getString(i));
				i++;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();;
		}
	}
	public void addYear(ResultSet rs)
	{
		try{
			yearList=new ArrayList<String>();
			while(rs.next())
			{
				int i=1;
				yearList.add(rs.getString(i));
				System.out.println("asd:"+ rs.getInt(i));
				i++;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	@Override
	public void addToFavorites() {
		// TODO Auto-generated method stub
		
	}
	public void back() {
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

	@Override
	public void findCars() {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DealerList.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
			Main.stage.setTitle("Favorites");
			Main.stage.setScene(scene);
			
			}
			
		catch(Exception e) {
			e.printStackTrace();
		}}
	public void viewFav(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/viewFav.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("bg.css").toExternalForm());
			Main.stage.setTitle("Favorites");
			Main.stage.setScene(scene);
			
			}
			
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	

	
	

}
