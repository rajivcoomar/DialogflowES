const dialogflow = require('@google-cloud/dialogflow').v2beta1;

callKnowledge();


async function callKnowledge(){
	
	
	 const sessionClient = new dialogflow.SessionsClient();

	 const projectId = <projectId>;
	 const sessionId = Math.floor(Math.random() * 37 ) ; ;
	 const languageCode = 'en-US';
	 const knowledgeBaseId = <knowledgeBaseId>;
	// const query = `phrase(s) to pass to detect, e.g. I'd like to reserve a room for six people`;

	// Define session path
	const sessionPath = sessionClient.projectAgentSessionPath(
	  projectId,
	  sessionId
	);
	const knowledgeBasePath =
	  'projects/' + projectId + '/knowledgeBases/' + knowledgeBaseId ;

	// The audio query request
	const request = {
	  session: sessionPath,
	  queryInput: {
		text: {
		  text: 'What can I do to protect myself and prevent',
		  languageCode: languageCode,
		},
	  },
	 queryParams: {
		knowledgeBaseNames: [knowledgeBasePath],
	  },
	   headers: { 
				
				 'content-type': 'application/json; charset=utf-8' 
				
			  }
	};

	const responses = await sessionClient.detectIntent(request);
	
	const result = responses[0].queryResult.fulfillmentText;
	console.log(result);
	  
}