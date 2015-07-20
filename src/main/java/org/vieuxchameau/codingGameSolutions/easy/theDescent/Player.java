package org.vieuxchameau.codingGameSolutions.easy.theDescent;

import java.util.Scanner;

import static java.lang.Integer.MIN_VALUE;

class Player {

	private static final int NUMBER_OF_MOUNTAINS = 8;

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);

		while (true) {
			final int shipPosition = in.nextInt(); // the horizontal coordinate of your ship (0 to 7).
			in.nextInt(); // SY : the altitude at which your ship is advancing in kilometers (10 to 1).

			int maximalHeight = MIN_VALUE;
			int positionToFire = -1;
			for (int i = 0; i < NUMBER_OF_MOUNTAINS; i++) {
				final int mountainHeight = in.nextInt(); // represents the height of one mountain, from 9 to 0. Mountain heights are provided from left to right.
				if (mountainHeight > maximalHeight) {
					maximalHeight = mountainHeight;
					positionToFire = i;
				}
			}

			System.out.println(shipPosition == positionToFire ? "FIRE" : "HOLD");
		}
	}
}