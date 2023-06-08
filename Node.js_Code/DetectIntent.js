const dialogflow = require('@google-cloud/dialogflow').v2beta1;

callDetectIntent();



async function callDetectIntent(){
	
	
	 const sessionClient = new dialogflow.SessionsClient();

	 const projectId = '<ProjectID>';
	 const sessionId = Math.floor(Math.random() * 37 ) ; ;
	 const languageCode = 'en-US';
	 
	// const query = `phrase(s) to pass to detect, e.g. I'd like to reserve a room for six people`;

	// Define session path
	const sessionPath = sessionClient.projectAgentSessionPath(
	  projectId,
	  sessionId
	);
	

	// The audio query request
	const request = {
	  session: sessionPath,
	  queryInput: {
		text: {
		  text: 'Hi',
		  languageCode: languageCode,
		},
	  },
	
	   headers: { 
				
				 'content-type': 'application/json; charset=utf-8' 
				
			  }
	};

	const responses = await sessionClient.detectIntent(request);
	
	const result = responses[0].queryResult.fulfillmentText;
	console.log(result);
	  
}