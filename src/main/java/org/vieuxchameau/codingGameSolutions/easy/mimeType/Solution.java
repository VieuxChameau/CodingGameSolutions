package org.vieuxchameau.codingGameSolutions.easy.mimeType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {

	private static final String UNKNOWN_MIME = "UNKNOWN";

	private static final int NOT_FOUND = -1;

	public static void main(final String args[]) {
		final Scanner in = new Scanner(System.in);
		final int numberOfElements = in.nextInt(); // Number of elements which make up the association table.
		in.nextLine();
		final int numberOfFiles = in.nextInt(); // Number numberOfFiles of file names to be analyzed.
		in.nextLine();
		final Map<String, String> mimeTypesByExtension = new HashMap<>();
		for (int i = 0; i < numberOfElements; i++) {
			final String fileExtension = in.next(); // file extension
			final String mimeType = in.next(); // MIME type.
			mimeTypesByExtension.put(fileExtension.toLowerCase(), mimeType);
			in.nextLine();
		}
		for (int i = 0; i < numberOfFiles; i++) {
			final String fileName = in.nextLine(); // One file name per line.
			final int indexOfExtension = fileName.lastIndexOf(".");
			if (fileNameHasExtension(indexOfExtension)) {
				final String fileExtension = fileName.substring(indexOfExtension + 1);
				System.out.println(getMimeType(fileExtension, mimeTypesByExtension));
			} else {
				System.out.println(UNKNOWN_MIME);
			}
		}
	}

	private static boolean fileNameHasExtension(final int indexOfExtension) {
		return indexOfExtension != NOT_FOUND;
	}

	private static String getMimeType(final String extension, final Map<String, String> mimeTypesByExtension) {
		if (extension.isEmpty()) {
			return UNKNOWN_MIME;
		}
		final String mime = mimeTypesByExtension.get(extension.toLowerCase());
		if (mime == null) {
			return UNKNOWN_MIME;
		}
		return mime;
	}
}
