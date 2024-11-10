package daos;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.*;
import org.bson.Document;
import usecases.account_creation.AccountCreationUserDataAccessInterface;
import database.MongoDBConnection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements AccountCreationUserDataAccessInterface {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;
    private final UserFactory userFactory;
    private final EventFactory eventFactory;
    private final CalendarFactory calendarFactory;

    public UserDAO(UserFactory userFactory, EventFactory eventFactory, CalendarFactory calendarFactory) {
        this.mongoClient = MongoDBConnection.getMongoClient();
        this.database = mongoClient.getDatabase("LinkUp");
        this.userCollection = database.getCollection("users");
        this.userFactory = userFactory;
        this.eventFactory = eventFactory;
        this.calendarFactory = calendarFactory;
    }

    @Override
    public boolean accountExists(String username) {
        Document query = new Document("username", username);
        return userCollection.find(query).first() != null;
    }

    @Override
    public void saveUser(User user) {
        Document userDoc = new Document("username", user.getName())
                .append("password", user.getPassword())
                .append("language", user.getLanguage())
                .append("friends", serializeFriends(user.getFriends()))
                .append("calendar", serializeCalendar(user.getUserCalendar()))
                .append("groups", serializeGroups(user.getGroups()));
        userCollection.insertOne(userDoc);
    }

    public void deleteUser(String username) {
        Document query = new Document("username", username);
        userCollection.deleteOne(query);
    }

    public User getUser(String username) {
        Document query = new Document("username", username);
        Document userDoc = userCollection.find(query).first();
        if (userDoc == null) return null;

        String name = userDoc.getString("username");
        String password = userDoc.getString("password");
        String language = userDoc.getString("language");
        User user = userFactory.create(name, password, language);

        List<Document> friendDocs = (List<Document>) userDoc.get("friends");
        List<User> friends = deserializeFriends(friendDocs);
        user.setFriends(friends);

        Document calendarDoc = (Document) userDoc.get("calendar");
        Calendar calendar = deserializeCalendar(calendarDoc);
        user.setUserCalendar(calendar);

        List<Document> groupDocs = (List<Document>) userDoc.get("groups");
        List<Group> groups = deserializeGroups(groupDocs);
        for (Group group : groups)
            user.addGroup(group);
        return user;
    }

    private List<Document> serializeFriends(List<User> friends) {
        List<Document> friendDocs = new ArrayList<>();
        for (User friend : friends) {
            friendDocs.add(new Document("username", friend.getName())
                    .append("language", friend.getLanguage()));
        }
        return friendDocs;
    }

    private Document serializeCalendar(Calendar calendar) {
        List<Document> eventDocs = new ArrayList<>();
        for (Event event : calendar.getEvents()) {
            eventDocs.add(new Document("eventName", event.getEventName())
                    .append("startTime", event.getStartTime().toString())
                    .append("endTime", event.getEndTime().toString()));
        }
        return new Document("name", calendar.getName())
                .append("events", eventDocs);
    }

    private List<Document> serializeGroups(List<Group> groups) {
        List<Document> groupDocs = new ArrayList<>();
        for (Group group : groups) {
            groupDocs.add(new Document("groupName", group.getName()));
        }
        return groupDocs;
    }

    private List<User> deserializeFriends(List<Document> friendDocs) {
        List<User> friends = new ArrayList<>();
        for (Document friendDoc : friendDocs) {
            // Mock class for the friend
            User friend = userFactory.create(friendDoc.getString("username"), "defaultpassword", friendDoc.getString("language"));
            friends.add(friend);
        }
        return friends;
    }

    private Calendar deserializeCalendar(Document calendarDoc) {
        if (calendarDoc == null) return null;

        String name = calendarDoc.getString("name");
        List<Document> eventDocs = (List<Document>) calendarDoc.get("events");
        List<Event> events = new ArrayList<>();
        for (Document eventDoc : eventDocs) {
            Event event = eventFactory.create(
                    eventDoc.getString("eventName"),
                    LocalDateTime.parse(eventDoc.getString("startTime")),
                    LocalDateTime.parse(eventDoc.getString("endTime")),
                    false
            );
            events.add(event);
        }
        Calendar calendar = calendarFactory.create(name);
        calendar.setEvents(events);
        return calendar;
    }

    private List<Group> deserializeGroups(List<Document> groupDocs) {
        // TODO: Add the functionality as specified
        /*
        Note the groupDocs store the name of each group the user is in. In this case, the group name will be treated as a unqiue
        ID for the specified group. Once we implement the groupDAO, we can use getGroup() from there.
         */
        return new ArrayList<>();
    }

}
