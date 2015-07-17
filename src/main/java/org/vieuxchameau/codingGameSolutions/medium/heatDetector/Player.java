package org.vieuxchameau.codingGameSolutions.medium.heatDetector;

import java.util.Scanner;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt(); // batman horizontal pos
        int Y0 = in.nextInt(); // batman vertical pos

        Range xRange = new Range(0, W);
        Range yRange = new Range(0, H);
        while (true) {
            String BOMB_DIR = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
            adaptXRange(X0, xRange, BOMB_DIR);
            adaptYRange(Y0, yRange, BOMB_DIR);
            X0 = xRange.getMidRange();
            Y0 = yRange.getMidRange();
            System.out.println(X0 + " " + Y0); // the location of the next window Batman should jump to.
        }
    }

    private static void adaptYRange(int y, Range yRange, String bomb_dir) {
        if (bomb_dir.contains("U")) {
            yRange.setMax(y);
        } else if (bomb_dir.contains("D")) {
            yRange.setMin(y);
        } else {
            yRange.close(y);
        }
    }

    private static void adaptXRange(int x, Range xRange, String bomb_dir) {
        if (bomb_dir.contains("L")) {
            xRange.setMax(x);
        } else if (bomb_dir.contains("R")) {
            xRange.setMin(x);
        } else {
            xRange.close(x);
        }
    }

    static class Range {
        private int min;
        private int max;

        Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        int getMidRange() {
            return min == max ? min : ((max - min) / 2) + min;
        }

        public void close(int x) {
            min = max = x;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }
}