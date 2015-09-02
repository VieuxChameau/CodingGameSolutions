package org.vieuxchameau.codingGameSolutions.medium.bender;

import java.util.*;

import static java.lang.String.format;
import static java.util.Arrays.asList;

class Solution {

	private static final char BENDER = '@';
	private static final char TARGET = '$';
	private static final char INVERTER = 'I';
	private static final char TELEPORTER = 'T';
	private static final char BEER = 'B';
	private static final char BLANK_AREA = ' ';
	private static final char BREAKABLE_OBSTACLE = 'X';
	private static final List<Character> OBSTACLES = new ArrayList<>(asList(BREAKABLE_OBSTACLE, '#'));
	private static final List<Character> DIRECTION_PRIORITIES = new ArrayList<>(asList('S', 'E', 'N', 'W'));

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfLines = in.nextInt();
		final int numberOfColumns = in.nextInt();
		in.nextLine();

		final char[][] map = createMap(in, numberOfLines, numberOfColumns);
		final Position benderPosition = findPosition(map, BENDER);

		final Bender bender = new Bender(benderPosition, map);
		try {
			bender.move();
			bender.displayMoves();
		} catch (IllegalStateException e) {
			System.out.println("LOOP");
		}
	}

	private static Position findPosition(final char[][] map, final char target) {
		for (int y = 0; y < map.length; y++) {
			final char[] row = map[y];
			for (int x = 0; x < row.length; x++) {
				if (row[x] == target) {
					return new Position(x, y);
				}
			}
		}

		throw new IllegalStateException("Bender is not present");
	}

	private static char[][] createMap(final Scanner in, final int numberOfLines, final int numberOfColumns) {
		final char[][] map = new char[numberOfLines][numberOfColumns];
		for (int i = 0; i < numberOfLines; i++) {
			final String row = in.nextLine();
			map[i] = row.toCharArray();
		}
		return map;
	}

	static class Bender {
		Position benderPosition;
		final Position targetPosition;
		final char[][] map;
		final Set<String> visitedStates = new HashSet<>();
		final List<String> moves = new LinkedList<>();
		boolean isBreakerMode = false;
		boolean isInvertMode = false;
		int obstaclesDestroyed = 0;
		char direction = 'S';

		Bender(final Position benderPosition, final char[][] map) {
			this.benderPosition = benderPosition;
			this.map = map;
			this.targetPosition = findPosition(map, TARGET);
			map[benderPosition.y][benderPosition.x] = BLANK_AREA;
		}

		private void move() {
			do {
				Position nextPosition = getNextPosition();
				char nextCell = getMapCell(nextPosition);

				char directionToTest = Solution.DIRECTION_PRIORITIES.get(0);
				while (isIndestructibleObstacleCell(nextCell)) {
					direction = directionToTest;
					nextPosition = getNextPosition();
					nextCell = getMapCell(nextPosition);
					directionToTest = getNexDirection(directionToTest);
				}
				moveTo(nextPosition, nextCell);
			} while (!isTargetPosition());
		}

		private void moveTo(final Position nextPosition, final char nextCell) {
			addCurrentState();
			if (isDestroyableObstacleCell(nextCell)) {
				applyMove(nextPosition);
				destroyObstacle(nextPosition);
			} else if (isInverterCell(nextCell)) {
				applyMove(nextPosition);
				revertDirectionPriorities();
			} else if (isBeerCell(nextCell)) {
				applyMove(nextPosition);
				toggleBreakerMode();
			} else if (isTeleporterCell(nextCell)) {
				destroyObstacle(nextPosition); // hide the teleporter to find the other teleporter
				final Position teleportedPosition = findPosition(map, TELEPORTER);
				fillMapCell(TELEPORTER, nextPosition); // reset the erased teleporter
				applyMove(teleportedPosition);
			} else if (isDirectionCell(nextCell)) {
				applyMove(nextPosition);
				direction = nextCell;
			} else {
				applyMove(nextPosition);
			}
		}

		private void toggleBreakerMode() {
			isBreakerMode = !isBreakerMode;
		}

		private void fillMapCell(final char cell, final Position position) {
			map[position.y][position.x] = cell;
		}

		private void destroyObstacle(final Position position) {
			map[position.y][position.x] = BLANK_AREA;
			++obstaclesDestroyed;
		}

		private boolean isTargetPosition() {
			return benderPosition.equals(targetPosition);
		}

		private char getNexDirection(final char dir) {
			return Solution.DIRECTION_PRIORITIES.get(Solution.DIRECTION_PRIORITIES.indexOf(dir) + 1);
		}

		private void addCurrentState() {
			final boolean isNewElement = visitedStates.add(getState());
			if (!isNewElement) {
				throw new IllegalStateException("State already inserted, loop detected");
			}
		}

		private String getState() {
			return format("%s;%s;%d;%d;%c;%d", isBreakerMode, isInvertMode, benderPosition.x, benderPosition.y, direction, obstaclesDestroyed);
		}

		private char getMapCell(final Position position) {
			return map[position.y][position.x];
		}

		private Position getNextPosition() {
			int x = benderPosition.x;
			int y = benderPosition.y;
			switch (direction) {
				case 'S':
					y += 1;
					break;
				case 'E':
					x += 1;
					break;
				case 'N':
					y -= 1;
					break;
				case 'W':
					x -= 1;
					break;
			}

			return new Position(x, y);
		}

		private void applyMove(final Position nextPosition) {
			String move = null;
			switch (direction) {
				case 'S':
					move = "SOUTH";
					break;
				case 'E':
					move = "EAST";
					break;
				case 'N':
					move = "NORTH";
					break;
				case 'W':
					move = "WEST";
					break;
			}
			moves.add(move);
			benderPosition = nextPosition;
		}

		private boolean isDirectionCell(final char nextCell) {
			return DIRECTION_PRIORITIES.contains(nextCell);
		}

		private boolean isTeleporterCell(final char nextCell) {
			return nextCell == TELEPORTER;
		}

		private boolean isBeerCell(final char nextCell) {
			return nextCell == BEER;
		}

		private void revertDirectionPriorities() {
			Collections.reverse(Solution.DIRECTION_PRIORITIES);
			isInvertMode = !isInvertMode;
		}

		private boolean isInverterCell(final char nextCell) {
			return nextCell == INVERTER;
		}

		private boolean isIndestructibleObstacleCell(final char nextCell) {
			return OBSTACLES.contains(nextCell) && !isDestroyableObstacleCell(nextCell);
		}

		private boolean isDestroyableObstacleCell(final char nextCell) {
			return isBreakerMode && nextCell == BREAKABLE_OBSTACLE;
		}

		private void displayMoves() {
			moves.stream().forEach(System.out::println);
		}
	}

	static class Position {
		final int x;
		final int y;

		private Position(final int x, final int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			final Position position = (Position) o;
			return Objects.equals(x, position.x) &&
					Objects.equals(y, position.y);
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}

