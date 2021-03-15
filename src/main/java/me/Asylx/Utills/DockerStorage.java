package me.Asylx.Utills;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.sound.sampled.Port;
import java.io.File;
import java.net.UnknownHostException;

public class DockerStorage {

    public static MongoDatabase mongoDatabase;
    public static MongoCollection mongoCollection;

    public static void SetupMongoDB() throws UnknownHostException {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://Admin:admin@cluster0.lvr4u.mongodb.net/Data?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        mongoDatabase = mongoClient.getDatabase("Data");
        mongoCollection = mongoDatabase.getCollection("Data");

        System.out.println("Database CONNECTED.");
    }

    public static Document getData(String value) {
        Document data = (Document) mongoCollection.find(new Document("Name", value)).first();
        return data;
    }

    public static void replaceData(String ID, String object, Object newValueObject) {
        Document data = (Document) mongoCollection.find(new Document("ID", ID)).first();

        if (data != null) {
            Bson newValue = new Document(object, newValueObject);
            Bson updatedOperation = new Document("$set", newValue);
            mongoCollection.updateOne(data, updatedOperation);

            System.out.println("OPERATION UPDATED.");
        } else {
            System.out.println("FAILED. NO DATA FOUND!");
        }

    }

    public static void addContainerData(String ID, String Amount, int port) {
        Document data = new Document();

        data.append("ID", ID);
        data.append("TYPE", "CONTAINER");
        data.append("Name", Amount);
        data.append("Port", port);

        mongoCollection.insertOne(data);
    }

    public static void deleteDocument(String ID) {
        Document data = (Document) mongoCollection.find(new Document("ID", ID)).first();

        data.remove(data);
    }

}



