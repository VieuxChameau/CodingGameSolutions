package org.vieuxchameau.codingGameSolutions.easy.asciiArt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {

    public static final char UNKNOW_KEY = '?';

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt(); // the width L of a letter represented in ASCII art
        in.nextLine();
        int H = in.nextInt(); // the height H of a letter represented in ASCII art
        in.nextLine();
        String T = in.nextLine(); // The line of text T, composed of N ASCII characters.
        Map<Character, String[]> mapping = initMapping(H);
        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            addRow(mapping, ROW, L, i);
        }

        char[] chars = T.toLowerCase().toCharArray();
        for (int i = 0; i < H; i++) {
            StringBuilder buf = new StringBuilder("");
            for (char c : chars) {
                String[] lines = mapping.get(c);
                if (lines == null) {
                    lines = mapping.get(UNKNOW_KEY);
                }
                buf.append(lines[i]);
            }
            System.out.println(buf.toString());
        }

    }

    private static void addRow(final Map<Character, String[]> map, String line, final int L, final int ROW) {
        for (int i = 0; i < 26; i++) {
            String[] rows = map.get((char) ('a' + i));
            rows[ROW] = line.substring(0, L);
            line = line.substring(L);
        }
        String[] rows = map.get(UNKNOW_KEY);
        rows[ROW] = line.substring(0, L);
    }

    private static Map<Character, String[]> initMapping(final int H) {
        Map<Character, String[]> map = new HashMap<>(27);
        for (int i = 0; i < 26; ++i) {
            map.put((char) ('a' + i), new String[H]);
        }
        map.put(UNKNOW_KEY, new String[H]);
        return map;
    }
}
