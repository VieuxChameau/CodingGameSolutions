package org.vieuxchameau.codingGameSolutions.medium.networkCabling;

import java.util.Scanner;

import static java.util.Arrays.sort;

class Solution {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfBuildings = in.nextInt(); // The number N of buildings which need to be connected to the optical fiber network
		final int[] yValues = new int[numberOfBuildings];

		int maximalX = Integer.MIN_VALUE;
		int minimumX = Integer.MAX_VALUE;
		for (int i = 0; i < numberOfBuildings; i++) {
			final int X = in.nextInt(); // The coordinates x and y of the buildings
			if (X > maximalX) {
				maximalX = X;
			}
			if (X < minimumX) {
				minimumX = X;
			}

			yValues[i] = in.nextInt();
		}

		final int averageY = median(yValues);

		long minimalLength = maximalX - minimumX;

		for (final int y : yValues) {
			minimalLength += (y > averageY ? y - averageY : averageY - y);
		}
		System.out.println(minimalLength);
	}

	public static int median(final int[] values) {
		sort(values);
		final int middleIndex = values.length / 2;

		if (values.length % 2 == 1) {
			return values[middleIndex];
		} else {
			return (values[middleIndex - 1] + values[middleIndex]) / 2;
		}
	}
}
