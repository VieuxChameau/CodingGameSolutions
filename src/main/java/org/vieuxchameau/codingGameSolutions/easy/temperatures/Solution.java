package org.vieuxchameau.codingGameSolutions.easy.temperatures;

import java.util.Scanner;

import static java.lang.Math.abs;

class Solution {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfTemperatures = in.nextInt(); // the number of temperatures to analyse
		if (numberOfTemperatures == 0) {
			System.out.println(0);
			return;
		}

		in.nextLine();
		final String temperatures = in.nextLine(); // the N temperatures expressed as integers ranging from -273 to 5526
		System.out.println(getTemperatureClosestToZero(temperatures));
	}

	private static int getTemperatureClosestToZero(final String temperatures) {
		int minimalTemperature = Integer.MAX_VALUE;

		for (String temperature : temperatures.split(" ")) {
			final int currentTemperature = Integer.parseInt(temperature);
			final int absoluteTemperature = abs(currentTemperature);
			final int absoluteMinimal = abs(minimalTemperature);
			if (absoluteTemperature < absoluteMinimal) {
				minimalTemperature = currentTemperature;
			} else if (absoluteTemperature == absoluteMinimal) {
				minimalTemperature = Math.max(currentTemperature, minimalTemperature);
			}
		}
		return minimalTemperature;
	}
}
