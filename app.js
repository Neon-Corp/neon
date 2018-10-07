const express = require('express');
const fonoapi = require('fonoapi-nodejs');
const app = express();

// Fonoapi setup
fonoapi.token = '';

app.get('/', function (req, res) {
  res.send('Hello World!');
});

app.listen(3000, function () {
  console.log('Listening on port 3000...');
});

