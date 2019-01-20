// Librerias y dependencias
let mongoose = require('mongoose');

// Declaración del esquema
let ActivitySchema = new mongoose.Schema(
    {
        name:          { type: String, required: true },
        user:          { type: String, required: true },
        description:   { type: String, required: true }
    }
);

// Exporta el modelo y el esquema a la Base de Datos
module.exports.schema = ActivitySchema;
module.exports.model = mongoose.model('Activity', ActivitySchema);
