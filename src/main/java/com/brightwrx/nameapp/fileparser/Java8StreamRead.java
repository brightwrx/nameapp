package com.brightwrx.nameapp.fileparser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Java8StreamRead {

	// public static final String filepath =
	// "/Users/sudheer/brightwrx/workspace/nameapp/data/names-unique-small.txt";
	public static final String filepath = "/Users/sudheer/brightwrx/workspace/nameapp/data/names-unique.txt";

	public static void main(String[] args) {
		long counter = 0;
		long startTime = System.nanoTime();
		Path file = Paths.get(filepath);
		HashMap<String, Integer> namesMap = new HashMap<String, Integer>();
		System.out.println("Parsing file: " + filepath);
		try {
			// Java 8: Stream class
			Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8);
			for (String line : (Iterable<String>) lines::iterator) {
				counter++;
				String fname = line.split(" ")[0];
				Integer oldCount = namesMap.get(fname);
				if (oldCount == null) {
					oldCount = 0;
				}
				namesMap.put(fname, oldCount + 1);
			}
//			namesMap.forEach((key, value) -> {System.out.println("Name : " + key + " Count : " + value);});
			lines.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		long endTime = System.nanoTime();
		long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
		System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms");
		System.out.println("Parsed names: " + counter + " Unique first names: " + namesMap.size());

	}
}