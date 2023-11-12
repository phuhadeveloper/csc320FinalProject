package csc320FinalProject;

import java.util.ArrayList;
import java.time.Year;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class VehiclesInventory {
	
	// Vehicle class
	public class Vehicle {
		// private fields
		private String make;
		private String model;
		private String color;
		private int year;
		private int mileage;
		
		// default constructor 
		public Vehicle() {
			make = "not assigned";
			model = "not assigned";
			color = "not assigned";
			year = 0;
			mileage = 0;
		}
		
		
		// parameterized constructor 
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
			if (!this.make.equalsIgnoreCase(make)) return false;
			if (!this.model.equalsIgnoreCase(model)) return false;
			if (!this.color.equalsIgnoreCase(color)) return false;
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
	
	//customs exceptions
	public class VehicleNotInListException extends Exception {
		public VehicleNotInListException(String errorMessage) {
			super(errorMessage);
		}
	}
	
	public class InvalidInputsException extends Exception {
		public InvalidInputsException(String errorMessage) {
			super(errorMessage);
		}
	}
	// end of customs exceptions
	
	// private fields for VehiclesInventory class 
	private ArrayList<Vehicle> vehiclesList;
	
	// default constructor
	public VehiclesInventory() {
		vehiclesList = new ArrayList<Vehicle>();
	}
	
	// parameterized constructor 
	public VehiclesInventory(ArrayList<Vehicle> carsStorage) {
		vehiclesList = carsStorage;
	}
	
	//getters
	public ArrayList<Vehicle> getVehiclesList() {
		return vehiclesList;
	}
	
	// setters
	public void setVehiclesList(ArrayList<Vehicle> carsStorage) {
		vehiclesList = carsStorage;
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
	
	//helper method to check if inputs are valid
	private Boolean checkInputs(String make, String model, String color, int year, int mileage) {
		if (make.equals(null) || make.equals("")) return false;
		if (model.equals(null) || model.equals("")) return false;
		if (color.equals(null) || color.equals("")) return false;
		if (year < 1900 || year > Year.now().getValue()) return false;
		if (mileage < 0) return false;
		
		return true;
	}
	
	
	// method to add a vehicle to vehiclesList
	public String addVehicle(String make, String model, String color, int year, int mileage) {
		try {
			if (!checkInputs(make, model, color, year, mileage)) {
				throw new InvalidInputsException("Invalid inputs.");
			}
			
			int index = getIndexOfVehicle(make, model, color, year, mileage);
			if (index != -1) {
				throw new VehicleNotInListException("Vehicle already exists.");
			}
			Vehicle newVehicle = new Vehicle(make, model, color, year, mileage);
			vehiclesList.add(newVehicle);
			return "Vehicle was added successfully.";
		} catch(InvalidInputsException e) {
			return e.getMessage();
		} catch(VehicleNotInListException e) {
			return e.getMessage();		}
	}
	
	// method to remove a vehicle from vehiclesList
	public String removeVehicle(String make, String model, String color, int year, int mileage) {
		try {
			if (!checkInputs(make, model, color, year, mileage)) {
				throw new InvalidInputsException("Invalid inputs.");
			}
			
			int indexOfVehicle = getIndexOfVehicle(make, model, color, year, mileage);
			
			if (indexOfVehicle == -1) throw new VehicleNotInListException("Vehicle not found.");
			
			vehiclesList.remove(indexOfVehicle);
			
			return "Vehicle was removed sucessful.";
		} catch(InvalidInputsException e) {
			return e.getMessage();		
		} catch(VehicleNotInListException e) {
			return e.getMessage();
		}
	}
	
	// method to update a vehicle from vehiclesList
	public String updateVehicle(String make, String model, String color, int year, int mileage, String newMake, String newModel, String newColor, int newYear, int newMileage) {
		try {
			if (!checkInputs(make, model, color, year, mileage)) {
				throw new InvalidInputsException("Invalid inputs.");
			}
			if (!checkInputs(newMake, newModel, newColor, newYear, newMileage)) {
				throw new InvalidInputsException("Invalid inputs.");
			}
			
			int indexOfVehicle = getIndexOfVehicle(make, model, color, year, mileage);
			
			if (indexOfVehicle == -1) throw new VehicleNotInListException("Vehicle not found.");
			
			Vehicle vehicle = vehiclesList.get(indexOfVehicle);
			vehicle.setMake(newMake);
			vehicle.setModel(newModel);
			vehicle.setColor(newColor);
			vehicle.setYear(newYear);
			vehicle.setMileage(newMileage);
			
			return "Vehicle's information was updated successfully.";
		} catch(InvalidInputsException e) {
			return e.getMessage();		
		} catch(VehicleNotInListException e) {
			return e.getMessage();
		}
	}
	
	// method to return a list of vehicles' information
	public String[] listVehiclesInfo() {
		String[] result = new String[vehiclesList.size()];
		for (int i = 0; i < vehiclesList.size(); i++) {
			result[i] = vehiclesList.get(i).toString();
 		}
		return result;
	}
	
	// method to print out contents from a file
	public static void printFileContents(String filePath) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(filePath);
		Scanner inFS = new Scanner(fileInputStream);
		
		System.out.println("Contents of the file are: ");
		
		while (inFS.hasNext()) {
			String nextItem = inFS.next();
			if (nextItem.equals("\n")) {
				System.out.println("");
				continue;
			}
			System.out.print(nextItem + " ");
		}
	}

	public static void main(String[] args) {
		try {
			VehiclesInventory vehicles = new VehiclesInventory();
			Scanner scanner = new Scanner(System.in);
			
			//adding vehicles
			System.out.println("Add vehicle (Toyota, Rav4, White, 2022, 10): ");
			System.out.println(vehicles.addVehicle("Toyota", "Rav4", "White", 2022, 10));
			
			// list current vehicles
			System.out.println("Current Vehicles:");
			for (Vehicle vehicle : vehicles.getVehiclesList()) {
				System.out.println(vehicle.toString());
			}
			
			//Remove vehicle
			System.out.println("Remove vehicle (Toyota, Rav4, White, 2022, 10): ");
			System.out.println(vehicles.removeVehicle("Toyota", "Rav4", "White", 2022, 10));
			
			// add new vehicle
			System.out.println("Add vehicle (Toyota, Camry, Black, 2019, 0): ");
			System.out.println(vehicles.addVehicle("Toyota", "Camry", "Black", 2019, 0));
			
			// list current vehicles
			System.out.println("Current Vehicles:");
			for (Vehicle vehicle : vehicles.getVehiclesList()) {
				System.out.println(vehicle.toString());
			}
			
			// update vehicle
			System.out.println("Update vehicle (Toyota, Camry, Black, 2019, 0 ) with (Toyota, Camry, White, 2019, 100): ");
			System.out.println(vehicles.updateVehicle("Toyota", "Camry", "Black", 2019, 0, "Toyota", "Camry", "White", 2019, 100));
			
			// list current vehicles
			System.out.println("Current Vehicles:");
			for (Vehicle vehicle : vehicles.getVehiclesList()) {
				System.out.println(vehicle.toString());
			}
			
			System.out.println("Do you want to print the vehicles' information to a file? Enter 'Y' or 'N': ");
			String userAnswer = scanner.next();
			
			if (userAnswer.equalsIgnoreCase("y")) {
				//create the file path
				String fileSeparator = System.getProperty("file.separator");
				String filePath = fileSeparator + "Users" + fileSeparator + "phuth" + fileSeparator + "git" + fileSeparator + "csc320FinalProject" + fileSeparator + "csc320FinalProject" + fileSeparator + "src" + fileSeparator + "csc320FinalProject" + fileSeparator + "vehiclesinfo.txt";
			
				// create a new file
				File file = new File(filePath);
				file.createNewFile();
				
				// writing vehicles' information to the file
				FileOutputStream fileOutputStream = new FileOutputStream(filePath);
				PrintWriter outFS = new PrintWriter(filePath);
				
				for (Vehicle vehicle: vehicles.getVehiclesList()) {
					outFS.println(vehicle.toString() + "\n");
				}
				
				outFS.close();
				
				// reading from file
				printFileContents(filePath);
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
