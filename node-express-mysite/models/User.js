const mongooes = require("mongoose");
const autoIncrement = require("mongoose-auto-increment");

mongooes.connect("mongodb://localhost:27017/webdb", {
    useNewUrlParser: true,
    useCreateIndex: true
});

const user = new mongooes.Schema({
    name: String,
    email: String,
    password: String,
    joinDate: {
        type: Date,
        default: Date.now
    }
}, {
    versionKey: false
});

autoIncrement.initialize(mongooes.connection);
user.plugin(autoIncrement.plugin, "User");

module.exports = mongooes.model("User", user); // 모델 이름, 변수 이름 - mongoose의 모델 객체 생성 (crud)