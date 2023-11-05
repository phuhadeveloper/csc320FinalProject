package csc320FinalProject;

import java.util.ArrayList;

public class VehiclesInventory {
	
	// Vehicle class
	public class Vehicle {
		// private fields
		private String make;
		private String model;
		private String color;
		private int year;
		private int mileage;
		
		// constructor 
		public Vehicle(String make, String model, String color, int year, int mileage) {
			this.make = make;
			this.model = model;
			this.color = color;
			this.year = year;
			this.mileage = mileage;
		}
		
		//getters
		public String getMake() {return make;}
		public String getModel() {return model;}
		public String getColor() {return color;}
		public int getYear() {return year;}
		public int getMileage() {return mileage;}
		
		//setters
		public void setMake(String make) {this.make = make;}
		public void setModel(String model) {this.model = model;}
		public void setColor(String color) {this.color = color;}
		public void setYear(int year) {this.year = year;}
		public void setMileage(int mileage) {this.mileage = mileage;}
		
		//equals method
		public Boolean equals(String make, String model, String color, int year, int mileage) {
			if (!this.make.equals(make)) return false;
			if (!this.model.equals(model)) return false;
			if (!this.color.equals(color)) return false;
			if (this.year != year) return false;
			if (this.mileage != mileage) return false;
			return true;
		}
		
		//toString method
		public String toString() {
			return year + " " + color + " " + make + " " + model + ", " + mileage + " miles"; 
		}
	}
	//end of Vehicle class
	
	// private fields for VehiclesInventory class 
	private ArrayList<Vehicle> vehiclesList;
	
	// constructor
	public VehiclesInventory() {
		vehiclesList = new ArrayList<Vehicle>();
	}
	
	// helper method to find index of vehicle in vehiclesList, return -1 if vehicle not in list
	private int getIndexOfVehicle(String make, String model, String color, int year, int mileage) {
		for (int i = 0; i < vehiclesList.size(); i++) {
			if (vehiclesList.get(i).equals(make, model, color, year, mileage)) {
				return i;
			}
		}
		return -1;
	}
	
	// method to add a vehicle to vehiclesList
	public String addVehicle(String make, String model, String color, int year, int mileage) {
		Vehicle newVehicle = new Vehicle(make, model, color, year, mileage);
		vehiclesList.add(newVehicle);
		return "Vehicle was added successfully.";
	}
	
	// method to remove a vehicle from vehiclesList
	public String removeVehicle(String make, String model, String color, int year, int mileage) {
		int indexOfVehicle = getIndexOfVehicle(make, model, color, year, mileage);
		
		if (indexOfVehicle == -1) return "Vehicle not found.";
		
		vehiclesList.remove(indexOfVehicle);
		
		return "Vehicle was removed sucessful.";
	}
	
	// method to update a vehicle from vehiclesList
	public String updateVehicle(String make, String model, String color, int year, int mileage, String newMake, String newModel, String newColor, int newYear, int newMileage) {
		int indexOfVehicle = getIndexOfVehicle(make, model, color, year, mileage);
		
		if (indexOfVehicle == -1) return "Vehicle not found";
		
		Vehicle vehicle = vehiclesList.get(indexOfVehicle);
		vehicle.setMake(newMake);
		vehicle.setModel(newModel);
		vehicle.setColor(newColor);
		vehicle.setYear(newYear);
		vehicle.setMileage(newMileage);
		
		return "Vehicle's information was updated successfully";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
