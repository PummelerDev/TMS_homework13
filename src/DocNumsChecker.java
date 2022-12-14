import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DocNumsChecker {

	private static ArrayList<File> filesList = new ArrayList<>(10);
	private static HashSet<String> docNumsSet = new HashSet<>(30);
	private static HashMap<String, String> numbersMap = new HashMap<>(30);

	public static void report() throws IOException {
		toPrepareDocs();
		toWritePreparedDocs();
		toPrepareNums();
		toCreateReport();
	}

	private static void toPrepareDocs() {
		Scanner scan = new Scanner(System.in);
		String value;
		while (scan.hasNextLine()) {
			value = new String(scan.nextLine());
			if (!value.equals("0")) {
				filesList.add(new File(value.trim()));
			} else {
				break;
			}
			System.out.println(Arrays.deepToString(filesList.toArray()));
		}
		scan.close();
		System.out.println("preparation of documents is completed");
	}

	private static void toWritePreparedDocs() throws IOException {
		BufferedReader reader;
		Path path;
		for (File f : filesList) {
			try {
				path = Paths.get(f.toURI());
				reader = Files.newBufferedReader(path);
				while (reader.ready()) {
					docNumsSet.add(reader.readLine().trim());
				}
			} catch (IOException e) {
				System.out.println("file not found");
			}
		}
	}

	private static void toPrepareNums() {
		for (String number : docNumsSet) {
			numbersMap.put(number, getValid(number));
		}
		System.out.println("preparation of numbers is completed");
	}

	private static void toCreateReport() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("NumbersReport.txt"));
			for (Entry<String, String> num : numbersMap.entrySet()) {
				writer.append(num.getKey() + " " + num.getValue() + "\n");
			}
			writer.close();
			System.out.println("report creation is completed");
		} catch (IOException e) {
			System.out.println("file not found");
		}
	}

	private static String getValid(String line) {
		String patternType1 = "^docnum?([A-Za-z0-9]{9})$";
		String patternType2 = "^contract?([A-Za-z0-9]{7})$";
		if (Pattern.matches(patternType1, line.trim()) || Pattern.matches(patternType2, line.trim())) {
			return "[VALID]";
		} else {
			return "[INVALID] " + getReasonInvalid(line);
		}
	}

	private static String getReasonInvalid(String line) throws StringIndexOutOfBoundsException {
		StringBuilder checkResult = new StringBuilder("");
		if (line.trim().length() != 15) {
			checkResult.append(" (wrong length)");
		}
		if (line.trim().replaceAll(" ", "").equals("")) {
			checkResult.append(" (empty) (must begin with \"docnum\" or \"contract\")");
		}
		if (line.trim().replaceAll("[0-9a-zA-Z]", "").matches("[^0-9a-zA-Z]+")) {
			checkResult.append(" (contains forbidden symbols)");
		}
		try {
			if (!line.trim().substring(0, 6).equals("docnum") & !line.trim().substring(0, 8).equals("contract")) {
				checkResult.append(" (must begin with \"docnum\" or \"contract\")");
			}
		} catch (StringIndexOutOfBoundsException e) {
		}
		return checkResult.toString();
	}
}
