// Librerias y dependencias
let mongoose = require('mongoose');

// Conexión con la Base de Datos (MongoDB)
mongoose.connect('mongodb://localhost/bancOfTime', function(err, res) {
    if(err) throw err;
    console.log('Connected to MongoDB');
});

// Declaración del esquema
let User = require('../model/user');
let Activity = require('../model/activity');

/***************************** FUNCIONES SOBRE USUARIOS *****************************/

// Devuelve una lista con todos los usuarios
exports.selectAllUsers = function (req, res) {
    User.find({}, { __v: false })
        .populate('listaOfertada').populate('listaRecibida')
        .exec( function (err, users) {
            if (err) {
                console.log(err);
                return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
            } else {
                return res.status(200).send(users);                // Devuelve un JSON
            }
        }
    );
};

// Devuelve el usuario buscado
exports.selectOneUser = function (req, res) {
    User.findOne({ username: req.params.name }, { __v: false })
        .populate('listaOfertada').populate('listaRecibida')
        .exec( function (err, user) {
            if(err){
                console.log(err);
                return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
            }else{
                return res.status(200).send(user);                 // Devuelve un JSON
            }
        }
    );
};

// Inserta un nuevo usuario (username único)
exports.insertUser = function (req, res) {
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
exports.updateUser = function (req, res) {
    User.update({ username: req.params.name }, req.body, function(err) {
        if (err) {
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});       // Devuelve un JSON
        }else{
            return res.status(200).send({'result': 'ACTUALIZADO'}); // Devuelve un JSON
        }
    });
};

// Elimina de la Base de Datos el usuario buscado
exports.deleteUser = function (req, res) {
    User.remove({ username: req.params.name }, function(err) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});     // Devuelve un JSON
        }else{
            return res.status(200).send({'result': 'ELIMINADO'}); // Devuelve un JSON
        }
    });
};

/*************************** FUNCIONES SOBRE ACTIVIDADES *****************************/

// Devuelve una lista con todas las actividades
exports.selectAllActivities = function (req, res) {
    Activity.find({}, { __v: false }, function (err, activities) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(activities);           // Devuelve un JSON
        }
    });

};

// Devuelve las actividad buscada
exports.selectOneActivity = function (req, res) {
    Activity.findOne({ _id: req.params.id }, { __v: false }, function (err, activity) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});  // Devuelve un JSON
        }else{
            return res.status(200).send(activity);             // Devuelve un JSON
        }
    });
};

// Inserta una nueva actividad
exports.insertActivity = function (req, res) {
    Activity(req.body).save(function (err) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});     // Devuelve un JSON
        }else{
            return res.status(201).send({'result': 'INSERTADO'}); // Devuelve un JSON
        }
    });
};

// Actualiza la información de una actividad
exports.updateActivity = function (req, res) {
    Activity.update({ name: req.params.id }, req.body, function(err) {
        if (err) {
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});       // Devuelve un JSON
        }else{
            return res.status(200).send({'result': 'ACTUALIZADO'}); // Devuelve un JSON
        }
    });
};

// Elimina de la Base de Datos la actividad buscada
exports.deleteActivity = function (req, res) {
    Activity.remove({ name: req.params.id }, function(err) {
        if(err){
            console.log(err);
            return res.status(202).send({'result': 'ERROR'});     // Devuelve un JSON
        }else{
            return res.status(200).send({'result': 'ELIMINADO'}); // Devuelve un JSON
        }
    });
};