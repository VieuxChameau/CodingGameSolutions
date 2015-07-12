package org.vieuxchameau.codingGameSolutions.easy.temperatures;

import java.util.Scanner;

import static java.lang.Math.abs;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the number of temperatures to analyse
        if (N == 0) {
            System.out.println(0);
            return;
        }

        in.nextLine();
        String TEMPS = in.nextLine(); // the N temperatures expressed as integers ranging from -273 to 5526
        int minTemp = Integer.MAX_VALUE;
        for (String temp : TEMPS.split(" ")) {
            int currentTemp = Integer.parseInt(temp);
            int absTemp = abs(currentTemp);
            int absMin = abs(minTemp);
            if (absTemp < absMin) {
                minTemp = currentTemp;
            } else if (absTemp == absMin) {
                minTemp = Math.max(currentTemp, minTemp);
            }
        }
        System.out.println(minTemp);
    }
}
