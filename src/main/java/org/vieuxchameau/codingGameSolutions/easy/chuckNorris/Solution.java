package org.vieuxchameau.codingGameSolutions.easy.chuckNorris;

import java.util.Scanner;

import static java.lang.Integer.toBinaryString;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();

        StringBuilder messageInBinary = new StringBuilder();
        for (final char charr : MESSAGE.toCharArray()) {
            messageInBinary.append(String.format("%7s", toBinaryString(charr)).replace(' ', '0'));
        }

        final char[] binaryMessage = messageInBinary.toString().toCharArray();
        final int length = binaryMessage.length;
        StringBuffer encodedMessage = new StringBuffer("");
        for (int i = 0; i < length; ) {
            final char currentChar = binaryMessage[i];
            int count = howMuchFollow(binaryMessage, i + 1, currentChar) + 1;
            encodedMessage.append(currentChar == '1' ? "0 " : "00 ");
            repeat(encodedMessage, count);
            i += count;
        }
        System.out.println(encodedMessage.toString().trim());
    }

    private static void repeat(final StringBuffer buffer, int count) {
        while (count-- > 0) {
            buffer.append('0');
        }
        buffer.append(' ');
    }

    private static int howMuchFollow(final char[] binaryMessage, final int index, final char currentChar) {
        if (index >= binaryMessage.length || binaryMessage[index] != currentChar) {
            return 0;
        }
        return howMuchFollow(binaryMessage, index + 1, currentChar) + 1;
    }
}
