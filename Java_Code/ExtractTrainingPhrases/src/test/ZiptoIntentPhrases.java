package test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ZiptoIntentPhrases {

	public static void main(String[] args) {
		
		String folderPath = "C:\\Users\\Rajiv\\Videos\\DialogflowES\\ExtractTrainingPhrases\\Riu-Kris\\intents"; // Path to the folder containing intent files
		String OutfolderPath = "C:\\Users\\Rajiv\\Videos\\DialogflowES\\ExtractTrainingPhrases\\Output"; // Path to the output folder where the generated files will be saved

		final File folder = new File(folderPath); // Create a File object representing the folder
		ZiptoIntentPhrases test = new ZiptoIntentPhrases(); // Create an instance of the ZiptoIntentPhrases class
		ArrayList<String> listOfFiles = test.listFilesForFolder(folder); // Get a list of file names in the folder that contain "usersays_en"
		
		
		ObjectMapper om = new ObjectMapper(); // Create an ObjectMapper object for reading JSON
		
		// Loop through the list of file names
		for(String fileName : listOfFiles) {
			
			String onlyFileName = fileName.substring(0,fileName.indexOf("_")); // Extract the intent name from the file name
			String testString = onlyFileName.toLowerCase(); // Convert the intent name to lowercase
			System.out.println(onlyFileName);
			
			
			try {
				UserSay[] root = om.readValue(new File(folderPath+"\\"+fileName), UserSay[].class); // Read the JSON file into an array of UserSay objects
				FileWriter myWriter = new FileWriter(OutfolderPath+"\\"+onlyFileName+"~.txt"); // Create a FileWriter object for writing the output to a text file
				int count = 1;
				for (UserSay itr : root) { // Loop through the UserSay objects
					String utterance = "";
					for(Datum values : itr.getData()) // Loop through the Datum objects within each UserSay object
					{
						utterance = utterance  + values.getText(); // Concatenate the text values to form an utterance
					}
					
					if(count == root.length)
					{
						myWriter.write(utterance); // Write the utterance to the output file
					}
					else
					{
						myWriter.write(utterance + "\n"); // Write the utterance followed by a newline character to the output file
					}
					
					count++;
							
				}
				
			    myWriter.close(); // Close the FileWriter
			    System.out.println("completed");
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> listOfFiles = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) { // Loop through the files and folders in the given folder
	        if (fileEntry.isDirectory()) { // If the current entry is a directory, recursively call this method on it
	            listFilesForFolder(fileEntry);
	        } else {
	        	if(fileEntry.getName().contains("usersays_en")) // If the file name contains "usersays_en", add it to the list of files
	        		listOfFiles.add(fileEntry.getName());
	        }
	    }
	    return listOfFiles; // Return the list of file names
	}

}
