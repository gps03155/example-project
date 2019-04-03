let connect = require("connect");
let connectRoute = require("connect-route");
let url = require("url");

let requestHandlers = function(router){
    router.get("/", function(req, res){
        res.setHeader("Content-Type", "text/html; charset=UTF-8");
        res.end("<h1>Main</h1>");
    });

    router.get("/hello", function(req, res){
        let query = url.parse(req.url, true).query;
        console.log(query);

        res.setHeader("Content-Type", "text/html; charset=UTF-8");
        res.end("<h1>Hello : </h1>" + query['id'] + "<br>" + query['name']);
    });

    // path variable
    router.get("/board/view/:no", function(req, res){
      console.log(req.params['no']);

        res.setHeader("Content-Type", "text/html; charset=UTF-8");
        res.end("<h1>Hello : </h1>" + req.params['no']);
    });

    // json
    router.get("/api/user/checkemail", function(req, res){
        let result = {
            result: true,
            data: "exists"
        };

        res.setHeader("Content-Type", "application/json");
        res.end(JSON.stringify(result));
    });
}

let app = connect();

app.use(connectRoute(requestHandlers), null);

// method chain
app.listen(3000); // port