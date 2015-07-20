package org.vieuxchameau.codingGameSolutions.easy.skynet;

import java.util.Scanner;

class Player {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int roadSizeBeforeGap = in.nextInt(); // the length of the road before the gap.
		final int lengthOfGap = in.nextInt(); // the length of the gap.
		in.nextInt(); // L : the length of the landing platform.

		final int endOfGap = roadSizeBeforeGap + lengthOfGap;
		while (true) {
			final int motorbikeSpeed = in.nextInt(); // the motorbike's speed.
			final int motorbikePosition = in.nextInt(); // the position on the road of the motorbike.

			if (gapIsPassed(endOfGap, motorbikePosition)) {
				slowDown();
			} else {
				if (shouldJump(roadSizeBeforeGap, motorbikePosition)) {
					jump();
				} else {
					if (motorbikeSpeedIsSufficient(lengthOfGap, motorbikeSpeed)) {
						keepSameSpeed();
					} else if (motorbikeSpeedIsTooFast(lengthOfGap, motorbikeSpeed)) {
						slowDown();
					} else {
						speedUp();
					}
				}
			}
		}
	}

	private static boolean shouldJump(final int roadSizeBeforeGap, final int motorbikePosition) {
		return motorbikePosition + 1 == roadSizeBeforeGap;
	}

	private static boolean gapIsPassed(final int endOfGap, final int motorbikePosition) {
		return motorbikePosition >= endOfGap;
	}

	private static boolean motorbikeSpeedIsTooFast(final int lengthOfGap, final int motorbikeSpeed) {
		return motorbikeSpeed > lengthOfGap;
	}

	private static boolean motorbikeSpeedIsSufficient(final int lengthOfGap, final int motoSpeed) {
		return motoSpeed == lengthOfGap + 1;
	}

	private static void keepSameSpeed() {
		System.out.println("WAIT");
	}

	private static void speedUp() {
		System.out.println("SPEED");
	}

	private static void jump() {
		System.out.println("JUMP");
	}

	private static void slowDown() {
		System.out.println("SLOW");
	}
}
