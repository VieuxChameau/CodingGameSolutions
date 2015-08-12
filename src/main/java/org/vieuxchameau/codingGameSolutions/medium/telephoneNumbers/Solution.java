package org.vieuxchameau.codingGameSolutions.medium.telephoneNumbers;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Solution {

	private static final int BEGIN_INDEX = 0;

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfPhoneNumbers = in.nextInt(); // The number N of telephone numbers.
		final Set<String> phonesPrefixes = new HashSet<>();
		for (int i = 0; i < numberOfPhoneNumbers; i++) {
			final String phoneNumber = in.next(); //  Each line contains a phone number, with a maximum length L. Telephone numbers consist of only the digits 0 to 9 included, without any spaces.
			for (int j = 1, length = phoneNumber.length(); j <= length; j++) {
				phonesPrefixes.add(phoneNumber.substring(BEGIN_INDEX, j));
			}
		}
		System.out.println(phonesPrefixes.size());
	}
}
