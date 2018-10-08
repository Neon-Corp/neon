var express = require('express');
var fonoapi = require('fonoapi-nodejs');
var app = express();

// Fonoapi setup
fonoapi.token = '95822991dafd555ddde2ea790640c7661049e367f9d785da';

var indexRouter = require('./routes/index');
var resultsRouter = require('./routes/results');

app.use('/', indexRouter);
app.use('/results', resultsRouter);

app.set('view engine', 'ejs');
app.use(express.static(__dirname + '/public'));

app.listen(3000, function () {
  console.log('Listening on port 3000...');
});
