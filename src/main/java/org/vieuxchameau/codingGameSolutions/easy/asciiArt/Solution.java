package org.vieuxchameau.codingGameSolutions.easy.asciiArt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {

	private static final char UNKNOWN_KEY = '?';

	private static final int NB_LETTERS_IN_ALPHABET = 26;

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int letterWidth = in.nextInt(); // the width L of a letter represented in ASCII art
		in.nextLine();
		final int letterHeight = in.nextInt(); // the height H of a letter represented in ASCII art
		in.nextLine();
		final String text = in.nextLine(); // The line of text T, composed of N ASCII characters.
		final Map<Character, String[]> alphabetMapping = initAlphabetMapping(letterHeight);
		for (int rowIndex = 0; rowIndex < letterHeight; rowIndex++) {
			final String row = in.nextLine();
			addRowToMapping(alphabetMapping, row, letterWidth, rowIndex);
		}

		showTextInAsciiArt(text, letterHeight, alphabetMapping);
	}

	private static void showTextInAsciiArt(final String text, final int letterHeight, final Map<Character, String[]> alphabetMapping) {
		final char[] textInLowerCase = text.toLowerCase().toCharArray();
		for (int rowIndex = 0; rowIndex < letterHeight; rowIndex++) {
			final StringBuilder buf = new StringBuilder("");
			for (char character : textInLowerCase) {
				String[] characterMapping = alphabetMapping.get(character);
				if (characterMapping == null) {
					characterMapping = alphabetMapping.get(UNKNOWN_KEY);
				}
				buf.append(characterMapping[rowIndex]);
			}
			System.out.println(buf.toString());
		}
	}

	private static Map<Character, String[]> initAlphabetMapping(final int letterHeight) {
		final Map<Character, String[]> map = new HashMap<>(27);
		for (int i = 0; i < NB_LETTERS_IN_ALPHABET; ++i) {
			map.put(getLetter(i), new String[letterHeight]);
		}
		map.put(UNKNOWN_KEY, new String[letterHeight]);
		return map;
	}

	private static void addRowToMapping(final Map<Character, String[]> alphabetMapping, String row, final int letterWidth, final int rowIndex) {
		for (int i = 0; i < NB_LETTERS_IN_ALPHABET; i++) {
			final String[] rows = alphabetMapping.get(getLetter(i));
			rows[rowIndex] = headRow(row, letterWidth);
			row = tailRow(row, letterWidth);
		}
		final String[] rows = alphabetMapping.get(UNKNOWN_KEY);
		rows[rowIndex] = headRow(row, letterWidth);
	}

	private static String tailRow(final String row, final int letterWidth) {
		return row.substring(letterWidth);
	}

	private static String headRow(final String row, final int letterWidth) {
		return row.substring(0, letterWidth);
	}

	private static char getLetter(final int i) {
		return (char) ('a' + i);
	}
}
