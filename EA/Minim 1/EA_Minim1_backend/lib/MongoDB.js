// Librerias y dependencias
let mongoose = require('mongoose');

// Conexión con la Base de Datos (MongoDB)
mongoose.connect('mongodb://localhost/minim1', function(err, res) {
    if(err) throw err;
    console.log('Connected to MongoDB');
});

// Declaración del esquema
let User = require('../model/user');

/***************************** ACCESO AL SISTEMA *****************************/

// Crea un nuevo usuario
exports.newUser = function (req, res) {
    User(req.body).save(function (err) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});     // Devuelve un JSON
        }else{
            return res.status(201).send({'result': 'INSERTADO'}); // Devuelve un JSON
        }
    });
};

// Login de administradores
exports.login = function (req, res) {
    User.findOne({ name: req.body.name, password: req.body.password, role: true, state: true }, { __v: false }, function (err, user) {
        if(user == null){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(user);                 // Devuelve un JSON
        }
    });
};

/********************************* LISTADOS **********************************/

// Filtro por nombre (name)
exports.selectName = function (req, res) {
    User.find({ name: req.params.name }, { password:false, __v: false }, function (err, user) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(user);                 // Devuelve un JSON
        }
    });
};

// Filtro por apellidos (surname)
exports.selectSurname = function (req, res) {
    User.find({ surname: req.params.surname }, { password:false, __v: false }, function (err, user) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(user);                 // Devuelve un JSON
        }
    });
};

// Filtro por rol (role)
exports.selectRole = function (req, res) {
    User.find({ role: req.params.role }, { password:false, __v: false }, function (err, user) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(user);                 // Devuelve un JSON
        }
    });
};

// Filtro por estado (state): actiu, inactiu
exports.selectState = function (req, res) {
    User.find({ state: req.params.state }, { password:false, __v: false }, function (err, user) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(user);                 // Devuelve un JSON
        }
    });
};

// Ordenación por orden alfabético (name)
exports.selectAll = function (req, res) {
    User.find({ }, { password: false, __v: false }, function (err, user) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            user.sort(function(a, b) {
                return (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0);
            } );
            return res.status(200).send(user);                 // Devuelve un JSON
        }
    });
};

// Ver detalle de un usuario
exports.selectOne = function (req, res) {
    User.findOne({ _id: req.params.id }, { password: false, __v: false }, function (err, user) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(user);                 // Devuelve un JSON
        }
    });
};

/******************************** OPERACIONES ********************************/

// Modificar un usuario
exports.updateOne = function (req, res) {
    User.update({ _id: req.params.id }, req.body, function (err) {
        if (err) {
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});       // Devuelve un JSON
        } else {
            return res.status(200).send({'result': 'ACTUALIZADO'}); // Devuelve un JSON
        }
    });
};

// Bloquar un usuario
exports.blockOne = function (req, res) {
    User.update({ _id: req.params.id }, { state: false }, function (err) {
        if (err) {
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});       // Devuelve un JSON
        } else {
            return res.status(200).send({'result': 'ACTUALIZADO'}); // Devuelve un JSON
        }
    });
};
