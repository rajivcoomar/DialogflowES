const axios = require('axios');
let data = JSON.stringify({
  "queryInput": {
    "text": {
      "text": "What is covid",
      "languageCode": "en-US"
    }
  }
});

let config = {
  method: 'post',
  maxBodyLength: Infinity,
  url: 'https://dialogflow.googleapis.com/v2beta1/projects/<projectID>/agent/sessions/123456789:detectIntent',
  headers: { 
    'Authorization': 'Bearer <auth token>', 
    'Content-Type': 'application/json'
  },
  data : data
};

axios.request(config)
.then((response) => {
  console.log(JSON.stringify(response.data));
})
.catch((error) => {
  console.log(error);
});