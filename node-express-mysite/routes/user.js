var express = require('express');
var session = require("express-session");

var router = express.Router();

var User = require("../models/User");

/* GET users listing. */
router.route('/join').get(function(req, res, next) {
  res.render("user/join");
});

router.route('/join').post(function (req, res, next){
  console.log(req.body);

  User.create(req.body, function (err) {
    if(err){
      next(err);
      return;
    }

    res.redirect("/user/joinsuccess");
  });
});

router.route("/joinsuccess").get(function (req, res, next) {
  res.render("user/joinsuccess", null);
});

router.route("/login").get(function (req, res, next) {
  console.log(req.query.result);

  res.render("user/login", {result: req.query.result}); // forwading 할때처럼 값을 넘김
});

router.route("/login").post(function (req, res, next) {
  console.log(req.body);

  User.findOne(req.body, ['_id', 'name'], {}, function(err, user){
    if(err){
      next(err);
      return;
    }

    if(user == null){ // 로그인 실패시
      res.redirect("/user/login?result=fail");

      return;
    }

    // session 처리
    console.log(user);

    req.session.authUser = user; // session 객체 저장

    res.redirect("/");
  });
});

router.route("/logout").get(function (req, res, next) {
  console.log("로그아웃");

  req.session.destroy(function(err){
    if(err){
      next(err);
      return;
    }

    res.redirect("/");
  });
});

module.exports = router;
