package org.vieuxchameau.codingGameSolutions.medium.stockExchangeLosses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        List<Integer> stockValues = getStockValues(in.nextLine());

        int maxStockValue = 0;
        int maximalLost = 0;
        while (stockValues.size() > 1) {
            int firstStockValue = stockValues.get(0);
            if (firstStockValue > maxStockValue) {
                final List<Integer> tailStockValues = tail(stockValues);
                final Integer minStockValue = Collections.min(tailStockValues);
                maximalLost = min(maximalLost, minStockValue > firstStockValue ? 0 : minStockValue - firstStockValue);
                maxStockValue = firstStockValue;
            } else {
                stockValues = tail(stockValues);
            }
        }
        System.out.println(maximalLost);
    }

    private static List<Integer> tail(List<Integer> stockValues) {
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
