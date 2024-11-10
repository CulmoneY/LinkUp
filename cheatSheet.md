# LinkUp Cheatsheet:
## 1. `Calendar` Interface

The `Calendar` interface manages events and associates with a specific owner, which could be a `User` or a `Group`.

- **Methods**
  - `getName()`: Returns the name of the calendar.
  - `setName(String name)`: Sets the name of the calendar.
  - `getEvents()`: Returns a list of events in the calendar.
  - `setEvents(List<Event> events)`: Sets a list of events.
  - `addEvent(Event event)`: Adds an event to the calendar.
  - `removeEvent(Event event)`: Removes an event from the calendar.

## 2. `Event` Interface

The `Event` interface represents an event with a name, start and end times.

- **Methods**
  - `getEventName()`: Returns the name of the event.
  - `setEventName(String eventName)`: Sets the name of the event.
  - `getStartTime()`: Returns the start time of the event (`LocalDateTime`).
  - `setStartTime(LocalDateTime time)`: Sets the start time of the event.
  - `getEndTime()`: Returns the end time of the event (`LocalDateTime`).
  - `setEndTime(LocalDateTime time)`: Sets the end time of the event.
  - `toString()`: Returns a string representation of the event details.

## 3. `Group` Interface

The `Group` interface represents a group of users and includes functionality for managing users, a group calendar, and messages.

- **Methods**
  - `getName()`: Returns the name of the group.
  - `setName(String name)`: Sets the name of the group.
  - `getUsers()`: Returns a list of users in the group.
  - `setUsers(List<User> users)`: Sets the list of users in the group.
  - `addUser(User user)`: Adds a user to the group.
  - `removeUser(User user)`: Removes a user from the group.
  - `getGroupCalendar()`: Returns the group's calendar.
  - `updateGroupCalendar()`: Updates the group's calendar.
  - `getMessages()`: Returns a list of messages within the group.
  - `addMessage(Message message)`: Adds a message to the group chat.
  - `addGroupEvent(Event event)`: Adds an event to the group's calendar.
  - `removeGroupEvent(Event event)`: Removes an event from the group's calendar.

## 4. `User` Interface

The `User` interface represents a user in the system with personal attributes, friends, groups, and a personal calendar.

- **Methods**
  - `getName()`: Returns the username.
  - `setName(String name)`: Sets the username.
  - `getPassword()`: Returns the user's password.
  - `setPassword(String password)`: Sets the user's password.
  - `getFriends()`: Returns a list of the user's friends.
  - `setFriends(List<User> friends)`: Sets the user's list of friends.
  - `addFriend(User friend)`: Adds a friend to the user's friend list.
  - `removeFriend(User friend)`: Removes a friend from the user's friend list.
  - `getUserCalendar()`: Returns the user's personal calendar.
  - `setUserCalendar(Calendar userCalendar)`: Sets the user's personal calendar.
  - `getLanguage()`: Returns the user's preferred language.
  - `setLanguage(String language)`: Sets the user's language preference.
  - `getGroups()`: Returns a list of groups the user is part of.
  - `addGroup(Group group)`: Adds a group to the user's group list.
  - `removeGroup(Group group)`: Removes a group from the user's group list.

## 5. `Message` Interface

The `Message` interface represents a message with sender, receiver, content, and timestamp information.

- **Methods**
  - `getSender()`: Returns the sender of the message (`User`).
  - `setSender(User sender)`: Sets the sender of the message.
  - `getReceiver()`: Returns the receiver of the message (`Group`).
  - `setReceiver(Group receiver)`: Sets the receiver of the message.
  - `getMessage()`: Returns the content of the message.
  - `setMessage(String message)`: Sets the content of the message.
  - `getTime()`: Returns the timestamp of the message (`LocalDateTime`).

## Java `LocalDateTime`

The `LocalDateTime` class in Java allows you to work with date and time without timezone info.

## Basic Usage

### Import `LocalDateTime`

```java
import java.time.LocalDateTime;
```

### 1. Current Date and Time

```java
LocalDateTime now = LocalDateTime.now();
System.out.println("Current DateTime: " + now);
```

### 2. Set Specific Date and Time

Use `of` to specify date and time (Year, Month, Day, Hour, Minute, Second).

```java
LocalDateTime dateTime = LocalDateTime.of(2023, 11, 4, 15, 30, 45);
System.out.println("Set DateTime: " + dateTime);
```

### 3. Modify Existing Date and Time

Use `with` to change specific parts of an existing date and time.

```java
LocalDateTime modified = LocalDateTime.now()
    .withYear(2023).withMonth(11).withDayOfMonth(4)
    .withHour(15).withMinute(30).withSecond(45);
```

