package org.vieuxchameau.codingGameSolutions.easy.mimeType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        in.nextLine();
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        in.nextLine();
        Map<String, String> knownExtensions = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            knownExtensions.put(EXT.toLowerCase(), MT);
            in.nextLine();
        }
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            if (FNAME.lastIndexOf(".") != -1) {
                String fileExtension = FNAME.substring(FNAME.lastIndexOf(".") + 1);
                System.out.println(getMime(knownExtensions, fileExtension));
            } else {
                System.out.println("UNKNOWN");
            }
        }
    }

    private static String getMime(final Map<String, String> knownExtensions, final String extension) {
        if (extension.isEmpty()) {
            return "UNKNOWN";
        }
        String mime = knownExtensions.get(extension.toLowerCase());
        if (mime == null) {
            return "UNKNOWN";
        }
        return mime;
    }
}
