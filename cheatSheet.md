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

# Clean Architecture Workflow Template

## Overview

Follow this guide for understanding the workflow involved in this project. Refer to this guide before asking any questions. For code examples, refer to any files related to Account Creation. These can be treated as complete and correct.

---

## Core Layers and Workflow

### 1. Entities
- **Purpose**: Entities represent core business objects and rules. They are simple data classes with essential properties and basic business logic.
- **Responsibilities**: Define properties and methods that encapsulate key business logic without dependencies on other layers.
  
#### Example
```java
public class CommonUser implements User{

    private String name;
    private String password;
    private List<User> friends;
    private Calendar userCalendar;
    private String language;
    private List<Group> groups;

    public CommonUser (String name, String password, String language) {
        this.name = name;
        this.password = password;
        this.language = language;
        this.friends = new ArrayList<User>();
        CalendarFactory calendarFactory = new CommonCalendarFactory();
        this.userCalendar = calendarFactory.create(name + "'s Calendar");
        this.groups = new ArrayList<Group>();
    }
```
- **Guideline**: Entities should focus solely on modeling the domain without any knowledge of use cases or persistence details.

---

### 2. Use Cases

#### Input Boundary
- **Purpose**: Defines the interface for available commands, representing the "use cases" of the application.
- **Guideline**: Only declare methods that are relevant to interacting with business logic.
  
#### Example
```java
public interface AccountCreationInputBoundary {
    void execute(AccountCreationInputData inputData);

    void switchToLoginView();

}
```

#### Input Data
- **Purpose**: Acts as a data transfer object (DTO) for passing input data from the controller to the interactor.
- **Guideline**: Structure these classes with only the necessary properties to avoid exposing excess data.

#### Example
```java
public class AccountCreationInputData {
    private String username;
    private String password;
    private String repeatedPassword;
    private String language;

    public AccountCreationInputData(String username, String password, String repeatedPassword, String language){
        this.username = username;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.language = language;
    }

    public String getUsername() {return this.username;}
```

#### Interactor
- **Purpose**: Implements the input boundary interface. This is where the core business logic lives.
- **Responsibilities**: Uses the entities and the data access interface to perform operations, modify the database, and communicate results to the input boundaries.

#### Example
```java
public class AccountCreationInteractor implements AccountCreationInputBoundary {
    final AccountCreationUserDataAccessInterface accountDataAccess;
    final AccountCreationOutputBoundary accountPresenter;
    final UserFactory userFactory;

    public AccountCreationInteractor(AccountCreationUserDataAccessInterface accountDataAccess,
                                     AccountCreationOutputBoundary accountPresenter,
                                     UserFactory userFactory) {
        this.accountDataAccess = accountDataAccess;
        this.accountPresenter = accountPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(AccountCreationInputData inputData){
        if (accountExists(inputData)) {
            accountPresenter.setFailView("account_exists");
        } else if (!passwordsEqual(inputData)){
            accountPresenter.setFailView("unmatched_passwords");
        } else if (missingFields(inputData)) {
            accountPresenter.setFailView("missing_fields");
        } else {
            User user = userFactory.create(inputData.getUsername(), inputData.getPassword(), inputData.getLanguage());
            accountDataAccess.saveUser(user);
            AccountCreationOutputData outputData = new AccountCreationOutputData(user.getName(), user.getLanguage(), true);
            accountPresenter.setPassView(outputData);
        }
    }
```
- **Guideline**: Interactors should not depend on concrete classes; instead, they rely on interfaces (boundaries).

#### Output Boundary
- **Purpose**: Defines the methods the presenter must implement to handle output data.
- **Guideline**: This interface declares how results from the interactor should be handled.

#### Example
```java
public interface AccountCreationOutputBoundary {

    void setPassView(AccountCreationOutputData user);

    void setFailView(String error);
}
```

#### Output Data
- **Purpose**: DTO to transfer output data from the interactor to the presenter.

