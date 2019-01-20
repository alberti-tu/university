let express = require('express');
let path = require('path');
let favicon = require('serve-favicon');
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
let bd = require('./lib/mongoDB');

// Funciones sobre Usuarios
app.get('/users/select', bd.selectAllUsers);              // Devuelve una lista con todos los usuarios
app.get('/users/select/:name', bd.selectOneUser);         // Devuelve el usuario buscado
app.post('/users/insert', bd.insertUser);                 // Inserta un nuevo usuario (username único)
app.post('/users/update/:name', bd.updateUser);           // Actualiza la información de un usuario
app.get('/users/delete/:name', bd.deleteUser);            // Elimina de la Base de Datos el usuario buscado

// Funciones sobre Actividades
app.get('/activities/select', bd.selectAllActivities);    // Devuelve una lista con todas las actividades
app.get('/activities/select/:id', bd.selectOneActivity);  // Devuelve las actividad buscada
app.post('/activities/insert', bd.insertActivity);        // Inserta una nueva actividad
app.post('/activities/update/:id', bd.updateActivity);    // Actualiza la información de una actividad
app.get('/activities/delete/:id', bd.deleteActivity);     // Elimina de la Base de Datos la actividad buscada


// catch 404 and forward to error handler
app.use(function(req, res, next) {
    let err = new Error('Not Found');
    err.status = 404;
    next(err);
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