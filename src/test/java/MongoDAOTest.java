import entity.CommonUserFactory;
import entity.CommonEventFactory;
import entity.CommonCalendarFactory;
import entity.CommonGroupFactory;
import entity.CommonMessageFactory;
import entity.User;

import entity.*;
import daos.MongoDAO;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MongoDAOTest {
    private static final boolean SKIP_TESTS = true; // Set to true to skip tests
    private MongoDAO mongoDAO;
    private User user;

    @BeforeEach
    void checkTestEnabled() {
        // Skip tests if SKIP_TESTS is true
        Assumptions.assumeTrue(!SKIP_TESTS, "Tests are disabled.");
    }

    @BeforeEach
    public void setUp() {
        // Initialize the factories needed for UserDAO
        CommonUserFactory userFactory = new CommonUserFactory();
        CommonEventFactory eventFactory = new CommonEventFactory();
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonGroupFactory groupFactory = new CommonGroupFactory();
        CommonMessageFactory messageFactory = new CommonMessageFactory();

        // Initialize UserDAO with the required dependencies
        mongoDAO = new MongoDAO(groupFactory, messageFactory, calendarFactory, userFactory, eventFactory);

        // Create a user instance for testing
        user = userFactory.create("Samy Asnoun", "ilovetren123", "Arabic");

        User friend1 = userFactory.create("Yianni Culmone", "password123", "English");
        User friend2 = userFactory.create("Neyl Nasr", "mypassword", "French");
        user.addFriend(friend1);
        user.addFriend(friend2);

        // Create a calendar for the user and add some events
        Calendar calendar = calendarFactory.create("Samy Asnoun's Calendar");
        Event event1 = eventFactory.create("Meeting with team", LocalDateTime.of(2023, 11, 10, 10, 0), LocalDateTime.of(2023, 11, 10, 11, 0), false);
        Event event2 = eventFactory.create("Lunch with friends", LocalDateTime.of(2023, 11, 10, 12, 0), LocalDateTime.of(2023, 11, 10, 13, 0), false);
        calendar.addEvent(event1);
        calendar.addEvent(event2);
        user.setUserCalendar(calendar);

    }

    @Nested
    class AccountSetupTests {
        @Test
        public void testSaveUser() {
            mongoDAO.saveUser(user);
            assertTrue(mongoDAO.accountExists(user.getName()));
        }

        @Test
        public void testDeleteUser() {
            // Save the user before attempting to delete
            mongoDAO.deleteUser(user.getName());
            assertFalse(mongoDAO.accountExists(user.getName()));
        }

        @Test //This test is just to add a group to the user, should be deleted later
        public void testAddGroup(){
            Group group1 = new CommonGroupFactory().create("Group 1", new ArrayList<>());
            Group group2 = new CommonGroupFactory().create("Test 2", new ArrayList<>());
            User user = mongoDAO.getUser("yianni7777");
            mongoDAO.saveGroup(group1);
            mongoDAO.saveGroup(group2);
        }
    }

    @Nested
    class AccountFieldsTests {
        @BeforeEach
        public void setUp() {
            mongoDAO.saveUser(user);
        }

        @AfterEach
        public void tearDown() {
            mongoDAO.deleteUser(user.getName());
        }

        @Test
        public void testAccountExists() {
            assertTrue(mongoDAO.accountExists(user.getName()));
        }

        @Test
        public void testAccountFieldsCorrect() {
            User userFromDB = mongoDAO.getUser(user.getName());
            assertEquals(user.getName(), userFromDB.getName());
            assertEquals(user.getPassword(), userFromDB.getPassword());
            assertEquals(user.getLanguage(), userFromDB.getLanguage());
        }

        @Test
        public void testUserFriendsSerialization() {
            User userFromDB = mongoDAO.getUser(user.getName());
            assertNotNull(userFromDB.getFriends());
            assertEquals(2, userFromDB.getFriends().size());
            assertEquals("Yianni Culmone", userFromDB.getFriends().get(0).getName());
            assertEquals("Neyl Nasr", userFromDB.getFriends().get(1).getName());
        }

        @Test
        public void testUserCalendarSerialization() {
            User userFromDB = mongoDAO.getUser(user.getName());
            assertNotNull(userFromDB.getUserCalendar());
            assertEquals(user.getUserCalendar().getName(), userFromDB.getUserCalendar().getName());
            assertEquals(2, userFromDB.getUserCalendar().getEvents().size());

            Event retrievedEvent1 = userFromDB.getUserCalendar().getEvents().get(0);
            Event retrievedEvent2 = userFromDB.getUserCalendar().getEvents().get(1);

            assertEquals("Meeting with team", retrievedEvent1.getEventName());
            assertEquals(LocalDateTime.of(2023, 11, 10, 10, 0), retrievedEvent1.getStartTime());
            assertEquals(LocalDateTime.of(2023, 11, 10, 11, 0), retrievedEvent1.getEndTime());

            assertEquals("Lunch with friends", retrievedEvent2.getEventName());
            assertEquals(LocalDateTime.of(2023, 11, 10, 12, 0), retrievedEvent2.getStartTime());
            assertEquals(LocalDateTime.of(2023, 11, 10, 13, 0), retrievedEvent2.getEndTime());
        }
    }

    @AfterEach
    void tearDown() {
        // Remove user data if it exists in the database
        if (user != null && mongoDAO.accountExists(user.getName())) {
            mongoDAO.deleteUser(user.getName());
        }

        // Clean up groups created in testAddGroup
        try {
            Group group1 = new CommonGroupFactory().create("Group 1", new ArrayList<>());
            Group group2 = new CommonGroupFactory().create("Test 2", new ArrayList<>());
            mongoDAO.deleteGroup(group1.getName());
            mongoDAO.deleteGroup(group2.getName());
        } catch (Exception e) {
            // Log or handle any unexpected issues during cleanup
            System.err.println("Error cleaning up groups: " + e.getMessage());
        }

        // Nullify references to assist garbage collection
        mongoDAO = null;
        user = null;
    }
}
