const express = require('express');
const { WebhookClient} = require('dialogflow-fulfillment');
var axios = require('axios');

const app = express();

app.post('/webhook', express.json(), (req, res) => {
  const agent = new WebhookClient({ request: req, response: res });


function getWeather(city) {
	
	return new Promise((resolve, reject) => {
	
			var config = {
				  method: 'get',
				maxBodyLength: Infinity,
				  url: 'http://api.weatherapi.com/v1/current.json?key=<key>&q='+city,
				  headers: { }
				};

				axios(config)
				.then(function (response) {
				//  console.log(JSON.stringify(response.data));
				  
				  resolve(JSON.stringify(response.data));
				})
				.catch(function (error) {
				  console.log(error);
				});
				
			});
	
	
}
	
	async function test(agent) {
		
		
		console.log(JSON.stringify(req.body.queryResult.outputContexts[0].parameters.City));
		console.log(JSON.stringify(req.body.queryResult.outputContexts[1].parameters.DegreeType));
		
		var city = JSON.stringify(req.body.queryResult.outputContexts[0].parameters.City);
		var degreeType = JSON.stringify(req.body.queryResult.outputContexts[1].parameters.DegreeType);
		var jsonObj = await getWeather(city);
		//console.log(jsonObj);
		
		
		var jsonData = JSON.parse(jsonObj);
		var tmpC = jsonData.current.temp_c;
		var tmpF = jsonData.current.temp_f;
		
		if(degreeType == "\"fahrenheit\"")
		{
			agent.add("Today's weather in "+city+" is "+tmpF+" F");
		}
		else
		{
			agent.add("Today's weather in "+city+" is "+tmpC+" C");
		}
		
		
		
	}



  let intentMap = new Map();
  intentMap.set('Weather - AskDegreeType', test);
  agent.handleRequest(intentMap);
});

app.listen(3000, () => console.log('Server listening on port 3000'));
