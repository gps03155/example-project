const mongooes = require("mongoose");
const autoIncrement = require("mongoose-auto-increment");

mongooes.connect("mongodb://localhost:27017/webdb", {
    useNewUrlParser: true,
    useCreateIndex: true
});

const guestbookMessage = new mongooes.Schema({
    name: String,
    password: String,
    message: String,
    regDate: {
        type: Date,
        default: Date.now
    }
}, {
    versionKey: false
});

autoIncrement.initialize(mongooes.connection);
guestbookMessage.plugin(autoIncrement.plugin, "GuestbookMessage");

module.exports = mongooes.model("GuestbookMessage", guestbookMessage); // 모델 이름, 변수 이름 - mongoose의 모델 객체 생성 (crud)