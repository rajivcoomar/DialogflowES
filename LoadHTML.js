const express = require("express");
const app = express();



app.set('views', __dirname + '/views');
app.engine('html', require('ejs').renderFile);

app.set('view engine', 'ejs');


app.get("/test", (request, response) => {
	  response.render('test.html');
});

var port = process.env.PORT || 3000; 
const listener = app.listen(port, () => {
 console.log("Your app is listening on port " + listener.address().port);
});
 