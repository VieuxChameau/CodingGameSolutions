package org.vieuxchameau.codingGameSolutions.medium.theGift;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

class Solution {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfParticipants = in.nextInt(); // the number N of participants
		final int giftPrice = in.nextInt(); // the price C of the gift

		final List<Participant> participants = new ArrayList<>(numberOfParticipants);
		int budgetSum = 0;
		for (int i = 0; i < numberOfParticipants; i++) {
			final int participantBudget = in.nextInt(); //  the list of budgets B of participants.
			budgetSum += participantBudget;
			participants.add(new Participant(participantBudget));
		}

		if (!isBudgetSumSufficient(budgetSum, giftPrice)) {
			System.out.println("IMPOSSIBLE");
			return;
		}

		participants.sort((left, right) -> Integer.compare(left.maximalBudget, right.maximalBudget));

		final List<Integer> contributions = calculateContributions(giftPrice, participants);

		contributions.stream().sorted().forEach(System.out::println);
	}

	private static List<Integer> calculateContributions(final int giftPrice, final List<Participant> participants) {
		final int numberOfParticipants = participants.size();

		final int contributionPerParticipants = giftPrice / numberOfParticipants;

		final int lowestRemainingBudget = participants.get(0).remainingBudget;
		if (contributionPerParticipants > lowestRemainingBudget) {
			participants.forEach(p -> p.contribute(lowestRemainingBudget));

			final List<Integer> contributions = new ArrayList<>((int) numberOfParticipants);
			contributions.addAll(participants.stream().filter(not(Participant::hasRemainingBudget)).map(Participant::getContribution).collect(toList()));
			contributions.addAll(calculateContributions(getRemainingGiftPrice(giftPrice, numberOfParticipants, lowestRemainingBudget), participantsWithBudget(participants)));
			return contributions;
		}

		participants.forEach(participant -> participant.contribute(contributionPerParticipants));

		final int remainderPrice = giftPrice % numberOfParticipants;
		if (remainderPrice > 0) {
			participantsWithBudget(participants).stream().limit(remainderPrice).forEach(p -> p.contribute(1));
		}
		return participants.stream().map(Participant::getContribution).collect(toList());
	}

	private static List<Participant> participantsWithBudget(final List<Participant> participants) {
		return participants.stream().filter(Participant::hasRemainingBudget).collect(toList());
	}

	private static int getRemainingGiftPrice(final int giftPrice, final int numberOfParticipants, final int contributionPerParticipants) {
		return giftPrice - (numberOfParticipants * contributionPerParticipants);
	}

	private static boolean isBudgetSumSufficient(final int budgetSum, final int giftPrice) {
		return budgetSum >= giftPrice;
	}

	public static <T> Predicate<T> not(final Predicate<T> predicate) {
		return predicate.negate();
	}

	static class Participant {
		private final int maximalBudget;
		private int remainingBudget;
		private int contribution;

		Participant(final int maximalBudget) {
			this.maximalBudget = maximalBudget;
			this.remainingBudget = maximalBudget;
		}

		void contribute(final int contribution) {
			if (contribution > remainingBudget) {
				throw new IllegalArgumentException("Not enough budget");
			}
			remainingBudget -= contribution;
			this.contribution += contribution;
		}

		boolean hasRemainingBudget() {
			return remainingBudget > 0;
		}

		public int getContribution() {
			return contribution;
		}
	}
}