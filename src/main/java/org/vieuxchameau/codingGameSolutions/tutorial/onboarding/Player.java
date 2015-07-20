package org.vieuxchameau.codingGameSolutions.tutorial.onboarding;

import java.util.Scanner;

class Player {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);

		while (true) {
			final String firstEnemyName = in.next(); // name of enemy 1
			final int firstEnemyDistance = in.nextInt(); // distance to enemy 1
			final String secondEnemyName = in.next(); // name of enemy 2
			final int secondEnemyDistance = in.nextInt(); // distance to enemy 2

			System.out.println((firstEnemyDistance < secondEnemyDistance) ? firstEnemyName : secondEnemyName);
		}
	}
}
