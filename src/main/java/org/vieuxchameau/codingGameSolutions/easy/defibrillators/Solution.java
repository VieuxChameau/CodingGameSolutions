package org.vieuxchameau.codingGameSolutions.easy.defibrillators;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

class Solution {

	private static final int DEFIBRILLATOR_NAME_INDEX = 1;
	private static final int DEFIBRILLATOR_LONGITUDE_INDEX = 4;
	private static final int DEFIBRILLATOR_LATITUDE_INDEX = 5;
	private static final int KM_EARTH_RADIUS_IN_RADIAN = 6371;

	public static void main(final String args[]) throws ParseException {
		Scanner in = new Scanner(System.in);
		String userLongitudeInDegrees = in.next(); // User's longitude (in degrees)
		in.nextLine();
		String userLatitudeInDegrees = in.next(); // User's latitude (in degrees)
		in.nextLine();
		int defibrillatorCount = in.nextInt(); // The number N of defibrillators located in the streets of Montpellier
		in.nextLine();

		double minimalDistance = Double.MAX_VALUE;
		String closestDefibrillatorName = null;
		for (int i = 0; i < defibrillatorCount; i++) {
			String DEFIB = in.nextLine();
			String[] defibrillatorData = DEFIB.split(";");
			String defibrillatorLongitudeInDegrees = defibrillatorData[DEFIBRILLATOR_LONGITUDE_INDEX];
			String defibrillatorLatitudeInDegrees = defibrillatorData[DEFIBRILLATOR_LATITUDE_INDEX];
			double defibrillatorDistance = computeDistance(userLongitudeInDegrees, userLatitudeInDegrees, defibrillatorLongitudeInDegrees, defibrillatorLatitudeInDegrees);
			if (defibrillatorDistance < minimalDistance) {
				minimalDistance = defibrillatorDistance;
				closestDefibrillatorName = defibrillatorData[DEFIBRILLATOR_NAME_INDEX];
			}
		}
		System.out.println(closestDefibrillatorName);
	}

	private static double computeDistance(final String userLongitudeInDegrees, final String userLatitudeInDegrees, final String defibrillatorLongitudeInDegrees, final String defibrillatorLatitudeInDegrees) throws ParseException {
		final double userLongitudeInRadians = toRadian(userLongitudeInDegrees);
		final double defibrillatorLongitudeInRadians = toRadian(defibrillatorLongitudeInDegrees);
		final double userLatitudeInRadians = toRadian(userLatitudeInDegrees);
		final double defibrillatorLatitudeInRadians = toRadian(defibrillatorLatitudeInDegrees);

		double x = computeX(userLongitudeInRadians, defibrillatorLongitudeInRadians, userLatitudeInRadians, defibrillatorLatitudeInRadians);
		double y = computeY(userLatitudeInRadians, defibrillatorLatitudeInRadians);
		return sqrt((x * x) + (y * y)) * KM_EARTH_RADIUS_IN_RADIAN;
	}

	private static double computeY(final double userLatitude, final double defibrillatorLatitude) {
		return defibrillatorLatitude - userLatitude;
	}

	private static double computeX(final double userLongitude, final double defibrillatorLongitude, final double userLatitude, final double defibrillatorLatitude) {
		return (defibrillatorLongitude - userLongitude) * Math.cos((defibrillatorLatitude + userLatitude) / 2);
	}

	private static double toRadian(final String degreeValue) throws ParseException {
		final NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		final Number degree = format.parse(degreeValue);
		return Math.toRadians(degree.doubleValue());
	}
}
