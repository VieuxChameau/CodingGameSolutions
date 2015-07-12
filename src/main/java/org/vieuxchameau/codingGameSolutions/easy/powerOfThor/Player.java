package org.vieuxchameau.codingGameSolutions.easy.powerOfThor;

import java.util.Scanner;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int LX = in.nextInt(); // the X position of the light of power
        int LY = in.nextInt(); // the Y position of the light of power
        int TX = in.nextInt(); // Thor's starting X position
        int TY = in.nextInt(); // Thor's starting Y position

        while (true) {
            in.nextInt();

            if (LX == TX) {
                if (TY > LY) {
                    System.out.println("N");
                    TY -= 1;
                } else {
                    System.out.println("S");
                    TY += 1;
                }
            } else if (LY == TY) {
                if (TX > LX) {
                    System.out.println("W");
                    TX -= 1;
                } else {
                    System.out.println("E");
                    TX += 1;
                }
            } else {
                String d = "";
                if (TY > LY) {
                    d += "N";
                    TY -= 1;
                } else {
                    d += "S";
                    TY += 1;
                }

                if (TX > LX) {
                    d += "W";
                    TX -= 1;
                } else {
                    d += "E";
                    TX += 1;
                }
                System.out.println(d);
            }
        }
    }
}
