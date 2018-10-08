var express = require('express');
var fonoapi = require('fonoapi-nodejs');
var router = express.Router();

router.get('/', function (req, res) {
  console.log('AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA');
  fonoapi.getDevices(function (queryString, data) {
    if (data == null || (data.hasOwnProperty('status') && data.status == 'error')) {
      res.render('results', {data: null});
    } else {
      res.render('results', {data: data, query: req.query.model});
    }
  }, req.query.model);
});

module.exports = router;