package org.vieuxchameau.codingGameSolutions.medium.winamax;

import java.util.*;

import static java.util.Arrays.asList;

public class Solution {

	private static final List<String> CARDS_VALUES = new ArrayList<>(asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));
	private static final int DEUCE = 0;
	private static final int PLAYER_ONE = 1;
	private static final int PLAYER_TWO = -1;

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);

		final Queue<Card> deckPlayer1 = buildDeck(in);
		final Queue<Card> deckPlayer2 = buildDeck(in);

		final Queue<Card> cardsToWinPlayer1 = new LinkedList<>();
		final Queue<Card> cardsToWinPlayer2 = new LinkedList<>();
		int numberOfRounds = 0;
		while (gameContinue(deckPlayer1, deckPlayer2)) {
			final Card cardPlayer1 = deckPlayer1.poll();
			final Card cardPlayer2 = deckPlayer2.poll();
			cardsToWinPlayer1.add(cardPlayer1);
			cardsToWinPlayer2.add(cardPlayer2);

			final int result = cardPlayer1.compareTo(cardPlayer2);
			if (isDeuce(result)) {
				try {
					cardsToWinPlayer1.addAll(popThreeCards(deckPlayer1));
					cardsToWinPlayer2.addAll(popThreeCards(deckPlayer2));
				} catch (IllegalStateException e) {
					System.out.println("PAT");
					return;
				}
			} else {
				if (result == PLAYER_ONE) {
					playerWin(deckPlayer1, cardsToWinPlayer1, cardsToWinPlayer2);
				} else if (result == PLAYER_TWO) {
					playerWin(deckPlayer2, cardsToWinPlayer1, cardsToWinPlayer2);
				}
				++numberOfRounds;
			}
		}

		displayTheWinner(deckPlayer1, numberOfRounds);
	}

	private static boolean isDeuce(final int result) {
		return result == DEUCE;
	}

	private static void displayTheWinner(final Queue<Card> deckPlayer1, final int numberOfRounds) {
		System.out.println(String.format("%s %d", (deckPlayer1.isEmpty() ? "2" : "1"), numberOfRounds));
	}

	private static Queue<Card> buildDeck(final Scanner in) {
		final int numberOfCardsPlayer = in.nextInt(); // the number of cards for the player
		final Queue<Card> deckPlayer = new LinkedList<>();
		for (int i = 0; i < numberOfCardsPlayer; i++) {
			deckPlayer.add(new Card(in.next())); // the n cards of the player
		}
		return deckPlayer;
	}

	private static void playerWin(final Queue<Card> deckPlayer, final Queue<Card> cardsToWinPlayer1, final Queue<Card> cardsToWinPlayer2) {
		deckPlayer.addAll(cardsToWinPlayer1);
		cardsToWinPlayer1.clear();
		deckPlayer.addAll(cardsToWinPlayer2);
		cardsToWinPlayer2.clear();
	}

	private static Stack<Card> popThreeCards(final Queue<Card> cards) {
		final Stack<Card> topCards = new Stack<>();
		for (int i = 0; i < 3; i++) {
			final Card card = cards.poll();
			if (card == null) {
				throw new IllegalStateException("No enough cards in the deck");
			}
			topCards.add(card);
		}
		return topCards;
	}

	private static boolean gameContinue(final Queue<Card> cardsPlayer1, final Queue<Card> cardsPlayer2) {
		return !cardsPlayer1.isEmpty() && !cardsPlayer2.isEmpty();
	}

	static class Card implements Comparable<Card> {
		private final String value;
		private final Suit suit;

		Card(final String value) {
			final int suitIndex = value.length() - 1;
			this.value = value.substring(0, suitIndex);
			this.suit = Suit.valueById(value.substring(suitIndex));
		}

		@Override
		public int compareTo(final Card card) {
			if (value.equals(card.value)) {
				return DEUCE;
			}
			final int cardValue = CARDS_VALUES.indexOf(value);
			final int rhsCardValue = CARDS_VALUES.indexOf(card.value);
			return cardValue > rhsCardValue ? PLAYER_ONE : PLAYER_TWO;
		}

		@Override
		public String toString() {
			return value + suit.id;
		}
	}

	enum Suit {
		SPADE("S"),
		HEART("H"),
		DIAMOND("D"),
		CLUB("C");

		private final String id;

		Suit(final String id) {
			this.id = id;
		}

		private static Suit valueById(final String id) {
			for (final Suit suit : values()) {
				if (suit.id.equals(id)) {
					return suit;
				}
			}
			throw new IllegalArgumentException("Unknown suit " + id);
		}
	}
}
