package org.vieuxchameau.codingGameSolutions.optimization.powerOfThor;

class Player {
	public static void main(String a[]) {
		java.util.Scanner i = new java.util.Scanner(System.in);
		int X = i.nextInt(), Y = i.nextInt(), x = i.nextInt(), y = i.nextInt();
		while (true) {
			System.out.println((y > Y ? "N" : y < Y ? "S" : "") + (x > X ? "W" : x < X ? "E" : ""));
			x += X == x ? 0 : x > X ? -1 : 1;
			y += Y == y ? 0 : y > Y ? -1 : 1;
		}
	}
}