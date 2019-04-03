let http = require("http");
let httpd = http.createServer(function (request, response) {
    // 브라우저 접속 후 실행
    console.log("브라우저로부터 요청을 받았습니다.");
    console.log("request received url : " + request.url);

    // head
    response.writeHead(200, {"Content-Type": "text/html"});

    // body
    response.end("<h1>Hello World</h1>");
});

httpd.listen(8088, function(){
    console.log("httpd starts.... port : 8088"); // 서버 접속 후 실행
});