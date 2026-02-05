package com.example.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.Connection.MongoProvider;
import com.example.Model.Empleado;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;


public class EmpleadoController {
    MongoProvider mongo = new MongoProvider();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public void addEmpleado (Empleado empleado){
        Date fecha = null;
        try {
            fecha = sdf.parse(empleado.getFechaAlta());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Document newEmpleado = new Document("EmpNum",empleado.getID())
        .append("nombre", empleado.getNombre())
        .append("departamento",empleado.getDepartamento())
        .append("salario", empleado.getSalario())
        .append("fecha", fecha);

        if (empleado.getOficio() != null) {
            newEmpleado.append("oficio", empleado.getOficio());
        }
        if (empleado.getComision() != 0) {
            newEmpleado.append("comisión", empleado.getComision());
        }

        mongo.empleados().insertOne(newEmpleado);
    }

    public void removeEmpleado (Bson filter){
        mongo.empleados().deleteOne(filter);
    }

    public void modifyEmpleado (Bson filter, Document actualizacion){
        mongo.empleados().updateMany(filter, actualizacion);
    }
    
    public void getEmpleado(Bson filter){
        FindIterable<Document> iterDoc = mongo.empleados().find(filter);
        for (Document doc : iterDoc) {
            System.out.println(doc);
        }
    }

    public void aggregateEmpleado(List<Bson> pipeline){
        AggregateIterable<Document> iterDoc = mongo.empleados().aggregate(pipeline);
        for (Document doc : iterDoc) {
            System.out.println(doc);
        }
    }
}
