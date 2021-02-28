package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
	private WriteToFile() {
	}

	public static void writeToFile(String fileContent) {
		try {

			File myObj = new File("filename.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}

			FileWriter myWriter = new FileWriter(myObj);
			myWriter.write(fileContent);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
