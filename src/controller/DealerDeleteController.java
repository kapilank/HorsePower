package controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.DealerDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.DealerPojo;
import model.Vehicle;

public class DealerDeleteController implements Initializable {
	Vehicle vehicle=null;
	DealerDAO dealerDao=new DealerDAO();
	ArrayList<DealerPojo> vehicleList=null;
	ArrayList<DealerPojo> vehiclesList1=null;
	@FXML private TableView<DealerPojo> vehicles;
	DealerPojo dealerPojo=new DealerPojo();
	DealerPojo dealerPojo1=new DealerPojo();
	ResultSet rs=null;
	@FXML private Button deleteCar;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		vehicle=new Vehicle();
		vehicleList=new ArrayList<DealerPojo>();
		vehicleList=dealerDao.viewAllCars();
		vehicles.setItems(FXCollections.observableArrayList(vehicleList));
		deleteCar.setOnAction((event)->{
			dealerPojo1=vehicles.getSelectionModel().getSelectedItem();
			System.out.println(dealerPojo1.getColor()+"color");
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Delete Car Confirmation");
			alert.setContentText("Are you sure you want to delete the car ?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				dealerDao.deletecar(dealerPojo1);
				vehiclesList1=new ArrayList<DealerPojo>();
				vehiclesList1=dealerDao.viewAllCars();
				vehicles.setItems(FXCollections.observableArrayList(vehiclesList1));
			} else if(result.get() == ButtonType.CANCEL) {
				
			}
			
			//deleteCar(dealerPojo1);
		});
		
		
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
}
