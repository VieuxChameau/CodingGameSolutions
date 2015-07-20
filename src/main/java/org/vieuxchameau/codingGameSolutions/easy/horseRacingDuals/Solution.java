package org.vieuxchameau.codingGameSolutions.easy.horseRacingDuals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Solution {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfHorses = in.nextInt(); // Number N of horses
		final List<Integer> horsesStrengths = new ArrayList<>();
		for (int i = 0; i < numberOfHorses; i++) {
			horsesStrengths.add(in.nextInt()); // the strength of an horse
		}

		Collections.sort(horsesStrengths);

		int previousStrength = Integer.MAX_VALUE;
		int minimalDifference = Integer.MAX_VALUE;
		for (Integer strength : horsesStrengths) {
			final int difference = getDifference(previousStrength, strength);
			if (difference < minimalDifference) {
				minimalDifference = difference;
			}
			previousStrength = strength;
		}
		System.out.println(minimalDifference);
	}

	private static int getDifference(final int previousStrength, final int strength) {
		return strength < previousStrength ? previousStrength - strength : strength - previousStrength;
	}
}
