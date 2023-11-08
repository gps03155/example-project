var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var favicon = require('serve-favicon');
var session = require('express-session');

var mainRouter = require('./routes/main');
var usersRouter = require('./routes/user');
var apiRouter = require('./routes/api');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false })); // form으로 넘어오는거 객체로 바꿔줌
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(favicon(path.join(__dirname, "public", "assets", "images", "favicon.ico")));

// Session Env
app.use(session({
  secret: "mysite-session",
  resave: true,
  saveUninitialized: true
}));

app.all("*", function (req, res, next) {
  res.locals.req = req;
  res.locals.res = res;

  next(); // 없으면 뒤로 넘어가지 않음
});

app.use('/', mainRouter);
app.use('/user', usersRouter);
app.use('/api', apiRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
