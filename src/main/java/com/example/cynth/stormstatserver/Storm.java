package com.example.cynth.stormstatserver; /**
 * Class for Storm objects. Storms have a name, amount of precipitation, amount of wind speed and date.
 * 
 * CSE 214-R12 Recitation, CSE 214-02 Lecture
 * @author Cynthia Lee
 * e-mail: cynthia.lee.2@stonybrook.edu
 * Stony Brook ID: 111737790
 */
import java.io.Serializable;

public class Storm implements Serializable {
	private String name;
	private double precipitation;
	private double windspeed;
	String date = ""; // YYYY-MM-DD

	/**
	 * Creates a Storm and assigns the according variables to it
	 * @param n
	 * String for the name of the Storm
	 * @param pre
	 * Double for the amount of precipitation
	 * @param speed
	 * Double for the amount of wind speed
	 * @param d
	 * String for the date of the Storm, with a "YYYY-MM-DD" format
	 */
	public Storm(String n, double pre, double speed, String d) {
		name = n;
		precipitation = pre;
		windspeed = speed;
		date = d;
		// check if the date is in the right format
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public double getPrecipitation() {
		return precipitation;
	}

	public double getWindspeed() {
		return windspeed;
	}

	public String getDate() {
		return date;
	}

	public void setName(String n) {
		name = n;
	}

	public void setPrecipitation(double p) {
		precipitation = p;
	}

	public void setWindspeed(double w) {
		windspeed = w;
	}

	public void setDate(String d) {
		date = d;
	}

	/**
	 * Method that describes the Storm with the date, wind speed and precipitation
	 * @return
	 * String that describes the Storm
	 */
	public String toString() {
		return name.toUpperCase() + ": Date " + date + ", " + windspeed + " km/h winds, " + precipitation
				+ " cm of rain";
	}

}
