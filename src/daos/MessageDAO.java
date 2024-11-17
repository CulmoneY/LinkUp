package daos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.deepl.api.*;
import database.MongoDBConnection;
import entity.Message;
import entity.MessageFactory;
import entity.User;
import org.bson.Document;
import usecases.message_translation.MessageTranslationDataAccessInterface;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MessageDAO implements MessageTranslationDataAccessInterface {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> groupCollection;
    private final MongoCollection<Document> userCollection;
    private final MongoCollection<Document> translationsCollection;
    private final MessageFactory messageFactory;
    private final UserGroupDAO userGroupDAO;


    public MessageDAO(MessageFactory messageFactory, UserGroupDAO userGroupDAO) {
        this.userGroupDAO = userGroupDAO;
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
        String authkey = null;

        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/config.properties")) {
            properties.load(input);
            authkey = properties.getProperty("deepl.apikey");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        Translator translator = new Translator(authkey);
        TextResult result = translator.translateText(message, null, targetLanguage);

        return result.getText();
    }

    public List<Message> getMessagesByGroup(String groupName) {
        Document query = new Document("groupname", groupName);
        List<Message> messages = new ArrayList<>();
        for (Document doc : groupCollection.find(query).sort(new Document("timestamp", 1))) { // Sort by timestamp ascending
            User sender = getUserByName(doc.getString("sender"));
            messages.add(messageFactory.create(
                    sender,
                    doc.getString("message"),
                    doc.getString("language")
            ));
        }
        return messages;
    }

    private User getUserByName(String sender) {
        return userGroupDAO.getUser(sender);
    }
}
