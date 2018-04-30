package com.example.cynth.stormstatserver; /**
 * Class to compare two Storms on the basis of the wind speed.
 * 
 * CSE 214-R12 Recitation, CSE 214-02 Lecture
 * @author Cynthia Lee
 * e-mail: cynthia.lee.2@stonybrook.edu
 * Stony Brook ID: 111737790
 */
import java.util.Comparator;

public class WindSpeedComparator implements Comparator<Storm>{
	
	/**Method that compares two Storms with their wind speed values
	 * @return
	 * 0 if the Storms have the same wind speed, -1 if the left/first Storm wind speed is less than the right/second Storm,
	 * otherwise it returns 1
	 */
	// override compare method
	@Override
	public int compare(Storm o1, Storm o2) { // parameters: LEFT,RIGHT
		// should return -1 if the left wind speed is less than the right wind speed
		// if equal return 0
		// 1 otherwise
		if (o1.getWindspeed() == o2.getWindspeed()) {
			return 0;
		} else if (o1.getWindspeed() < o2.getWindspeed()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	/*
	int compare(Storm left, Storm right) {
		// should return -1 if the left wind speed is less than the right wind speed
		// if equal return 0
		// 1 otherwise
		if (left.getWindspeeed() == right.getWindspeeed()) {
			return 0;
		} else if (left.getWindspeeed() < right.getWindspeeed()) {
			return -1;
		} else {
			return 1;
		}
	}
	*/
}
