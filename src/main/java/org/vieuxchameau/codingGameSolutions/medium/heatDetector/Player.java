package org.vieuxchameau.codingGameSolutions.medium.heatDetector;

import java.util.Scanner;

class Player {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int buildingWidth = in.nextInt(); // width of the building.
		final int buildingHeight = in.nextInt(); // height of the building.
		in.nextInt(); // maximum number of turns before game over.
		int batmanHorizontalPosition = in.nextInt(); // batman horizontal pos
		int batmanVerticalPosition = in.nextInt(); // batman vertical pos

		final Range horizontalRange = new Range(0, buildingWidth);
		final Range verticalRange = new Range(0, buildingHeight);
		while (true) {
			final String bombDirection = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
			adaptHorizontalRange(batmanHorizontalPosition, horizontalRange, bombDirection);
			adaptVerticalRange(batmanVerticalPosition, verticalRange, bombDirection);
			batmanHorizontalPosition = horizontalRange.getMidRange();
			batmanVerticalPosition = verticalRange.getMidRange();
			System.out.println(batmanHorizontalPosition + " " + batmanVerticalPosition); // the location of the next window Batman should jump to.
		}
	}

	private static void adaptVerticalRange(final int batmanVerticalPosition, final Range verticalRange, final String bombDirection) {
		if (bombDirection.contains("U")) {
			verticalRange.setMax(batmanVerticalPosition);
		} else if (bombDirection.contains("D")) {
			verticalRange.setMin(batmanVerticalPosition);
		} else {
			verticalRange.close(batmanVerticalPosition);
		}
	}

	private static void adaptHorizontalRange(final int batmanHorizontalPosition, final Range horizontalRange, final String bombDirection) {
		if (bombDirection.contains("L")) {
			horizontalRange.setMax(batmanHorizontalPosition);
		} else if (bombDirection.contains("R")) {
			horizontalRange.setMin(batmanHorizontalPosition);
		} else {
			horizontalRange.close(batmanHorizontalPosition);
		}
	}

	static class Range {
		private int min;

		private int max;

		Range(final int min, final int max) {
			this.min = min;
			this.max = max;
		}

		int getMidRange() {
			return min == max ? min : ((max - min) / 2) + min;
		}

		void close(int x) {
			min = max = x;
		}

		void setMax(int max) {
			this.max = max;
		}

		void setMin(int min) {
			this.min = min;
		}
	}
}