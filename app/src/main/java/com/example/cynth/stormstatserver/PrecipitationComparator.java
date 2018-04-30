package com.example.cynth.stormstatserver; /**
 * Class to compare two Storm objects by the amount of precipitation.
 * 
 * CSE 214-R12 Recitation, CSE 214-02 Lecture
 * @author Cynthia Lee
 * e-mail: cynthia.lee.2@stonybrook.edu
 * Stony Brook ID: 111737790
 */
import java.util.Comparator;

public class PrecipitationComparator implements Comparator<Storm> {

	/**
	 * Method that compares two Storm objects based on their precipitation values
	 * @return 
	 * Returns 0 of the precipitation values are the same, -1 if the
	 * left/first storm precipitation is less than the right/second storm, otherwise it returns 1
	 */
	@Override
	public int compare(Storm o1, Storm o2) { // parameters: LEFT,RIGHT
		// should return -1 if the left precipitation is less than the right
		// precipitation
		// if equal return 0
		// 1 otherwise
		if (o1.getPrecipitation() == o2.getPrecipitation()) {
			return 0;
		} else if (o1.getPrecipitation() < o2.getPrecipitation()) {
			return -1;
		} else {
			return 1;
		}
	}

}
