package com.example.cynth.stormstatserver; /**
 * Class that allows the user to interact with a database of Storms. Provides the user with a menu-based interface that allows
 * them to add, remove and edit Storms.
 * 
 * CSE 214-R12 Recitation, CSE 214-02 Lecture
 * @author Cynthia Lee
 * e-mail: cynthia.lee.2@stonybrook.edu
 * Stony Brook ID: 111737790
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class StormStatServer {
	// menu
	// should be able to serialize (save) the database at the end of the program
	// de-serialize (load)

	// on start up check if hurricane.ser exists
	// if it does, it should be loaded
	// if not should create new HashMap and use

	private static HashMap<String, Storm> database;

	/**
	 * Prompts user with a menu to interact with Storm objects
	 */
	public static void main(String[] args) {
		System.out.println(
				"Welcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you when it happened!\n");
		try {
			FileInputStream file = new FileInputStream("hurricane.ser");
			ObjectInputStream inStream = new ObjectInputStream(file);

			// hurricane.ser exists, LOAD from file
			// FileInputStream file = new FileInputStream("storage.obj");
			// ObjectInputStream inStream = new ObjectInputStream(file);
			// StorageTable storage;
			// storage = (StorageTable) inStream.readObject();
			try {
				database = (HashMap<String, Storm>) inStream.readObject();
				System.out.println("hurricane.ser was found and loaded.");
				System.out.println();
			} catch (ClassNotFoundException e) {
				System.out.println("Class could not be found. ClassNotFoundError.");
				System.out.println();
			}
			inStream.close();

			// missing code here can use StorageTable constructed previously
			/*
			 * FileInputStream file = new FileInputStream("storage.obj"); ObjectInputStream
			 * inStream = new ObjectInputStream(file); // Reading from a file
			 * HashMap<String, Storm> database; database = (HashMap) inStream.readObject();
			 * inStream.close();
			 */

		} catch (FileNotFoundException ex) { // create a new HashMap to use
			System.out.println("No previous data found.\n");
			database = new HashMap<String, Storm>();
			// code to save to a file
			// StorageTable storage = new StorageTable(/*Constructor Parameters*/);
			// missing code here adds Storage objects to the table.
			// FileOutputStream file = new FileOutputStream("storage.obj");
			// ObjectOutputStream outStream = new ObjectOutputStream(file);
			// the following line will save the object in the file
			// outStream.writeObject(storage);
			// outStream.close();
		} catch (IOException ex) {
			System.out.println("File has input and/or output error.");
		}
		// FileOutputStream file = new FileOutputStream("database.obj");
		// ObjectOutputStream outStream = new ObjectOutputStream(file);
		System.out.println(
				"Menu:\nA) Add A Storm\nL) Look Up A Storm\nD) Delete A Storm\nE) Edit Storm Data\nR) Print Storms Sorted By Rainfall\nW) Print Storms by Windspeed\nX) Save and quit\nQ) Quit and delete saved data\n");
		Scanner input = new Scanner(System.in);
		boolean repeat = true;
		String option;
		do {
			System.out.print("Please select an option: ");
			option = input.nextLine();
			if (option.equalsIgnoreCase("A")) {
				System.out.print("Please enter name: ");
				String name = input.nextLine().toUpperCase(); // need to make upper case
				System.out.print("Please enter date: "); // CHECK FORMAT
				String date = input.nextLine();
				if (checkDate(date) == true) {
					System.out.print("Please enter precipitation (cm): ");
					double pre = Double.parseDouble(input.nextLine());
					if (pre < 0) {
						System.out.println("Precipitation cannot be a negative number!\n");
					} else {
						System.out.print("Please enter windspeed (km/h): ");
						double speed = Double.parseDouble(input.nextLine());
						if (speed < 0) {
							System.out.println("Windspeed should not be a negative number!\n");
						} else {
							Storm st = new Storm(name, pre, speed, date);
							database.put(st.getName(), st);
							System.out.println(st.getName() + " added.\n");
						}
					}
				} else {
					System.out.println("The date entered was in an invalid format.");
					System.out.println();
				}
			} else if (option.equalsIgnoreCase("L")) {
				System.out.print("Please enter a name: ");
				String name = input.nextLine().toUpperCase();
				// find and print out the storm in a string
				if (database.get(name) != null) {
					System.out.println();
					System.out.println(database.get(name).toString());
					System.out.println();
				} else {
					System.out.println("Storm not found.");
					System.out.println();
				}
			} else if (option.equalsIgnoreCase("D")) {
				System.out.print("Please enter name: ");
				String name = input.nextLine().toUpperCase();
				if (database.get(name) != null) {
					database.remove(name);
					System.out.println("Storm " + name + " has been deleted.\n");
				} else {
					System.out.println("Storm not found, cannot delete.");
					System.out.println();
				}
			} else if (option.equalsIgnoreCase("E")) {
				boolean valid = false;
				System.out.print("Please enter name: ");
				String name = input.nextLine().toUpperCase();
				if (database.get(name) != null) {
					System.out.print("In Edit Mode, press enter without any input to leave data unchanged.\n");
					System.out.print("Please enter date: ");
					String date = input.nextLine();
					if (date.equals("")) {
						valid = true;
					} else if (checkDate(date) == false) {
						System.out.println("Invalid date format\n");
						valid = false;
					} else {
						database.get(name).setDate(date);
						valid = true;
					}
					if (valid) {
						System.out.print("Please enter precipitation (cm): ");
						String sPre = input.nextLine();
						if (!sPre.equals("")) {
							Double pre = Double.parseDouble(sPre);
							if (pre < 0) {
								System.out.println("Precipitation cannot be a negative number!");
								valid = false;
							} else {
								database.get(name).setPrecipitation(pre);
								valid = true;
							}
						}
					}
					if (valid) {
						System.out.print("Please enter windspeed (km/h): ");
						String sSpeed = input.nextLine();
						if (!sSpeed.equals("")) {
							Double speed = Double.parseDouble(sSpeed);
							if (speed < 0) {
								System.out.println("Windspeed cannot be a negative number!");
								valid = false;
							} else {
								database.get(name).setWindspeed(speed);
								valid = true;
							}
						}
					}
					if (valid) {
						System.out.println(name + " added.");
						System.out.println();
					}

				} else {
					System.out.println("Key not found.\n");
				}
			} else if (option.equalsIgnoreCase("R")) { // print storms sorted by rainfall (precipitation)
				System.out.println();
				ArrayList<Storm> stormList = new ArrayList<Storm>(database.values());
				Collections.sort(stormList, new PrecipitationComparator());
				System.out.format("%-20s %-14s %s%n", "Name", "Windspeed", "RainFall");
				// System.out.println("Name\tWindspeed\tRainfall");
				String s = "";
				System.out.println("--------------------------------------------");
				for (int i = 0; i < stormList.size(); i++) {
					// System.out.println(stormList.get(i).getName() + "\t" +
					// stormList.get(i).getWindspeed() + "\t"
					// + stormList.get(i).getPrecipitation());
					s = s + String.format("%-20s %-14s %s%n", stormList.get(i).getName(),
							stormList.get(i).getWindspeed(), stormList.get(i).getPrecipitation());
				}
				System.out.println(s);
				System.out.println();
			} else if (option.equalsIgnoreCase("W")) {
				System.out.println();
				ArrayList<Storm> stormList = new ArrayList<Storm>(database.values());
				Collections.sort(stormList, new WindSpeedComparator());
				// System.out.println("Name\tWindspeed\tRainfall");
				System.out.format("%-20s %-14s %s%n", "Name", "Windspeed", "RainFall");
				System.out.println("--------------------------------------------");
				String s = "";
				for (int i = 0; i < stormList.size(); i++) {
					// System.out.println(stormList.get(i).getName() + "\t" +
					// stormList.get(i).getWindspeed() + "\t"
					// + stormList.get(i).getPrecipitation());
					s = s + String.format("%-20s %-14s %s%n", stormList.get(i).getName(),
							stormList.get(i).getWindspeed(), stormList.get(i).getPrecipitation());
				}
				System.out.println(s);
				System.out.println();
			} else if (option.equalsIgnoreCase("X")) { // SAVE AND QUIT
				try {
					FileOutputStream file = new FileOutputStream("hurricane.ser");
					ObjectOutputStream outStream = new ObjectOutputStream(file);
					// the following line will save the object in the file
					outStream.writeObject(database);
					outStream.close();
					System.out.println(
							"File saved to hurricane.ser; feel free to use the weather channel in the meantime.");
					repeat = false;
				} catch (FileNotFoundException ex) {
					System.out.println("Could not find the file.");
				} catch (IOException ex) {
					System.out.println("File input and/or output error.");
				}
			} else if (option.equalsIgnoreCase("Q")) {
				File f = new File("hurricane.ser");
				f.delete();
				System.out.println(
						"Goodbye, it's hard to hold an (electric) candle in the cold November (and April) rain!");
				repeat = false;
			} else {
				System.out.println("Invalid option!");
				System.out.println();
			}
		} while (repeat);
	}

	/**
	 * Method that checks if a String for the date of the Storm has a correct format
	 * of "YYYY-MM-DD"
	 * 
	 * @param d
	 *            String that will be checked if it has the correct date format
	 * @return Boolean true if the String is in YYYY-MM-DD format, otherwise returns
	 *         false
	 */
	public static boolean checkDate(String d) {
		// YYYY-MM-DD or YYYY-M-DD
		// or YYYY-MM-D or YYYY-M-D
		String[] parts = d.split("-", 3);
		/*
		 * // need to check length of each part if ((parts[0].length() != 4) &&
		 * (parts[1].length() != 2 || parts[1].length() != 1) && (parts[2].length() != 2
		 * || parts[2].length() != 1)) { return false; }
		 */
		if (parts[0].length() != 4 || d == null || parts.length != 3) {
			return false;
		}
		//if ((parts[1].length() != 1 || parts[1].length() != 2) || (parts[2].length() != 1 || parts[2].length() != 2)) {
			//System.out.println(parts[1].length() + " " + parts[2].length());
			//return false;
		//}
		if ((parts[1].length() == 1 || parts[1].length() == 2) && (parts[2].length() == 1 || parts[2].length() == 2)) {
			
		} else {
			return false;
		}
		for (int i = 0; i < parts[0].length(); i++) { // years
			if (!Character.isDigit(parts[0].charAt(i))) {
				return false;
			}
		}
		/*
		 * for (int i = 0; i < parts[1].length(); i++) { // month // month must be
		 * 1,2,3,4,5,6,7,8,9,10,11,12 if (!Character.isDigit(parts[0].charAt(i))) {
		 * return false; } }
		 */
		// check if month is in range 1-12
		try {
			int month = Integer.parseInt(parts[1]);
			if (month < 1 || month > 12) {
				return false;
			}
		} catch (NumberFormatException ex) {
			return false;
		}
		// check if day is in range 1-31
		try {
			int day = Integer.parseInt(parts[2]);
			if (day < 1 || day > 31) {
				return false;
			}
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

}
