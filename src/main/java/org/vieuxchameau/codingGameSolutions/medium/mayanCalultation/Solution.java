package org.vieuxchameau.codingGameSolutions.medium.mayanCalultation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.math.BigInteger.ZERO;

class Solution {

	private static final BigInteger BASE = new BigInteger("20");
	private static final int NUMBER_OF_MAYANS_NUMERALS = 20;
	private static final int THRESHOLD_POWER = 15;

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numeralWidth = in.nextInt(); // the width L  of a mayan numeral.
		final int numeralHeight = in.nextInt();  // the height H of a mayan numeral.
		final Map<BigInteger, MayanNumeral> mayanNumerals = initMayanNumerals(numeralHeight);
		for (int i = 0; i < numeralHeight; i++) {
			final String numeralLine = in.next(); //  the ASCII representation of the 20 mayan numerals. Each line is (20 x L) characters long.
			addLineToRepresentation(numeralWidth, numeralLine, mayanNumerals);
		}

		final MayanNumber leftNumber = getMayanNumber(in, numeralHeight, mayanNumerals);
		final MayanNumber rightNumber = getMayanNumber(in, numeralHeight, mayanNumerals);

		final String operation = in.next(); // the operation to carry err:  *, /, +, or -

		final BigInteger result = compute(operation, leftNumber, rightNumber);
		if (result.equals(ZERO)) {
			showZero(mayanNumerals);
			return;
		}
		showResultAsMayanNumber(result, mayanNumerals);
	}

	private static void showZero(final Map<BigInteger, MayanNumeral> mayanNumerals) {
		mayanNumerals.get(ZERO).representation.forEach(System.out::println);
	}

	private static void showResultAsMayanNumber(BigInteger result, final Map<BigInteger, MayanNumeral> mayanNumerals) {
		BigInteger divisor = BASE.pow(THRESHOLD_POWER);
		boolean startShowing = false;
		while (!result.equals(ZERO)) {
			final BigInteger resultOfPower = result.divide(divisor);
			if (startShowing || !resultOfPower.equals(ZERO)) {
				final MayanNumeral mayanNumeral = mayanNumerals.get(resultOfPower);
				mayanNumeral.representation.forEach(System.out::println);
				startShowing = true;
				result = result.mod(divisor);
			}
			divisor = divisor.divide(BASE);
		}

		while (!divisor.equals(ZERO)) {
			showZero(mayanNumerals);
			divisor = divisor.divide(BASE);
		}
	}

	private static BigInteger compute(final String operation, final MayanNumber leftNumber, final MayanNumber rightNumber) {
		final BigInteger leftNumberValue = leftNumber.getValue();
		final BigInteger rightNumberValue = rightNumber.getValue();

		if (operation.equals("+")) {
			return leftNumberValue.add(rightNumberValue);
		} else if (operation.equals("-")) {
			return leftNumberValue.subtract(rightNumberValue);
		} else if (operation.equals("*")) {
			return leftNumberValue.multiply(rightNumberValue);
		}
		return leftNumberValue.divide(rightNumberValue);
	}

	private static MayanNumber getMayanNumber(final Scanner in, final int numeralHeight, final Map<BigInteger, MayanNumeral> mayanNumerals) {
		final int numberOfLinesNumber = in.nextInt(); //  the amount of lines S1 of the number.
		final MayanNumber mayanNumber = new MayanNumber(numberOfLinesNumber / numeralHeight);
		final List<String> lines = new ArrayList<>(numberOfLinesNumber);
		for (int i = 0; i < numberOfLinesNumber; i++) {
			lines.add(in.next()); // the first number, each line having L characters.
		}
		computeLines(mayanNumber, numeralHeight, lines, mayanNumerals);
		return mayanNumber;
	}

	private static void computeLines(final MayanNumber mayanNumber, final int numeralHeight, final List<String> lines, final Map<BigInteger, MayanNumeral> mayanNumerals) {
		final int numberOfLines = lines.size();

		for (int i = 0; i < numberOfLines; ) {
			final List<String> numeral = lines.subList(i, i + numeralHeight);
			mayanNumber.mayanNumerals.add(findNumeralByRepresentation(mayanNumerals, numeral));
			i += numeralHeight;
		}
	}

	private static MayanNumeral findNumeralByRepresentation(final Map<BigInteger, MayanNumeral> mayanNumerals, final List<String> lines) {
		for (final MayanNumeral mayanNumeral : mayanNumerals.values()) {
			if (mayanNumeral.match(lines)) {
				return mayanNumeral;
			}
		}
		return null;
	}

	private static void addLineToRepresentation(final int numeralWidth, final String numeralLine, final Map<BigInteger, MayanNumeral> mayanNumerals) {
		for (int i = 0; i < NUMBER_OF_MAYANS_NUMERALS; i++) {
			final MayanNumeral mayanNumeral = mayanNumerals.get(BigInteger.valueOf(i));
			final int beginIndex = i * numeralWidth;
			final String substring = numeralLine.substring(beginIndex, beginIndex + numeralWidth);
			mayanNumeral.representation.add(substring);
		}
	}

	private static Map<BigInteger, MayanNumeral> initMayanNumerals(final int numeralHeight) {
		final Map<BigInteger, MayanNumeral> mayansNumerals = new HashMap<>(NUMBER_OF_MAYANS_NUMERALS);

		for (long i = 0; i < NUMBER_OF_MAYANS_NUMERALS; i++) {
			final MayanNumeral mayanNumeral = new MayanNumeral(i, numeralHeight);
			mayansNumerals.put(mayanNumeral.value, mayanNumeral);
		}
		return mayansNumerals;
	}

	static class MayanNumeral {
		private final List<String> representation;
		private final BigInteger value;

		public MayanNumeral(final long value, final int numeralHeight) {
			this.value = BigInteger.valueOf(value);
			representation = new ArrayList<>(numeralHeight);
		}

		public boolean match(final List<String> lines) {
			return this.representation.equals(lines);
		}
	}

	static class MayanNumber {
		private final List<MayanNumeral> mayanNumerals;

		MayanNumber(final int numberOfNumerals) {
			mayanNumerals = new ArrayList<>(numberOfNumerals);
		}

		public BigInteger getValue() {
			BigInteger value = ZERO;
			BigInteger powerOfTwenty = BASE.pow(mayanNumerals.size() - 1);
			for (final MayanNumeral mayanNumeral : mayanNumerals) {
				value = value.add(powerOfTwenty.multiply(mayanNumeral.value));
				powerOfTwenty = powerOfTwenty.divide(BASE);
			}
			return value;
		}
	}
}
