package com.example.Controller;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.Connection.MongoProvider;
import com.example.Model.Videoxogo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;

public class VideoxogoController {

    public void addVideoxogo(Videoxogo videoxogo){
        try (MongoProvider mongo = new MongoProvider()) {

            Document newVideoxogo = new Document("xogador",videoxogo.getXogador())
            .append("xogo", videoxogo.getXogo())
            .append("puntuacion", videoxogo.getPuntuacion())
            .append("duracion", videoxogo.getDuracion())
            .append("nivel", videoxogo.getNivel());

            mongo.partidas().insertOne(newVideoxogo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeVideoxogo(Bson filter){
        try (MongoProvider mongo = new MongoProvider()) {
            mongo.partidas().deleteOne(filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateVideoxogo(Bson filter,Document actualizacion){
        try (MongoProvider mongo = new MongoProvider()) {
            mongo.partidas().updateMany(filter, actualizacion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FindIterable<Document> getVideoxogo(Bson filter){
        FindIterable<Document>iterDoc = null;
        try (MongoProvider mongo = new MongoProvider()) {
            iterDoc = mongo.partidas().find(filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iterDoc;
    }

    public AggregateIterable<Document> aggregateVideoxogo(List<Bson> pipeline){
        AggregateIterable<Document>iterDoc = null;
        try (MongoProvider mongo = new MongoProvider()) {
            iterDoc = mongo.partidas().aggregate(pipeline);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iterDoc;
    }
}
