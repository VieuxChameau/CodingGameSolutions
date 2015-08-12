package org.vieuxchameau.codingGameSolutions.medium.dwarf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfRelationships = in.nextInt(); // the number of relationships of influence
		final Map<Integer, Person> people = new HashMap<>();
		for (int i = 0; i < numberOfRelationships; i++) {
			final int guruId = in.nextInt(); // a relationship of influence between two people (x influences y)
			final int influencedPersonId = in.nextInt();
			final Person guru = getOrAdd(people, guruId);
			final Person influencedPerson = getOrAdd(people, influencedPersonId);
			guru.addInfluencedPerson(influencedPerson);
		}

		final int longestSuccession = findLeaders(people).stream().mapToInt(person -> person.countMaximumInfluencedPeople()).max().getAsInt();
		System.out.println(longestSuccession); // The number of people involved in the longest succession of influences
	}

	static Person getOrAdd(final Map<Integer, Person> people, final int personId) {
		Person person = people.get(personId);
		if (person == null) {
			person = new Person();
			people.put(personId, person);
		}
		return person;
	}

	static Set<Person> findLeaders(final Map<Integer, Person> people) {
		return people.values().stream().filter(Person::isLeader).collect(Collectors.toSet());
	}

	static class Person {
		private Set<Person> gurus = new HashSet<>();
		private Set<Person> influencedPeople = new HashSet<>();

		public void addInfluencedPerson(final Person influencedPerson) {
			influencedPeople.add(influencedPerson);
			influencedPerson.gurus.add(this);
		}

		boolean isLeader() {
			return gurus.isEmpty();
		}

		int countMaximumInfluencedPeople() {
			if (influencedPeople.isEmpty()) {
				return 1;
			}
			return influencedPeople.stream().mapToInt(p -> p.countMaximumInfluencedPeople()).max().getAsInt() + 1;
		}
	}
}
