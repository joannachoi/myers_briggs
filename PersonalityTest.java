// Name: Joanna Choi
// VUnetID: choije
// Email: joanna.e.choi@vanderbilt.edu
// Class: CS101, Vanderbilt University
// Honor statement: I have neither given nor received any unauthorized aid on this assignment.
// Date: 11/11/14

// Description: This program calculates the Keirsey Temperament Sorter personality test
//				for people named in a given file. The program determines four independent
//				dimensions of personality: extrovert versus introvert, sensation versus
//				intuition, thinking versus feeling, and judging versus perceiving.

import java.io.*;
import java.util.*;

public class PersonalityTest {

	public static final int numberOfDimensions = 4;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner keyboard = new Scanner(System.in);
		File inFile = null;

		introduction();

		// input and output file names
		String inputFile = inputFile(keyboard, inFile);
		System.out.print("Output file name: ");
		String outputName = keyboard.nextLine();
		File output = new File(outputName);
		PrintStream outputFile = new PrintStream(output);

		// process input file
		Scanner input = new Scanner(new File(inputFile));
		while (input.hasNextLine()) {
			String name = input.nextLine();
			outputFile.println(name);
			String line = input.nextLine().toUpperCase();
			int[] dimensionA = new int[numberOfDimensions];
			int[] dimensionB = new int[numberOfDimensions];
			personalityTest(line, dimensionA, dimensionB);
			answers(dimensionA, dimensionB, outputFile);
			int[] percentB = new int[numberOfDimensions];
			convertBtoPercent(dimensionA, dimensionB, percentB);
			outputFile.println("Percent B: " + Arrays.toString(percentB));
			type(dimensionA, dimensionB, outputFile);
			outputFile.println();
		}

	}

	/**
	 * This method includes an introduction to what the program will do.
	 */
	public static void introduction() {
		System.out
				.println("This program calculates the Keirsey Temperament Sorter personality test");
		System.out
				.println("for people named in a given file. The program determines four independent");
		System.out
				.println("dimensions of personality: extrovert versus introvert, sensation versus");
		System.out
				.println("intuition, thinking versus feeling, and judging versus perceiving.");
		System.out.println();
	}

	/**
	 * This method ensures that the file that the user types in exists.
	 * 
	 * @param keyboard
	 *            this allows the user to type in the name of the file
	 * @param inFile
	 *            this is the file that the user inputs
	 * @return an existing file
	 */
	public static String inputFile(Scanner keyboard, File inFile) {
		String inputFile;
		System.out.print("Input file name: ");
		inputFile = keyboard.nextLine();
		inFile = new File(inputFile);

		while (!inFile.exists()) {
			System.out.print("Input file name: ");
			inputFile = keyboard.nextLine();
			inFile = new File(inputFile);
		}

		return inputFile;
	}

	/**
	 * This method converts the A's and B's of a person's personality test
	 * results into two arrays.
	 * 
	 * @param line
	 *            the line of A's, B's, and -'s
	 * @param dimensionA
	 *            the A values that are stored for the 4 dimensions
	 * @param dimensionB
	 *            the B values that are stored for the 4 dimensions
	 * @throws FileNotFoundException
	 *             if the file is not found
	 */
	public static void personalityTest(String line, int[] dimensionA,
			int[] dimensionB) throws FileNotFoundException {

		for (int i = 0; i < 10; i++) {
			String groupOfSeven = line.substring(i * 7, (i * 7) + 7);

			// extrovert or introvert
			if (groupOfSeven.charAt(0) == 'A') {
				dimensionA[0]++;
			} else if (groupOfSeven.charAt(0) == 'B') {
				dimensionB[0]++;
			}

			// sensing or intuition & thinking or feeling & judging or
			// perceiving
			for (int j = 1; j < 7; j++) {
				if (groupOfSeven.charAt(j) == 'A' && (j == 1 || j == 2)) {
					dimensionA[1]++;
				}
				if (groupOfSeven.charAt(j) == 'B' && (j == 1 || j == 2)) {
					dimensionB[1]++;
				}
				if (groupOfSeven.charAt(j) == 'A' && (j == 3 || j == 4)) {
					dimensionA[2]++;
				}
				if (groupOfSeven.charAt(j) == 'B' && (j == 3 || j == 4)) {
					dimensionB[2]++;
				}
				if (groupOfSeven.charAt(j) == 'A' && (j == 5 || j == 6)) {
					dimensionA[3]++;
				}
				if (groupOfSeven.charAt(j) == 'B' && (j == 5 || j == 6)) {
					dimensionB[3]++;
				}
			}
		}

	}

	/**
	 * This method prints out the answers.
	 * 
	 * @param dimensionA
	 *            the A values that are stored for the 4 dimensions
	 * @param dimensionB
	 *            the B values that are stored for the 4 dimensions
	 * @param outputFile
	 *            the file that the user wants the results to be printed to
	 */
	public static void answers(int[] dimensionA, int[] dimensionB,
			PrintStream outputFile) {
		outputFile.print("Answers: ");
		outputFile.print("[");
		for (int i = 0; i < numberOfDimensions - 1; i++) {
			outputFile.print(dimensionA[i]);
			outputFile.print("A-");
			outputFile.print(dimensionB[i]);
			outputFile.print("B, ");
		}
		outputFile.print(dimensionA[numberOfDimensions - 1]);
		outputFile.print("A-");
		outputFile.print(dimensionB[numberOfDimensions - 1]);
		outputFile.print("B]");
		outputFile.println();
	}

	/**
	 * This method converts the Dimension B values into percents
	 * 
	 * @param dimensionA
	 *            the A values that are stored for the 4 dimensions
	 * @param dimensionB
	 *            the B values that are stored for the 4 dimensions
	 * @param percentB
	 *            the values of dimensionB that are converted into percents
	 */
	public static void convertBtoPercent(int[] dimensionA, int[] dimensionB,
			int[] percentB) {
		for (int i = 0; i < numberOfDimensions; i++) {
			int x = dimensionA[i] + dimensionB[i];
			percentB[i] = Math.round(dimensionB[i] * 100 / x);
		}
	}

	/**
	 * This method determines the 4 different types that each person is.
	 * 
	 * @param dimensionA
	 *            the A values that are stored for the 4 dimensions
	 * @param dimensionB
	 *            the B values that are stored for the 4 dimensions
	 * @param outputFile
	 *            the file that the user wants the results to be printed to
	 */
	public static void type(int[] dimensionA, int[] dimensionB,
			PrintStream outputFile) {
		char[] A = { 'E', 'S', 'T', 'J' };
		char[] B = { 'I', 'N', 'F', 'P' };
		outputFile.print("Type: ");
		for (int i = 0; i < numberOfDimensions; i++) {
			if (dimensionA[i] > dimensionB[i]) {
				outputFile.print(A[i]);
			} else if (dimensionA[i] < dimensionB[i]) {
				outputFile.print(B[i]);
			} else if (dimensionA[i] == dimensionB[i]) {
				outputFile.print("X");
			}
		}
		outputFile.println();
	}

}
