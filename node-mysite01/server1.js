let connect = require("connect");

let logger = function(req, res, next){
    console.log('%s %s', req.method, req.url);
    next();
}

let hello = function(req, res, next){
    res.setHeader("Content-Type", "text/html");
    res.end("<h1>Hello World</h1>");
    next();
}

let app = connect();

app.use(hello, null);
app.use(logger, null);

// method chain
app.listen(3000); // port