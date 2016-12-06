package model;

import java.util.ArrayList;

import controller.Customer;

public class Favourites {
	private String make;
	private String model;
	private String year;
	private String color;
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	private ArrayList<Customer> customer;
	private ArrayList<Vehicle> vehicle;
	public ArrayList<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(ArrayList<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	public ArrayList<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(ArrayList<Customer> customer) {
		this.customer = customer;
	}	
	

}
