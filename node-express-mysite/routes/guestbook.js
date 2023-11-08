const express = require("express");
const GuestbookMessage = require("../models/Guestbook")
const moment = require("moment");

const router = express.Router();

router.route("/delete/:id").get(function (req, res, next) {
    res.render("deleteform", {
        id: req.params.id
    }); // data 따로 보낼거 없으면 null
});

router.route("/delete").post(function (req, res, next) {
    GuestbookMessage.deleteOne(req.body, function(err){
        if(err){
            next(err);
            return;
        }

        res.redirect("/");
    }); // 비동기
});

router.route("/add").post(function (req, res, next) {
    console.log(req.body); // 넘어오는 데이터를 객체로 모음
    GuestbookMessage.create(req.body);

    res.redirect("/");
});

router.route(/.*/).get(function (req, res, next) {
    GuestbookMessage.find({}, [
        "_id", "name", "message", "regDate"
    ], {
        sort:{
            regDate: -1
        }
    },
        function (err, guestbookMessages) {
        if(err){
            next(err); // 에러 화면 부르기
            return; // return 안하면 rener 실행
        }

        res.render("list", {
            guestbooksMessages: guestbookMessages,
            moment: moment
        }); // 비동기 방식이라 밖에 빼두면 안됨 - 쿼리에 문제는 없는데 결과가 안나올 수 있음
    });
});

module.exports = router;