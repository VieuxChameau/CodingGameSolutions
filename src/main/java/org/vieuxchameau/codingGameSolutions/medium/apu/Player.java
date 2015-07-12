package org.vieuxchameau.codingGameSolutions.medium.apu;

import java.util.Scanner;

import static java.lang.String.format;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        in.nextLine();
        int height = in.nextInt(); // the number of cells on the Y axis
        in.nextLine();
        char[][] map = new char[height][width];
        for (int y = 0; y < height; ++y) {
            String line = in.nextLine(); // width characters, each either 0 or .
            for (int x = 0; x < width; ++x) {
                map[y][x] = line.charAt(x);
            }
        }


        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (isPowerNode(map[y][x])) {
                    final int x1 = getHorizontalNeighbor(map[y], x + 1);
                    final int y1 = x1 != -1 ? y : -1;

                    final int y2 = getVerticalNeighbor(map, y + 1, x);
                    final int x2 = y2 != -1 ? x : -1;
                    // Three coordinates: a node, its right neighbor, its bottom neighbor
                    System.out.println(format("%d %d %d %d %d %d", x, y, x1, y1, x2, y2));
                }
            }
        }
    }

    private static int getVerticalNeighbor(char[][] map, int y, int x) {
        if (y < map.length) {
            if (isPowerNode(map[y][x])) {
                return y;
            }
            return getVerticalNeighbor(map, y + 1, x);
        }
        return -1;
    }

    private static int getHorizontalNeighbor(char[] mapLine, int x) {
        if (x < mapLine.length) {
            if (isPowerNode(mapLine[x])) {
                return x;
            }
            return getHorizontalNeighbor(mapLine, x + 1);
        }
        return -1;
    }

    private static boolean isPowerNode(char node) {
        return node == '0';
    }

}
