package model;

import java.util.ArrayList;

public class Customer {
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private ArrayList<Vehicle> vehicle;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getemailAddress() {
		return address;
	}
	public void setemailAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public ArrayList<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(ArrayList<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
