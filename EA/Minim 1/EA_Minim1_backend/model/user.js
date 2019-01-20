// Librerias y dependencias
let mongoose = require('mongoose');

// Declaración del esquema
let UserSchema = new mongoose.Schema(
    {
        name:       { type: String, required: true },
        surname:    { type: String, required: true },
        password:   { type: String, required: true },
        role:       { type: Boolean, required: true },
        state:      { type: Boolean, required: true }
    }
);

// Exporta el modelo a la Base de Datos
module.exports = mongoose.model('User', UserSchema);