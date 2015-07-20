package org.vieuxchameau.codingGameSolutions.medium.scrabble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.util.Arrays.asList;

class Solution {
	private static final Set<Character> ONE_POINT_LETTERS = new HashSet<>(asList('e', 'a', 'i', 'o', 'n', 'r', 't', 'l', 's', 'u'));

	private static final Set<Character> TWO_POINTS_LETTERS = new HashSet<>(asList('d', 'g'));

	private static final Set<Character> THREE_POINTS_LETTERS = new HashSet<>(asList('b', 'c', 'm', 'p'));

	private static final Set<Character> FOUR_POINTS_LETTERS = new HashSet<>(asList('f', 'h', 'v', 'w', 'y'));

	private static final Set<Character> FIVE_POINTS_LETTERS = new HashSet<>(asList('k'));

	private static final Set<Character> EIGHT_POINTS_LETTERS = new HashSet<>(asList('j', 'x'));

	private static final Set<Character> TEN_POINTS_LETTERS = new HashSet<>(asList('q', 'z'));

	private static final Map<Set<Character>, Integer> POINTS_BY_LETTER = new HashMap<Set<Character>, Integer>() {{
		put(ONE_POINT_LETTERS, 1);
		put(TWO_POINTS_LETTERS, 2);
		put(THREE_POINTS_LETTERS, 3);
		put(FOUR_POINTS_LETTERS, 4);
		put(FIVE_POINTS_LETTERS, 5);
		put(EIGHT_POINTS_LETTERS, 8);
		put(TEN_POINTS_LETTERS, 10);
	}};

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfWords = in.nextInt(); // The number N of words in the dictionary
		in.nextLine();
		final List<DictionaryWord> dictionaryWords = new ArrayList<>(numberOfWords);
		for (int i = 0; i < numberOfWords; i++) {
			final String dictionaryWord = in.nextLine(); // The words in the dictionary. One word per line.
			dictionaryWords.add(new DictionaryWord(dictionaryWord));
		}

		final String availableLetters = in.nextLine();
		final DictionaryWord mostValuableWord = findMostValuableWord(availableLetters, dictionaryWords);
		System.out.println(mostValuableWord.word);
	}

	private static DictionaryWord findMostValuableWord(final String letters, final List<DictionaryWord> dictionaryWords) {
		DictionaryWord mostValuableWord = null;
		final DictionaryWord availableLetters = new DictionaryWord(letters);
		int bestScore = Integer.MIN_VALUE;
		for (DictionaryWord dictionaryWord : dictionaryWords) {
			if (dictionaryWord.score > bestScore && dictionaryWord.match(availableLetters)) {
				mostValuableWord = dictionaryWord;
				bestScore = dictionaryWord.score;
			}
		}
		return mostValuableWord;
	}

	static class DictionaryWord {
		private final String word;

		private final int score;

		private final Map<Character, Integer> letterRef = new HashMap<>();

		DictionaryWord(final String word) {
			this.word = word;

			for (char c : word.toCharArray()) {
				final Integer nbAppearance = letterRef.get(c);
				if (nbAppearance != null) {
					letterRef.put(c, nbAppearance + 1);
				} else {
					letterRef.put(c, 1);
				}
			}
			int wordScore = 0;
			for (Map.Entry<Character, Integer> characterIntegerEntry : letterRef.entrySet()) {
				wordScore += (getPointFor(characterIntegerEntry.getKey()) * characterIntegerEntry.getValue());
			}
			score = wordScore;
		}

		public boolean match(final DictionaryWord availableLetters) {
			for (Map.Entry<Character, Integer> entry : this.letterRef.entrySet()) {
				final Integer nbAppearance = availableLetters.letterRef.get(entry.getKey());
				if (nbAppearance == null || nbAppearance < entry.getValue()) {
					return false;
				}
			}
			return true;
		}
	}

	private static int getPointFor(final Character character) {
		for (Map.Entry<Set<Character>, Integer> entry : POINTS_BY_LETTER.entrySet()) {
			if (entry.getKey().contains(character)) {
				return entry.getValue();
			}
		}
		throw new IllegalArgumentException("Unknown character " + character);
	}
}
