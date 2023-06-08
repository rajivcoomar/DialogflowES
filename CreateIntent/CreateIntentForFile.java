package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2beta1.AgentName;
import com.google.cloud.dialogflow.v2beta1.Intent;
import com.google.cloud.dialogflow.v2beta1.Intent.TrainingPhrase;
import com.google.cloud.dialogflow.v2beta1.Intent.TrainingPhrase.Part;
import com.google.cloud.dialogflow.v2beta1.IntentsClient;

public class CreateIntentForFile {

	public static void main(String[] args) {

		CreateIntentForFile test = new CreateIntentForFile();
		try {

			BufferedReader reader;

			reader = new BufferedReader(new FileReader("C:\\Users\\Rajiv\\Videos\\DialogflowES\\code\\Dialogflow_AccountBalance.txt"));
			String line = reader.readLine();
			List<String> trainingPhrasesParts = new ArrayList<String>();
			
			while (line != null) {
//				System.out.println(line);
				// read next line
				line = reader.readLine();
				if(line != null)
					trainingPhrasesParts.add(line);
			}

			reader.close();
			System.out.println(trainingPhrasesParts);
			test.createIntent("AccountBalance", "<project-id>",trainingPhrasesParts);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Create an intent of the given intent type
	 *
	 * @param displayName The display name of the intent.
	 * @param projectId Project/Agent Id.
	 * @param trainingPhrasesParts Training phrases.
	 * @param messageTexts Message texts for the agent's response when the intent is detected.
	 * @return The created Intent.
	 */
	public static Intent createIntent(
			String displayName,
			String projectId,
			List<String> trainingPhrasesParts
			//    List<String> messageTexts
			)
					throws ApiException, IOException {
		// Instantiates a client
		try (IntentsClient intentsClient = IntentsClient.create()) {
			// Set the project agent name using the projectID (my-project-id)
			AgentName parent = AgentName.of(projectId);

			// Build the trainingPhrases from the trainingPhrasesParts
			List<TrainingPhrase> trainingPhrases = new ArrayList<>();
			for (String trainingPhrase : trainingPhrasesParts) {
				trainingPhrases.add(
						TrainingPhrase.newBuilder()
						.addParts(Part.newBuilder().setText(trainingPhrase).build())
						.build());
			}

			// Build the message texts for the agent's response
			//    Message message = Message.newBuilder().setText(Text.newBuilder().addAllText(messageTexts).build()).build();

			// Build the intent
			Intent intent =
					Intent.newBuilder()
					.setDisplayName(displayName)
					//            .addMessages(message)
					.addAllTrainingPhrases(trainingPhrases)
					.build();

			// Performs the create intent request
			Intent response = intentsClient.createIntent(parent, intent);
			System.out.format("Intent created: %s\n", response);

			return response;
		}
	}


}
