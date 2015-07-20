package org.vieuxchameau.codingGameSolutions.medium.conwaySequence;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.joining;

class Solution {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int startingNumber = in.nextInt(); //  The original number R of the sequence.
		int numberOfLineToDisplay = in.nextInt();//The line L to display. The index of the first line is 1.

		List<Integer> result = new ArrayList<>(1);
		result.add(startingNumber);
		while (--numberOfLineToDisplay > 0) {
			final List<Integer> lineResult = new ArrayList<>(result.size());
			Integer previousValue = result.get(0);
			int count = 1;
			for (int i = 1, size = result.size(); i < size; i++) {
				final Integer value = result.get(i);
				if (value.equals(previousValue)) {
					++count;
				} else {
					addCountForValue(lineResult, previousValue, count);
					previousValue = value;
					count = 1;
				}
			}
			addCountForValue(lineResult, previousValue, count);
			result = lineResult;
		}
		System.out.println(result.stream().map(String::valueOf).collect(joining(" ")));
	}

	private static void addCountForValue(final List<Integer> lineResult, final Integer value, final int count) {
		lineResult.add(count);
		lineResult.add(value);
	}
}



