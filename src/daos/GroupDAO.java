package daos;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.*;
import org.bson.Document;
import database.MongoDBConnection;
import usecases.create_group.CreateGroupDataAccessInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO implements CreateGroupDataAccessInterface {
    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> groupCollection;
    private final MessageFactory messageFactory;
    private final GroupFactory groupFactory;
    private final CalendarFactory calendarFactory;
    private final UserFactory userFactory;
    private final EventFactory eventFactory;

    public GroupDAO(GroupFactory groupFactory, MessageFactory messageFactory, CalendarFactory calendarFactory, UserFactory userFactory, EventFactory eventFactory) {
        this.mongoClient = MongoDBConnection.getMongoClient();
        this.database = mongoClient.getDatabase("LinkUp");
        this.groupCollection = database.getCollection("groups");
        this.groupFactory = groupFactory;
        this.messageFactory = messageFactory;
        this.calendarFactory = calendarFactory;
        this.userFactory = userFactory;
        this.eventFactory = eventFactory;
    }

    public Group getGroup(String groupName) {

        Document query = new Document("groupname", groupName);
        Document groupDoc = groupCollection.find(query).first();
        if (groupDoc == null) return null;
        String groupname = groupDoc.getString("groupname");
        List<Document> userDocs = (List<Document>) groupDoc.get("users");
        List<User> users = deserializeUsers(userDocs);


        Document calendarDoc = (Document) groupDoc.get("calendar");
        Calendar calendar = deserializeCalendar(calendarDoc);

        List<Document> messageDoc = (List<Document>) groupDoc.get("messages");
        List<Message> messages = deserializeMessages(messageDoc);

        Group group = groupFactory.create(groupname, users);

        for (Message message : messages) {
            group.addMessage(message);
        }

        group.setGroupCalendar(calendar);

        return group;

    }



    public boolean groupExist(String groupName) {
        Document query = new Document("groupname", groupName);
        return groupCollection.find(query).first() != null;
    }

    public void saveGroup(Group group)  {
        Document groupDoc = new Document("groupname", group.getName())
                .append("messages", serializeMessages(group.getMessages()))
                .append("users", serializeUsers(group.getUsers()))
                .append("calendar", serializeCalendar(group.getGroupCalendar()));

        groupCollection.insertOne(groupDoc);
    }

    @Override
    public List<User> groupMembersToUsers(List<String> groupMembers) {
        List<User> users = new ArrayList<>();
        for (String groupMember : groupMembers) {
            User groupMemberUser = getUser(groupMember);
            users.add(groupMemberUser);
        }
        return users;
    }

    public void deleteGroup(String groupName) {
        Document query = new Document("groupname", groupName);
        groupCollection.deleteOne(query);
    }

    private User getUser(String username) {
        MongoCollection<Document> userCollection = database.getCollection("users");
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

    private List<Document> serializeMessages (List<Message> messages) {
        List<Document> messageDocs = new ArrayList<>();
        for (Message message : messages) {
            Document messageDoc = new Document("message", message.getMessage())
                    .append("sender", message.getSender().getName())
                    .append("time", message.getTime().toString());
            messageDocs.add(messageDoc);
        }
        return messageDocs;
    }

    private List<Document> serializeUsers (List<User> users) {
        List<Document> userDocs = new ArrayList<>();
        for (User user : users) {
            Document userDoc = new Document("username", user.getName())
                    .append("language", user.getLanguage());
            userDocs.add(userDoc);
        }
        return userDocs;
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

    private List<Message> deserializeMessages (List<Document> messageDocs) {
        List<Message> messages = new ArrayList<>();
        for (Document messageDoc : messageDocs) {
            String textmessage = messageDoc.getString("message");
            User sender = userFactory.create(messageDoc.getString("sender"), "defaultpassword", "defaultlanguage");
            LocalDateTime time = LocalDateTime.parse(messageDoc.getString("time"));
            Message message = messageFactory.create(sender, textmessage);
            message.setTime(time);
            messages.add(message);
        }
        return messages;
    }

    private List<User> deserializeUsers (List<Document> userDocs) {
        List<User> users = new ArrayList<>();
        for (Document userDoc : userDocs) {
            String username = userDoc.getString("username");
            String language = userDoc.getString("language");
            User user = userFactory.create(username, "defaultpassword", language);
            users.add(user);
        }
        return users;
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
                    true
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

    private List<User> deserializeFriends(List<Document> friendDocs) {
        List<User> friends = new ArrayList<>();
        for (Document friendDoc : friendDocs) {
            // Mock class for the friend
            User friend = userFactory.create(friendDoc.getString("username"), "defaultpassword", friendDoc.getString("language"));
            friends.add(friend);
        }
        return friends;
    }
}
