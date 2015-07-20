package org.vieuxchameau.codingGameSolutions.easy.powerOfThor;

import java.util.Scanner;

class Player {

	private static final String UP = "N";

	private static final String DOWN = "S";

	private static final String LEFT = "W";

	private static final String RIGHT = "E";

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int lightHorizontalPosition = in.nextInt(); // the X position of the light of power
		final int lightVerticalPosition = in.nextInt(); // the Y position of the light of power
		int thorHorizontalPosition = in.nextInt(); // Thor's starting X position
		int thorVerticalPosition = in.nextInt(); // Thor's starting Y position

		while (true) {
			in.nextInt(); // The level of Thor's remaining energy, representing the number of moves he can still make.

			String directionToMove = "";
			if (thorVerticalPosition > lightVerticalPosition) {
				directionToMove = UP;
				thorVerticalPosition -= 1;
			} else if (thorVerticalPosition < lightVerticalPosition) {
				directionToMove = DOWN;
				thorVerticalPosition += 1;
			}

			if (thorHorizontalPosition > lightHorizontalPosition) {
				directionToMove += LEFT;
				thorHorizontalPosition -= 1;
			} else if (thorHorizontalPosition < lightHorizontalPosition) {
				directionToMove += RIGHT;
				thorHorizontalPosition += 1;
			}

			System.out.println(directionToMove);
		}
	}
}