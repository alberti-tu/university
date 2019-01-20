let express = require('express');
let path = require('path');
let logger = require('morgan');
let cookieParser = require('cookie-parser');
let bodyParser = require('body-parser');

let app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
// app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

app.use('/', require('./routes/index'));

app.get('/', function (req, res) {
    res.render('index')
});

// Funciones de la Base de Datos (MongoDB)
let bd = require('./lib/MongoDB');

app.post('/new', bd.newUser);                           // Crea un nuevo usuario
app.post('/login', bd.login);                           // Login
app.get('/select/name/:name', bd.selectName);           // Filtro por nombre (name)
app.get('/select/surname/:surname', bd.selectSurname);  // Filtro por apellidos (surname)
app.get('/select/role/:role', bd.selectRole);           // Filtro por rol (role)
app.get('/select/state/:state', bd.selectState);        // Filtro por estado (state): actiu, inactiu
app.get('/select/all', bd.selectAll);                   // Ordenación por orden alfabético (name)
app.get('/select/one/:id', bd.selectOne);               // Ver detalle de un usuario
app.post('/update/:id', bd.updateOne);                  // Modificar un usuario
app.get('/block/:id', bd.blockOne);                      // Bloquar un usuario

/*
// catch 404 and forward to error handler
app.use(function(req, res, next) {
    let err = new Error('Not Found');
    err.status = 404;
    next(err);
});
*/

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