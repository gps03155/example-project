let connect = require("connect");
let connectRoute = require("connect-route");
let serveStatic = require("serve-static"); // html, css, image file
let fileSystem = require("fs"); // file read

let app = connect();

// __dirname = full path
app.use(serveStatic(__dirname + "/htdocs"), null); // css 적용
console.log(__dirname);

app.use(connectRoute(function(router){
    router.get("/", function(req, res){
        fileSystem.readFile(__dirname + "/htdocs/main/index.html", "UTF-8", function (error, data) {
            // console.log(data); // html 문서 내용

            res.writeHead(200, {"Content-Type": "text/html"});
            res.end(data);
        });
    });
}), null);

// method chain
app.listen(3000); // port