- **Add or Subtract Time**:
  ```java
  LocalDateTime tomorrow = dateTime.plusDays(1);  // Adds 1 day
  LocalDateTime nextMonth = dateTime.plusMonths(1);  // Adds 1 month
  LocalDateTime nextYear = dateTime.plusYears(1);  // Adds 1 year
  LocalDateTime inFiveHours = dateTime.plusHours(5);  // Adds 5 hours

  LocalDateTime lastWeek = dateTime.minusWeeks(1);  // Subtracts 1 week
  LocalDateTime tenMinutesAgo = dateTime.minusMinutes(10);  // Subtracts 10 minutes
  ```

For more, see the [official documentation](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html).

# MongoDB Java Basics

This section provides an overview of common MongoDB operations in Java using the MongoDB Java Driver. These methods cover creating, reading, updating, and deleting documents in a MongoDB database.

### MongoDB Setup

Before using MongoDB in Java, make sure to include the MongoDB Java Driver in your project dependencies (e.g., Maven or Gradle).

#### Basic Setup

```java
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
MongoDatabase database = mongoClient.getDatabase("databaseName");
MongoCollection<Document> collection = database.getCollection("collectionName");
```

### CRUD Operations (Create, Read, Update, Delete)

#### Create

- **Insert a Single Document**

  ```java
  Document doc = new Document("key", "value")
                      .append("anotherKey", "anotherValue");
  collection.insertOne(doc);
  ```

- **Insert Multiple Documents**

  ```java
  List<Document> documents = Arrays.asList(
      new Document("key1", "value1"),
      new Document("key2", "value2")
  );
  collection.insertMany(documents);
  ```

#### Read

- **Find All Documents**

  ```java
  for (Document doc : collection.find()) {
      System.out.println(doc.toJson());
  }
  ```

- **Find Documents with a Filter**

  ```java
  Document query = new Document("key", "value");
  for (Document doc : collection.find(query)) {
      System.out.println(doc.toJson());
  }
  ```

- **Find One Document**

  ```java
  Document query = new Document("key", "value");
  Document doc = collection.find(query).first();
  if (doc != null) {
      System.out.println(doc.toJson());
  }
  ```

- **Projection**

  ```java
  Document projection = new Document("field1", 1).append("field2", 0); // 1 to include, 0 to exclude
  for (Document doc : collection.find().projection(projection)) {
      System.out.println(doc.toJson());
  }
  ```

#### Update

- **Update One Document**

  ```java
  Document query = new Document("key", "value");
  Document update = new Document("$set", new Document("newKey", "newValue"));
  collection.updateOne(query, update);
  ```

- **Update Multiple Documents**

  ```java
  Document query = new Document("key", "value");
  Document update = new Document("$set", new Document("newKey", "newValue"));
  collection.updateMany(query, update);
  ```

- **Replace a Document**

  ```java
  Document query = new Document("key", "value");
  Document replacement = new Document("newKey", "newValue");
  collection.replaceOne(query, replacement);
  ```

#### Delete

- **Delete One Document**

  ```java
  Document query = new Document("key", "value");
  collection.deleteOne(query);
  ```

- **Delete Multiple Documents**

  ```java
  Document query = new Document("key", "value");
  collection.deleteMany(query);
  ```

### Indexing

- **Create an Index**

  ```java
  collection.createIndex(new Document("key", 1)); // 1 for ascending, -1 for descending
  ```

- **View Indexes**

  ```java
  for (Document index : collection.listIndexes()) {
      System.out.println(index.toJson());
  }
  ```

- **Drop an Index**

  ```java
  collection.dropIndex("indexName");
  ```

### Aggregation

MongoDB’s aggregation framework allows for data processing and transformation.

- **Basic Aggregation Pipeline**

  ```java
  List<Document> pipeline = Arrays.asList(
      new Document("$match", new Document("key", "value")),
      new Document("$group", new Document("_id", "$groupField")
                                .append("total", new Document("$sum", "$numericField"))),
      new Document("$sort", new Document("total", -1))
  );
  for (Document doc : collection.aggregate(pipeline)) {
      System.out.println(doc.toJson());
  }
  ```

### Working with `ObjectId`

- **Generate a New `ObjectId`**

  ```java
  ObjectId id = new ObjectId();
  ```

- **Convert String to `ObjectId`**

  ```java
  ObjectId id = new ObjectId("string_id");
  ```

For additional methods and details, refer to the [MongoDB Java Driver Documentation](https://mongodb.github.io/mongo-java-driver/).
