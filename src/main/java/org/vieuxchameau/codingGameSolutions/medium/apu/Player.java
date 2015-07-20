package org.vieuxchameau.codingGameSolutions.medium.apu;

import java.util.Scanner;

import static java.lang.String.format;

class Player {

	private static final char POWER_NODE = '0';

	private static final int NEIGHBOUR_NOT_FOUND = -1;

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int width = in.nextInt(); // the number of cells on the X axis
		in.nextLine();
		final int height = in.nextInt(); // the number of cells on the Y axis
		in.nextLine();
		final char[][] map = new char[height][width];
		for (int y = 0; y < height; ++y) {
			final String line = in.nextLine(); // width characters, each either 0 or .
			final char[] mapRow = map[y];
			for (int x = 0; x < width; ++x) {
				mapRow[x] = line.charAt(x);
			}
		}

		showPowerNodeNeighbours(map);
	}

	private static void showPowerNodeNeighbours(final char[][] map) {
		for (int y = 0, height = map.length; y < height; ++y) {
			final char[] line = map[y];
			for (int x = 0, width = line.length; x < width; ++x) {
				if (isPowerNode(line[x])) {
					final String rightNeighbourPosition = getRightNeighbourPosition(line, x, y);
					final String bottomNeighbourPosition = getBottomNeighbourPosition(map, x, y);

					// Three coordinates: a node, its right neighbour, its bottom neighbour
					System.out.println(format("%s %s %s", positionAsString(x, y), rightNeighbourPosition, bottomNeighbourPosition));
				}
			}
		}
	}

	private static String getBottomNeighbourPosition(final char[][] map, final int x, final int y) {
		final int y2 = findBottomNeighbour(map, y + 1, x);
		final int x2 = y2 != NEIGHBOUR_NOT_FOUND ? x : NEIGHBOUR_NOT_FOUND;
		return positionAsString(x2, y2);
	}

	private static String getRightNeighbourPosition(final char[] line, final int x, final int y) {
		final int x1 = findRightNeighbour(line, x + 1);
		final int y1 = x1 != NEIGHBOUR_NOT_FOUND ? y : NEIGHBOUR_NOT_FOUND;
		return positionAsString(x1, y1);
	}

	private static int findBottomNeighbour(char[][] map, int y, int x) {
		if (y < map.length) {
			if (isPowerNode(map[y][x])) {
				return y;
			}
			return findBottomNeighbour(map, y + 1, x);
		}
		return NEIGHBOUR_NOT_FOUND;
	}

	private static int findRightNeighbour(char[] mapLine, int x) {
		if (x < mapLine.length) {
			if (isPowerNode(mapLine[x])) {
				return x;
			}
			return findRightNeighbour(mapLine, x + 1);
		}
		return NEIGHBOUR_NOT_FOUND;
	}

	private static String positionAsString(final int x, final int y) {
		return format("%d %d", x, y);
	}

	private static boolean isPowerNode(final char node) {
		return node == POWER_NODE;
	}
}
