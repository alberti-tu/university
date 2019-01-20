// Librerias y dependencias
let mongoose = require('mongoose');
let Activity = require('./activity').schema;

// Declaración del esquema
let UserSchema = new mongoose.Schema(
    {
        username: { type: String, required: true, unique: true },
        password: { type: String, required: true },
        wallet:   { type: Number, required: true },
        listaOfertada:
        [
            Activity
        ],
        listaRecibida:
        [
            Activity
        ]
    }
);

// Exporta el modelo a la Base de Datos
module.exports = mongoose.model('User', UserSchema);