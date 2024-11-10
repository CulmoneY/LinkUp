package database;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
import java.io.FileInputStream;
import java.util.Properties;

public class MongoDBConnection {
    private static MongoClient mongoClient;

    static {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/config.properties")) {
            properties.load(input);
            String connectionString = properties.getProperty("mongodb.connectionString");
            mongoClient = MongoClients.create(connectionString);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }
}

