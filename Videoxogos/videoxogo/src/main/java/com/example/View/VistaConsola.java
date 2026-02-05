package com.example.View;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.Controller.VideoxogoController;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;

public class VistaConsola {
    VideoxogoController controller = new VideoxogoController();

    public void consulta1 (){
        // 1. Puntuación total por xogador: Obtén a puntuación total acumulada de cada xogador
        // sumando as puntuacións de todas as súas partidas.
        // - Debe amosarse o nome do xogador e a puntuación total.
        // - Non é necesario ordenar o resultado.

        List<Bson> puntuacionTotal = Arrays.asList(
            Aggregates.group(
                "$xogador",
                 Accumulators.sum("puntuacion_toal", "$puntuacion")
                ),
            Aggregates.project(
                Projections.fields(
                    Projections.excludeId(),
                    Projections.include("puntuacion_total")
                )
            )
        );
        AggregateIterable<Document> iterDoc = controller.aggregateVideoxogo(puntuacionTotal);
        for(Document doc:iterDoc){
            System.out.println(doc);
        }

    }
}
