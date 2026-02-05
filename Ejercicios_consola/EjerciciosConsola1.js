// use domotica
// db.createCollection("mediciones")

// 1. Insertar 3 mediciones con fechas distintas.
db.mediciones.insertMany([
  {
    "sensorId":1,
    "ts":new Date(),
    "tipo":{
      "temp":10,
      "hum":"25%"
    },
    "valor":20,
    "ubicacion":"Sanclemente"
  },
  {
    "sensorId":2,
    "ts":new Date("2000-03-10"),
    "tipo":{
      "temp":20,
      "hum":"30%"
    },
    "valor":21,
    "ubicacion":"Sanclemente"
  },
  {
    "sensorId":3,
    "ts":new Date("1990-09-20"),
    "tipo":{
      "temp":15,
      "hum":"15%"
    },
    "valor":22,
    "ubicacion":"Marruecos"
  },
])

// 2. Consultar por sensorId y rango ts (últimas 24h).
db.mediciones.find({"sensorId":1,"ts":{$gt:new Date(Date.now() - 24 * 60 * 60 * 1000)}})
// db.mediciones.find({"ts":{$gt:new Date(Date.now() - 24 * 60 * 60 * 1000)}})

// 3. Consultar mediciones por encima de umbral.
db.mediciones.find({"valor":{$gt:20}})

// 4. Corregir mediciones erróneas (updateMany).

db.mediciones.updateMany(
  {"ubicacion":"Sanclemente"},
  {$set:{"tipo":"hum"}}
)

db.mediciones.updateMany(
  {"ubicacion":"Sanclemente"},
  {$set:{"tipo.hum":"20%"}}
)

// 5. Eliminar mediciones anteriores a una fecha (deleteMany).
db.mediciones.deleteMany(
  {"ts":{$lt:new Date("2026-01-20")}}
)
