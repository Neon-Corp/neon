const express = require('express');
const fonoapi = require('fonoapi-nodejs');
const router = express.Router();
const app = express();

app.set('view engine', 'ejs');

// Fonoapi setup
fonoapi.token = '95822991dafd555ddde2ea790640c7661049e367f9d785da';

router.use('/search', function (req, res) {
  fonoapi.getDevices(function (queryString, data) {
    if (data == null || (data.hasOwnProperty('status') && data.status == 'error')) {
      res.render('search', {data: null});
    } else {
      res.render('search', {data: data, query: req.query.model});
    }
  }, req.query.model);
});

app.use(express.static(__dirname + '/public'));
router.use('/', function (req, res) {
  res.sendFile(__dirname + "/public/");
});

app.use('/', router);

app.listen(3000, function () {
  console.log('Listening on port 3000...');
});
