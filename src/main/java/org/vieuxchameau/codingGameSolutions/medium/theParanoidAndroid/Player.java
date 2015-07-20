package org.vieuxchameau.codingGameSolutions.medium.theParanoidAndroid;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Player {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		in.nextInt(); // number of floors
		in.nextInt(); // width of the area
		in.nextInt(); // maximum number of rounds
		final int exitFloor = in.nextInt(); // floor on which the exit is found
		final int exitPosition = in.nextInt(); // position of the exit on its floor
		in.nextInt(); // number of generated clones
		in.nextInt(); // ignore (always zero)
		final int numberOfElevators = in.nextInt(); // number of elevators

		final Map<Integer, Integer> targetPositionsByFloor = new HashMap<>(numberOfElevators + 1);
		for (int i = 0; i < numberOfElevators; i++) {
			final int elevatorFloor = in.nextInt(); // floor on which this elevator is found
			final int elevatorPosition = in.nextInt(); // position of the elevator on its floor
			targetPositionsByFloor.put(elevatorFloor, elevatorPosition);
		}

		targetPositionsByFloor.put(exitFloor, exitPosition);

		leadDroid(in, targetPositionsByFloor);
	}

	private static void leadDroid(final Scanner in, final Map<Integer, Integer> targetPositionsByFloor) {
		while (true) {
			final int leadingCloneFloor = in.nextInt(); // floor of the leading clone
			final int leadingClonePosition = in.nextInt(); // position of the leading clone on its floor
			final String leadingCloneDirection = in.next(); // direction of the leading clone: LEFT or RIGHT

			final Integer targetPosition = targetPositionsByFloor.get(leadingCloneFloor);
			if (targetPosition != null) {
				if (leadingCloneGoInGoodDirection(leadingClonePosition, leadingCloneDirection, targetPosition)) {
					cloneWait();
				} else {
					cloneBlock();
				}
				targetPositionsByFloor.remove(leadingCloneFloor);
			} else {
				cloneWait();
			}
		}
	}

	private static boolean leadingCloneGoInGoodDirection(final int leadingClonePosition, final String leadingCloneDirection, final Integer targetPosition) {
		return ("LEFT".equals(leadingCloneDirection) && targetPosition < leadingClonePosition) || ("RIGHT".equals(leadingCloneDirection) && leadingClonePosition < targetPosition);
	}

	private static void cloneWait() {
		System.out.println("WAIT");
	}

	private static void cloneBlock() {
		System.out.println("BLOCK");
	}
}
