package org.vieuxchameau.codingGameSolutions.easy.theDescent;

import java.util.Scanner;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int SX = in.nextInt();
            in.nextInt(); // SY

            int max = -1;
            int index = -1;
            for (int i = 0; i < 8; i++) {
                // represents the height of one mountain, from 9 to 0. Mountain heights are provided from left to right.
                int MH = in.nextInt();
                if (MH > max) {
                    max = MH;
                    index = i;
                }
            }

            System.out.println(SX == index ? "FIRE" : "HOLD");
        }
    }
}