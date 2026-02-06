package com.example.View;

import java.util.Arrays;
import java.util.List;

import javax.print.Doc;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.Controller.VideoxogoController;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

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
                    Accumulators.sum("puntuacion_total", "$puntuacion")
                    ),
                Aggregates.project(
                    Projections.fields(
                        Projections.excludeId(),
                        Projections.computed("xogador", "$_id"),
                        Projections.include("puntuacion_total")
                    )
                )
            );
            List<Document> iterDoc = controller.aggregateVideoxogo(puntuacionTotal);
            System.out.println("\nCONSULTA1:");
            for(Document doc:iterDoc){
                System.out.println(doc);
            }
        }
    
    public void consulta2 (){
        // Mellor partida de cada xogador: Para cada xogador, obtén a súa mellor puntuación
        // acadada nunha soa partida.
        List<Bson>mellorPartida = Arrays.asList(
            Aggregates.group(
                "$xogador",
                 Accumulators.max("max_puntuacion", "$puntuacion")
            ),
            Aggregates.project(
                Projections.fields(
                    Projections.excludeId(),
                    Projections.computed("xogador", "$_id"),
                    Projections.include("max_puntuacion")
                )
            )
        );
        List<Document>iterDoc = controller.aggregateVideoxogo(mellorPartida);
        System.out.println("\nCONSULTA2:");
        for(Document doc : iterDoc){
            System.out.println(doc);
        }
    }

    public void consulta3 (){
        // 3. Partida máis curta por xogo: Para cada xogo, obtén a duración mínima rexistrada
        // entre todas as partidas dese xogo.

        List<Bson>partidaCurta = Arrays.asList(
            Aggregates.group(
                "$xogo", 
                Accumulators.min("min_partida", "$duracion")
            ),
            Aggregates.project(
                Projections.fields(
                    Projections.excludeId(),
                    Projections.computed("xogo", "$_id"),
                    Projections.include("min_partida")
                )
            )
        );

        List<Document>iterDoc = controller.aggregateVideoxogo(partidaCurta);
        System.out.println("\nCONSULTA3");
        for (Document doc : iterDoc){
            System.out.println(doc);
        }
    }

    public void consulta4(){
        // 4. Ranking de xogadores: Obtén un ranking de xogadores ordenado de maior a menor
        // segundo a súa puntuación total acumulada.
        List<Bson>ranking = Arrays.asList(
            Aggregates.group(
                "$xogador",
                Accumulators.sum("puntuacion_total", "$puntuacion")
            ),
            Aggregates.sort(
                Sorts.descending("puntuacion_total")
            ),
            Aggregates.project(
                Projections.fields(
                    // Projections.excludeId(),
                    // Projections.computed("xogador", "$_id"),
                    Projections.include("puntuacion_total")
                )
            )
        );
        List<Document>iterDoc = controller.aggregateVideoxogo(ranking);
        System.out.println("\nCONSULTA4");
        for(Document doc:iterDoc){
            System.out.println(doc);
        }
    }

    public void consulta5 (){
        // 5. Listaxe simplificada de partidas: Obtén unha listaxe de partidas na que só se mostren
        // os campos:
        //     - xogador
        //     - xogo
        //     - puntuacion
        // O campo _id non debe aparecer.

        List<Bson> listaxe = Arrays.asList(
            Aggregates.project(
                Projections.fields(
                    Projections.excludeId(),
                    Projections.include("$xogador","$xogo","$puntuacion")
                )
            )
        );
        List<Document>iterDoc = controller.aggregateVideoxogo(listaxe);
        System.out.println("\nCONSULTA5");
        for(Document doc:iterDoc){
            System.out.println(doc);
        }
    }

    public void consulta6 (){
        // 6. Xogos máis puntuables: Calcula a puntuación media das partidas de cada xogo e
        // amosa o resultado ordenado de maior a menor puntuación media.

        List<Bson>xogosPuntuables = Arrays.asList(
            Aggregates.group(
                "$xogo",
                 Accumulators.avg("media_puntuacion", "$puntuacion")
            ),
            Aggregates.sort(
                Sorts.descending("media_puntuacion")
            ),
            Aggregates.project(
                Projections.fields(
                    Projections.include("media_puntuacion")
                )
            )
        );
        List<Document>iterDoc = controller.aggregateVideoxogo(xogosPuntuables);
        System.out.println("\nCONSULTA6");
        for(Document doc:iterDoc){
            System.out.println(doc);
        }
    }
}
