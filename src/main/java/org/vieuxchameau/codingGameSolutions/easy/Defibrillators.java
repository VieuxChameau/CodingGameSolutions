package org.vieuxchameau.codingGameSolutions.easy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

class Solution {

    private static final int LONGITUDE_INDEX = 4;
    private static final int LATITUDE_INDEX = 5;

    public static void main(String args[]) throws ParseException {
        Scanner in = new Scanner(System.in);
        String LON = in.next(); // User's longitude (in degrees)
        in.nextLine();
        String LAT = in.next(); // User's latitude (in degrees)
        in.nextLine();
        int N = in.nextInt(); // The number N of defibrillators located in the streets of Montpellier
        in.nextLine();


        double min = Double.MAX_VALUE;
        String defibName = null;
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            String[] defibData = DEFIB.split(";");
            String longitude = defibData[LONGITUDE_INDEX];
            String latitude = defibData[LATITUDE_INDEX];
            double distance = computeDistance(LON, LAT, longitude, latitude);
            if (distance < min) {
                min = distance;
                defibName = defibData[1];
            }
        }
        System.out.println(defibName);
    }

    private static double computeDistance(final String lon, final String lat, final String longitude, final String lattitude) throws ParseException {
        Double userLongitude = toRadian(lon);
        Double defLongitude = toRadian(longitude);
        Double userLatitude = toRadian(lat);
        Double defLatitude = toRadian(lattitude);

        double x = (defLongitude - userLongitude) * Math.cos((defLatitude + userLatitude) / 2);
        double y = defLatitude - userLatitude;
        return sqrt((x * x) + (y * y)) * 6371;
    }

    private static double toRadian(String lon) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(lon);
        return Math.toRadians(number.doubleValue());
    }
}
