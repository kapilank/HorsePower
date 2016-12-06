package controller;


import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import dao.DealerDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.Vehicle;

public class DealerController implements Initializable {
	@FXML private ComboBox<String> make;
	@FXML private ComboBox<String> model;
	@FXML private ComboBox<String> color;
	@FXML private ComboBox<String> year; 
	DealerDAO dealerDao=null;
	List<String> list=new ArrayList<String>();
	List<String> styleList=null;
	List<String> yearList=null;
	List<String> colorList=null;
	ResultSet rs=null; 
	ResultSet rs1=null;
	ResultSet rs2=null;
	ResultSet r3=null;
	String modelValue=null;
	String styleValue=null;
	String yearValue=null;
	String colorValue=null;
	Vehicle vehicle=null;


	public void addAction() {
		// TODO Auto-generated method stub
		vehicle=new Vehicle();
		vehicle.setCarName(modelValue);
		vehicle.setStyleName(styleValue);
		vehicle.setColor(colorValue);
		vehicle.setYear(yearValue);
		dealerDao=new DealerDAO();
		dealerDao.add(vehicle);
		
	}

	public void back() {
		// TODO Auto-generated method stub
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Dealer.fxml"));
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dealerDao=new DealerDAO();
		//List<String> arrayList=new ArrayList<String>();
		rs=dealerDao.viewVechicles();
		addCar(rs);
		make.setItems(FXCollections.observableArrayList(list));
		make.setOnAction((event) -> {
			modelValue = make.getSelectionModel().getSelectedItem();
			rs1=dealerDao.getStyle(modelValue);
			addStyle(rs1);
			model.getSelectionModel().clearSelection();
			model.setValue(null);
			model.setItems(FXCollections.observableArrayList(styleList));
			model.setOnAction((event1)-> {
				styleValue=model.getSelectionModel().getSelectedItem();
				r3=dealerDao.getYear(styleValue);
				addYear(r3);
				year.getSelectionModel().clearSelection();
				year.setValue(null);
				year.setItems(FXCollections.observableArrayList(yearList));
				year.setOnAction((event2)->{
				yearValue=year.getSelectionModel().getSelectedItem();
				rs2=dealerDao.getColor(styleValue, yearValue);
				addColor(rs2);
				for(String i: colorList)
				{
					System.out.println(i+"inside for each");
				}
				color.setItems(FXCollections.observableArrayList(colorList));
				color.setOnAction((event3)->{
					colorValue=color.getSelectionModel().getSelectedItem();
				});
					
				});
				
			});
			
		});
		
	}
	private void addColor(ResultSet rs22) {
		// TODO Auto-generated method stub
		try
		{
			colorList=new ArrayList<String>();
			while(rs22.next())
			{
				int i=1;
				colorList.add(rs22.getString(i));
				i++;
			}
		}
		catch(Exception ex)	
		{
			ex.printStackTrace();
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

}
