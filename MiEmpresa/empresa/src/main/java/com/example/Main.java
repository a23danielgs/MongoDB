package com.example;



import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.Controller.EmpleadoController;
import com.example.Model.Empleado;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;


public class Main {
    public static void main(String[] args) {
        EmpleadoController ec = new EmpleadoController();


        ec.addEmpleado(new Empleado(1,"Juan",10,1000,"1999-10-10"));
        ec.addEmpleado(new Empleado(2, "Alicia", 10, 1400, "2000-08-07","Profesora"));
        ec.addEmpleado(new Empleado(3, "María Jesús", 20, 1500, "2005-01-05","Analista",100));
        ec.addEmpleado(new Empleado(4, "Alberto", 20, 1100, "2001-11-15"));
        ec.addEmpleado(new Empleado(5, "Fernando", 30, 1400, "1999-11-20","Analista",200));


        // Visualiza los empleados del departamento 10.
        System.out.println("Visualiza los empleados del departamento 10.");
        ec.getEmpleado(new Document("departamento",10));

        // Visualiza los empleados del departamento 10 y 20 .
        System.out.println("\nVisualiza los empleados del departamento 10 y 20.");
        ec.getEmpleado(new Document("departamento",new Document("$in",Arrays.asList(10,20))));


        // Obtén los empleados con salario > 1300 y oficio Profesora.
        System.out.println("\nObtén los empleados con salario > 1300 y oficio Profesora.");
        ec.getEmpleado(new Document("salario",new Document("$gt",1300)).append("oficio", "Profesora"));
 

        // Sube el salario de los analistas en 100€, a todos los analistas.
        System.out.println("\nSube el salario de los analistas en 100€, a todos los analistas.");
        System.out.println("ANTES:");
        ec.getEmpleado(new Document("oficio","Analista")); 
        ec.modifyEmpleado(new Document("oficio","Analista"), new Document("$inc",new Document("salario",100)));
        System.out.println("DESPUES:");
        ec.getEmpleado(new Document("oficio","Analista"));

        // Decrementa la comisión existente en 20€.                                     
        System.out.println("\nDecrementa la comisión existente en 20€.");
        System.out.println("ANTES:");
        ec.getEmpleado(new Document("comisión",new Document("$gt",20))); 
        ec.modifyEmpleado(new Document("comisión",new Document("$gt",0)),new Document("$inc",new Document("comisión",-20)));
        System.out.println("DESPUES:");
        ec.getEmpleado(new Document("comisión",new Document("$gt",0)));
            
        // Visualiza la media de salario.
        System.out.println("\nVisualiza la media de salario.");
        List<Bson>Media = Arrays.asList(
            Aggregates.group(
                null,
                Accumulators.avg("mediaSalario","$salario")
            ),
            Aggregates.project(
                Projections.fields(
                    Projections.excludeId()
                )
            )
        );
        ec.aggregateEmpleado(Media);

        // Visualiza por departamento el número de empleados, el salario medio y el máximo salario.
        System.out.println("\nVisualiza por departamento el número de empleados, el salario medio y el máximo salario.");
        for (int i = 10; i < 31; i=i+10) {
            List<Bson>NumAvgMax = Arrays.asList(
                Aggregates.match(new Document("departamento",i)),
                Aggregates.group(
                    null,
                    Accumulators.sum("totalEmpleados", 1),
                    Accumulators.avg("mediaSalario", "$salario"),
                    Accumulators.max("maxSalario", "$salario")
                ),
                Aggregates.project(
                    Projections.fields(
                        Projections.excludeId()
                    )
                )
            );
            ec.aggregateEmpleado(NumAvgMax);
        }

        // Visualiza el nombre del empleado que tiene el máximo salario.
        System.out.println("\nVisualiza el nombre del empleado que tiene el máximo salario.");
        List<Bson>nameMaxSalary = Arrays.asList(
            Aggregates.sort(Sorts.descending("salario")),
            Aggregates.limit(1),
            Aggregates.project(
                Projections.fields(
                    Projections.include("nombre"),
                    Projections.excludeId()
                )
            )
        );
        ec.aggregateEmpleado(nameMaxSalary);
    }
}








