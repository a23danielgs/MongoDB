package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.Connection.MongoProvider;
import com.example.Model.Videoxogo;

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

    public List<Document> getVideoxogo(Bson filter){
        List<Document>iterDoc = new ArrayList<>();
        try (MongoProvider mongo = new MongoProvider()) {
            mongo.partidas().find(filter).into(iterDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iterDoc;
    }

    public List<Document> aggregateVideoxogo(List<Bson> pipeline){
        List<Document>iterDoc = new ArrayList<>();
        try (MongoProvider mongo = new MongoProvider()) {
            mongo.partidas().aggregate(pipeline).into(iterDoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iterDoc;
    }
}
