
//* Demonstrates:
// *  - Writing to a text file (overwrite)
// *  - Reading from a text file
// *  - Appending to a text file
// *  - Modifying text in a file (word replacement)
// *  - Deleting a file
// *
// * Instructions to run:
// * 1) Save this file as FileOperationsDemo.java
// * 2) Compile: javac FileOperationsDemo.java
// * 3) Run: java FileOperationsDemo
// *

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileOperationsDemo {
    private static final Path FILE_PATH = Paths.get("sample.txt");

    public static void main(String[] args) {
        try {
            // 1. Write to file
            writeFile("Hello, this is the original content.\nThis is line 2.");

            // 2. Read File
            System.out.println("Initial file contents:");
            readFile();

            // 3. Append text
            appendToFile("This line was appended later.");

            System.out.println("\nAfter appending:");
            readFile();

            // 4. Modify File (replace word)
            modifyFile("original", "updated");

            System.out.println("\nAfter modification:");
            readFile();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Write content to file (overwrites if file exists)
    private static void writeFile(String content) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
            writer.write(content);
        }
    }

    // Read and print file contents
    private static void readFile() throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    // Append new content to file
    private static void appendToFile(String content) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH, StandardOpenOption.APPEND)) {
            writer.newLine();
            writer.write(content);
        }
    }

    // Modify file contents: replace all occurrences of oldWord with newWord
    private static void modifyFile(String oldWord, String newWord) throws IOException {
        List<String> lines = Files.readAllLines(FILE_PATH);
        List<String> modified = new ArrayList<>();

        for (String line : lines) {
            modified.add(line.replace(oldWord, newWord));
        }

        Files.write(FILE_PATH, modified);
    }
}


