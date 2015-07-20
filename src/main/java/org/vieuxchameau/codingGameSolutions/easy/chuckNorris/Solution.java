package org.vieuxchameau.codingGameSolutions.easy.chuckNorris;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.toBinaryString;

class Solution {

	private static final String ONE_IN_CHUCK_NORRIS = "0 ";

	private static final String ZERO_IN_CHUCK_NORRIS = "00 ";

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final String message = in.nextLine(); // the message consisting of N ASCII characters (without carriage return)

		final StringBuilder sb = new StringBuilder();
		for (final char character : message.toCharArray()) {
			sb.append(asBinary(character));
		}

		final char[] binaryMessage = sb.toString().toCharArray();
		final StringBuilder encodedMessage = new StringBuilder("");
		for (int i = 0, length = binaryMessage.length; i < length; ) {
			final char character = binaryMessage[i];
			final int count = countSameFollowingCharacter(binaryMessage, i + 1, character) + 1;
			encodedMessage.append(character == '1' ? ONE_IN_CHUCK_NORRIS : ZERO_IN_CHUCK_NORRIS);
			encodedMessage.append(repeatZero(count));
			i += count;
		}
		System.out.println(encodedMessage.toString().trim());
	}

	private static String asBinary(final char character) {
		return String.format("%7s", toBinaryString(character)).replace(' ', '0');
	}

	private static char[] repeatZero(final int count) {
		final char[] data = new char[count + 1];
		Arrays.fill(data, '0');
		data[count] = ' ';
		return data;
	}

	private static int countSameFollowingCharacter(final char[] binaryMessage, final int index, final char character) {
		if (index >= binaryMessage.length || binaryMessage[index] != character) {
			return 0;
		}
		return countSameFollowingCharacter(binaryMessage, index + 1, character) + 1;
	}
}