#### Example
```java
public class AccountCreationOutputData {
    private String username;
    private String language;
    private boolean success;

    public AccountCreationOutputData(String username, String language, boolean success) {
        this.username = username;
        this.language = language;
        this.success = success;
    }

    public String getUsername() {return this.username;}
```

#### Data Access Interface
- **Purpose**: Declares methods for interacting with the database or persistence layer.

#### Example
```java
public interface AccountCreationUserDataAccessInterface {

    boolean accountExists(String username);

    void saveUser(User user);
}
```

---

### 3. Interface Adapters

#### Controller
- **Purpose**: Receives inputs from the view (UI) and interacts with the use case interactor to perform actions.

#### Example
```java
public class AccountCreationController {
    private final AccountCreationInputBoundary accountCreationInteractor;

    public AccountCreationController(AccountCreationInputBoundary accountCreationInteractor) {
        this.accountCreationInteractor = accountCreationInteractor;
    }

    public void execute(String username, String password, String repeatedPassword, String language) {
        final AccountCreationInputData accountCreationInputData = new AccountCreationInputData(
                username, password, repeatedPassword, language);
        accountCreationInteractor.execute(accountCreationInputData);
    }

    public void switchToLoginView() {
        accountCreationInteractor.switchToLoginView();
    }

}
```
- **Guideline**: Controllers should be lightweight, primarily responsible for passing data to interactors.

#### Presenter
- **Purpose**: Implements the output boundary, translating interactor responses into a form the view can handle by changing the state and updating the fireproperty with a unique property name.
- **Responsibilities**: Updates the view model with data to be displayed.

#### Example
```java
public class AccountCreationPresenter implements AccountCreationOutputBoundary {
    private final AccountCreationViewModel accountCreationViewModel;
    private final ViewManagerModel viewManagerModel;
    public AccountCreationPresenter(AccountCreationViewModel accountViewModel, ViewManagerModel viewManagerModel) {
        this.accountCreationViewModel = accountViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void setPassView(AccountCreationOutputData response) {
        AccountCreationState accountCreationState = accountCreationViewModel.getState();
        accountCreationState.setUsernameError(response.getUsername());
        accountCreationState.setPasswordError(null);
        accountCreationViewModel.firePropertyChanged("success");
    }
```
- **Guideline**: Presenters should avoid business logic; they merely format data for the view.

#### State
- **Purpose**: Stores data that can be accessed by the view.

#### Example
```java
public class AccountCreationState {
    private String username = "";
    private String usernameError;
    private String password = "";
    private String passwordError;
    private String errorCode;

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
```
- **Guideline**: Store data in a format accessible to views but avoid complex logic here.

#### View Model
- **Purpose**: Already defined, just pass in a unique view name.


#### Example
```java
public class UserViewModel extends Observable {
    private String registrationStatus;

    public void setRegistrationStatus(String status) {
        this.registrationStatus = status;
        setChanged();
        notifyObservers();
    }
}
```

---

### 4. Views

- **Purpose**: The UI components that the user interacts with, directly tied to the ViewModel.
- **Key Methods**:
  - **`actionPerformed()`**: Invoked on UI actions (e.g., button clicks) to trigger the controller.
  - **`propertyChange()`**: Listens to property changes from the presenter, updating the view accordingly with information from the state.

#### Example
```java
public class AccountCreationViewModel extends ViewModel<AccountCreationState> {

    public static final String TITLE_LABEL = "Create Account";
    public static final String USERNAME_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";
    public static final String REPEAT_PASSWORD_LABEL = "Repeat Password";
    public static final String LANGUAGE_LABEL = "Language";

    public static final String CREATE_ACCOUNT_BUTTON_LABEL = "Create Account";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public static final String LOGIN_BUTTON_LABEL = "Switch to Login";
    
    public AccountCreationViewModel() {
        super("accountCreationView"); // The view name
        setState(new AccountCreationState());
    }
```

---
