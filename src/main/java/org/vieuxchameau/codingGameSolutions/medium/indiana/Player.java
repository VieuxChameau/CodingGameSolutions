package org.vieuxchameau.codingGameSolutions.medium.indiana;

import java.util.Scanner;

class Player {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int mapWidth = in.nextInt(); // number of columns.
		final int mapHeight = in.nextInt(); // number of rows.
		in.nextLine();
		final RoomType[][] map = new RoomType[mapHeight][mapWidth];
		for (int y = 0; y < mapHeight; y++) {
			final RoomType[] mapRow = map[y];
			final String line = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.

			final String[] rowCells = line.split(" ");
			for (int x = 0; x < mapWidth; x++) {
				mapRow[x] = RoomType.fromIdentifier(rowCells[x]);
			}
		}

		in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).
		in.nextLine();

		while (true) {
			final int indiHorizontalPosition = in.nextInt(); //  two integers to indicate Indy's current position on the grid.
			final int indiVerticalPosition = in.nextInt();
			final String entranceDirection = in.next(); // a single word indicating Indy's entrance point into the current room: TOP if Indy enters from above, LEFT if Indy enters from the left and RIGHT if Indy enters from the right.
			in.nextLine();

			final RoomType indiRoom = map[indiVerticalPosition][indiHorizontalPosition];
			final Direction exitDirection = indiRoom.getExitDirection(Direction.valueOf(entranceDirection));
			showCoordinateOnExit(indiHorizontalPosition, indiVerticalPosition, exitDirection);
		}
	}

	private static void showCoordinateOnExit(int indiHorizontalPosition, int indiVerticalPosition, final Direction exitDirection) {
		switch (exitDirection) {
			case LEFT:
				indiHorizontalPosition--;
				break;
			case RIGHT:
				indiHorizontalPosition++;
				break;
			case DOWN:
				indiVerticalPosition++;
				break;
		}

		System.out.println(String.format("%d %d", indiHorizontalPosition, indiVerticalPosition));
	}

	enum RoomType {
		BLOCK("0") {
			@Override
			public Direction getExitDirection(final Direction entranceDirection) {
				return null;
			}
		},
		CROSS("1"),
		HORIZONTAL_TUBE("2") {
			@Override
			public Direction getExitDirection(final Direction entranceDirection) {
				return entranceDirection == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
			}
		},
		VERTICAL_TUBE("3"),
		DOUBLE_TUBE("4") {
			@Override
			public Direction getExitDirection(final Direction entranceDirection) {
				if (entranceDirection == Direction.TOP) {
					return Direction.LEFT;
				} else if (entranceDirection == Direction.RIGHT) {
					return Direction.DOWN;
				}
				return null;
			}
		},
		REVERSE_DOUBLE_TUBE("5") {
			@Override
			public Direction getExitDirection(final Direction entranceDirection) {
				if (entranceDirection == Direction.TOP) {
					return Direction.RIGHT;
				} else if (entranceDirection == Direction.LEFT) {
					return Direction.DOWN;
				}
				return null;
			}
		},
		REVERSE_T("6") {
			@Override
			public Direction getExitDirection(final Direction entranceDirection) {
				return entranceDirection == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
			}
		},
		RIGHT_T("7"),
		T("8"),
		LEFT_T("9"),
		LEFT_TUBE("10") {
			@Override
			public Direction getExitDirection(final Direction entranceDirection) {
				return entranceDirection == Direction.TOP ? Direction.LEFT : null;
			}
		},
		RIGHT_TUBE("11") {
			@Override
			public Direction getExitDirection(final Direction entranceDirection) {
				return entranceDirection == Direction.TOP ? Direction.RIGHT : null;
			}
		},
		REVERSE_LEFT_TUBE("12"),
		REVERSE_RIGHT_TUBE("13");

		private final String identifier;

		RoomType(final String identifier) {

			this.identifier = identifier;
		}

		static RoomType fromIdentifier(final String identifier) {
			for (final RoomType roomType : values()) {
				if (roomType.identifier.equals(identifier)) {
					return roomType;
				}
			}
			return null;
		}

		public Direction getExitDirection(final Direction entranceDirection) {
			return Direction.DOWN;
		}
	}

	enum Direction {
		TOP,
		LEFT,
		RIGHT,
		DOWN
	}
}
