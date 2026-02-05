// use tienda
// db.createCollection("clientes")
// db.createCollection("pedidos")

// 1. Insertar 2 clientes y 3 pedidos.
db.clientes.insertMany([
    {
        "idCliente":1,
        "nombre":"Manuel",
        "emails":["mnl@gmail.com","mnl@gmail.es","ManolitoGanador@gmail.com"],
        "direcciones": [
            {
                "calle": "Mayor",
                "numero": 12,
                "ciudad": "Madrid"
            },
            {
                "calle": "San Juan",
                "numero": 5,
                "ciudad": "Sanclemente"
            }
        ]
    },
    {
        "idCliente":2,
        "nombre":"Diana",
        "emails":["dnaa@gmail.com","diana@gmail.es"],
        "direcciones": [
            {
                "calle": "Menor",
                "numero": 2,
                "ciudad": "Barcelona"
            },
            {
                "calle": "San Jorge",
                "numero": 50,
                "ciudad": "Sanclemente"
            }
        ]
    }
])
db.pedidos.insertMany([
    {
        "idPedido": 1,
        "idCliente": 1,
        "fecha": new Date("2026-01-23"),
        "lineas": [
            { 
                "sku": "A1", 
                "nombre": "Camiseta", 
                "cantidad": 2, 
                "precio": 15.0 
            },
            { 
                "sku": "B2", 
                "nombre": "Pantalón", 
                "cantidad": 1, 
                "precio": 35.0 
            }
        ],
        "estado": "pendiente"
    },
    {
        "idPedido": 2,
        "idCliente": 2,
        "fecha": new Date("2026-01-22"),
        "lineas": [
            { 
                "sku": "C3", 
                "nombre": "Zapatos", 
                "cantidad": 1, 
                "precio": 50.0 
            },
            { 
                "sku": "A1", 
                "nombre": "Camiseta", 
                "cantidad": 3, 
                "precio": 15.0 

            }
        ],
        "estado": "enviado"
    },
    {
        "idPedido": 3,
        "idCliente": 2,
        "fecha": new Date("2026-01-21"),
        "lineas": [
            { 
                "sku": "D4", 
                "nombre": "Gorra", 
                "cantidad": 200, 
                "precio": 10.0 
            }
        ],
        "estado": "entregado"
    }
])

// 2. Pedidos de un cliente por idCliente.
db.pedidos.find({"idCliente":2})

// 3. Pedidos con cantidad > 100
db.pedidos.find({"lineas.cantidad":{$gt:100}})

db.pedidos.aggregate([
    {
        $project:{
            _id:0,
            idPedido:1,
            idCliente:1,
            fecha:1,
            estado:1,
            cantidadTotal:{ $sum:"$lineas.cantidad" }
        }
    },
    {
        $match:{
            cantidadTotal: {$gt:100}
        }
    }
])

db.pedidos.aggregate([
    { $unwind: "$lineas" },
    { $match: { "lineas.cantidad": { $gt:100 } } },
    { $project: {
        _id: 0,
        idPedido: 1,
        idCliente: 1,
        fecha: 1,
        estado: 1,
        linea: "$lineas"   
    }}
])

//4. Cambiar el estado de un pedido
db.pedidos.updateOne(
    {"idPedido":2},
    {$set:{"estado":"entregado"}}
)

// 5. Cambiar el email principal de un cliente.
db.clientes.updateOne(
    {"idCliente":2},
    {$set:{"emails.0":"DNAA@gmail.com"}}
)