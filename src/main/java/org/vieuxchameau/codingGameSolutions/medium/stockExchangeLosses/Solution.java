package org.vieuxchameau.codingGameSolutions.medium.stockExchangeLosses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

class Solution {

	private static final int NO_LOSS_VALUE = 0;

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		in.nextInt(); //  the number n of stock values available.
		in.nextLine();
		List<Integer> stockValues = getStockValues(in.nextLine()); //  the stock values arranged in order, from the date of their introduction on the stock market v1 until the last known value vn. The values are integers.

		int maxStockValue = 0;
		int maximalLoss = NO_LOSS_VALUE;
		while (stockValues.size() > 1) {
			final int headStockValue = stockValues.get(0);
			if (headStockValue > maxStockValue) {
				final List<Integer> tailStockValues = tail(stockValues);
				final Integer minimalStockValueInTail = Collections.min(tailStockValues);
				maximalLoss = min(maximalLoss, getLossValue(headStockValue, minimalStockValueInTail));
				maxStockValue = headStockValue;
			} else {
				stockValues = tail(stockValues);
			}
		}
		System.out.println(maximalLoss);
	}

	private static int getLossValue(final int headStockValue, final Integer minimalStockValueInTail) {
		return hasNoLoss(headStockValue, minimalStockValueInTail) ? NO_LOSS_VALUE : minimalStockValueInTail - headStockValue;
	}

	private static boolean hasNoLoss(final int headStockValue, final Integer minimalStockValueInTail) {
		return minimalStockValueInTail > headStockValue;
	}

	private static List<Integer> tail(final List<Integer> stockValues) {
		return stockValues.subList(1, stockValues.size());
	}

	private static List<Integer> getStockValues(final String aggregatedStockValues) {
		final List<Integer> stockValues = new ArrayList<>();
		for (String stockValue : aggregatedStockValues.split(" ")) {
			final Integer intValue = Integer.valueOf(stockValue);
			stockValues.add(intValue);
		}
		return stockValues;
	}
}
