
Code explained in youtube. Refer the channel playlist: 
 -  https://www.youtube.com/playlist?list=PLMcMSFfe-ZLiUJMDDydzLht1T2nI_Qs4N

Here's a high-level overview of the code:

The program imports necessary Java libraries including java.io.File, java.io.FileWriter, java.util.ArrayList, and com.fasterxml.jackson.databind.ObjectMapper.

The ZiptoIntentPhrases class is defined with a main method that serves as the entry point of the program.

The program defines two file paths, folderPath and OutfolderPath, which specify the input folder where the JSON files containing training phrases are located, and the output folder where the plain text files will be generated, respectively.

The program creates a File object folder using the folderPath.

The program creates an instance of the ZiptoIntentPhrases class to be used for calling the listFilesForFolder method.

The listFilesForFolder method is called with the folder object as the argument, which recursively iterates through all the files and subdirectories in the specified folder and adds the names of files containing "usersays_en" in their names to an ArrayList called listOfFiles.

The program creates an ObjectMapper object om from the Jackson library, which is used to deserialize the JSON files into Java objects.

The program iterates through the listOfFiles and for each file, it reads its contents into an array of UserSay objects using the om.readValue() method, where UserSay is a custom class representing the structure of the JSON file.

For each UserSay object, the program extracts the training phrases from the UserSay object's data field, which is an array of Datum objects.

The program concatenates the text of the Datum objects into a single string representing the training phrase.

The program writes the training phrase string into a plain text file with a file name generated from the original file name with "~.txt" appended, and saves it in the output folder specified by OutfolderPath.

The program repeats the above steps for all the JSON files in the input folder.

Once all the JSON files are processed, the program prints "completed" to indicate the completion of the conversion process.






