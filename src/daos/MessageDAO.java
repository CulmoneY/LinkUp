package daos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.deepl.api.*;
import database.MongoDBConnection;
import entity.Message;
import entity.MessageFactory;
import org.bson.Document;
import usecases.message_translation.MessageTranslationDataAccessInterface;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MessageDAO implements MessageTranslationDataAccessInterface {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> groupCollection;
    private final MongoCollection<Document> userCollection;
    private final MongoCollection<Document> translationsCollection;
    private final MessageFactory messageFactory;

    public MessageDAO(MessageFactory messageFactory) {
        this.mongoClient = MongoDBConnection.getMongoClient();
        this.database = mongoClient.getDatabase("LinkUp");
        this.groupCollection = database.getCollection("groups");
        this.userCollection = database.getCollection("users");
        this.translationsCollection = database.getCollection("translations");
        this.messageFactory = messageFactory;
    }

    @Override
    public boolean messageAlreadyTranslated(String message, String targetLanguage) {
        Document query = new Document("original_message", message)
                .append("target_language", targetLanguage);
        return translationsCollection.find(query).first() != null;
    }

    @Override
    public String getTranslatedMessage(String message, String targetLanguage) {
        Document query = new Document("original_message", message)
                .append("target_language", targetLanguage);
        Document result = translationsCollection.find(query).first();
        return result.getString("translated_message");
    }

    @Override
    public void saveTranslation(String message, String targetLanguage, String translatedMessage) {
        Document translation = new Document("original_message", message)
                .append("target_language", targetLanguage)
                .append("translated_message", translatedMessage);
        translationsCollection.insertOne(translation);
    }

    @Override
    public String translateMessage(String message, String targetLanguage) throws DeepLException, InterruptedException {
        String authkey = "DEEPLAPIKEY";
        Translator translator = new Translator(authkey);
        TextResult result = translator.translateText(message, null, targetLanguage);

        saveTranslation(message, targetLanguage, result.getText());

        return result.getText();
    }
}
