package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class DealerMain {
	
	public void addCars(){
		try{
			AnchorPane root;
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/DealerAdd.fxml"));
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
	public void deleteCars(){
		try{
			AnchorPane root;
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/DealerDelete.fxml"));
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

}
