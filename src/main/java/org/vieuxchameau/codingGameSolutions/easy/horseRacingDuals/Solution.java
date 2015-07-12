package org.vieuxchameau.codingGameSolutions.easy.horseRacingDuals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number N of horses
        List<Integer> strengths = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            strengths.add(in.nextInt()); // the strength of an horse
        }

        Collections.sort(strengths);

        int prev = Integer.MAX_VALUE;
        int minimumDifference = Integer.MAX_VALUE;
        for (Integer Pi : strengths) {
            int currentDifference = Pi < prev ? prev - Pi : Pi - prev;
            if (currentDifference < minimumDifference) {
                minimumDifference = currentDifference;
            }
            prev = Pi;
        }
        System.out.println(minimumDifference);
    }
}
