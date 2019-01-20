// Librerias y dependencias
let mongoose = require('mongoose');

// Conexión con la Base de Datos (MongoDB)
mongoose.connect('mongodb://localhost/node', function(err, res) {
    if(err) throw err;
    console.log('Connected to MongoDB');
});

// Declaración del esquema
let User = require('../model/user');

// Devuelve una lista con todos los usuarios
exports.selectAll = function (req, res) {
    User.find({}, { __v : false }, function (err, users) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(users);                // Devuelve un JSON
        }
    });
};

// Devuelve el usuario buscado
exports.selectOne = function (req, res) {
    User.findOne({ username: req.params.name }, { __v : false }, function (err, user) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(user);                 // Devuelve un JSON
        }
    });
};

// Inserta un nuevo usuario (username único)
exports.insert = function (req, res) {
    User(req.body).save(function (err) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});     // Devuelve un JSON
        }else{
            return res.status(201).send({'result': 'INSERTADO'}); // Devuelve un JSON
        }
    });
};

// Actualiza la información de un usuario
exports.update = function (req, res) {
    User.update({ username: req.params.name }, req.body, function(err) {
        if (err) {
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});     // Devuelve un JSON
        }else{
            return res.status(200).send({'result': 'ACTUALIZADO'}); // Devuelve un JSON
        }
    });
};

// Elimina de la Base de Datos el usuario buscado
exports.delete = function (req, res) {
    User.remove({ username: req.params.name }, function(err) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});     // Devuelve un JSON
        }else{
            return res.status(200).send({'result': 'ELIMINADO'}); // Devuelve un JSON
        }
    });
};