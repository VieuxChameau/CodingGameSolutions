package org.vieuxchameau.codingGameSolutions.easy.skynet;

import java.util.Scanner;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int R = in.nextInt(); // the length of the road before the gap.
        int G = in.nextInt(); // the length of the gap.
        in.nextInt(); // L : the length of the landing platform.

        final int endOfGap = R + G;
        while (true) {
            int S = in.nextInt(); // the motorbike's speed.
            int X = in.nextInt(); // the position on the road of the motorbike.

            if (X >= endOfGap) {
                System.out.println("SLOW");
            } else {
                if (X + 1 == R) {
                    System.out.println("JUMP");
                } else {
                    if (S == G + 1) {
                        System.out.println("WAIT");
                    } else if (S > G) {
                        System.out.println("SLOW");
                    } else {
                        System.out.println("SPEED");
                    }
                }
            }
        }
    }
}
