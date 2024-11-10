package daos;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.CommonUserFactory;
import entity.User;
import org.bson.Document;
import usecases.account_creation.AccountCreationUserDataAccessInterface;
import database.MongoDBConnection;

public class UserDAO implements AccountCreationUserDataAccessInterface {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public UserDAO() {
        this.mongoClient = MongoDBConnection.getMongoClient();
        this.database = mongoClient.getDatabase("LinkUp");
        this.collection = database.getCollection("users");
    }

    @Override
    public boolean accountExists(String username) {
        Document query = new Document("username", username);
        return collection.find(query).first() != null;
    }

    @Override
    public void saveUser(User user) {
        Document document = new Document("username", user.getName())
                .append("password", user.getPassword())
                .append("language", user.getLanguage());
        collection.insertOne(document);
    }

    public void deleteUser(String username) {
        Document query = new Document("username", username);
        collection.deleteOne(query);
    }

    public User getUser(String name) {
        Document query = new Document("username", name);
        try {
            String username = collection.find(query).first().getString("username");
            String password = collection.find(query).first().getString("password");
            String language = collection.find(query).first().getString("language");

            CommonUserFactory userFactory = new CommonUserFactory();
            return userFactory.create(username, password, language);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
