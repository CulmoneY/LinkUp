package usecases.message;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.message.MessageInputData;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class MessageInputDataTest {

    private CommonUser sender;
    private String groupName;
    private String message;
    private String language;
    private MessageInputData messageInputData;

    @BeforeEach
    void setUp() {
        // Create a CommonUser object
        sender = new CommonUser("John Doe", "password123", "English");

        // Initialize other fields for the test
        groupName = "Test Group";
        message = "This is a test message";
        language = "English";

        // Create the MessageInputData object
        messageInputData = new MessageInputData(groupName, sender, message, language);
    }

    @Test
    void testConstructorAndGetters() {
        // Test that the constructor correctly initializes the fields
        assertEquals(groupName, messageInputData.getGroupName(), "Group name should be initialized correctly.");
        assertEquals(sender, messageInputData.getSender(), "Sender should be initialized correctly.");
        assertNotNull(messageInputData.getTime(), "Time should be set when the object is created.");
        assertEquals(message, messageInputData.getMessage(), "Message should be initialized correctly.");
        assertEquals(language, messageInputData.getLanguage(), "Language should be initialized correctly.");
    }

    @Test
    void testSetters() {
        // Change the values using setters
        String newGroupName = "New Group";
        String newMessage = "New test message";
        String newLanguage = "Spanish";

        messageInputData.setGroupName(newGroupName);
        messageInputData.setMessage(newMessage);
        messageInputData.setLanguage(newLanguage);

        // Verify that the values were updated correctly
        assertEquals(newGroupName, messageInputData.getGroupName(), "Group name should be updated correctly.");
        assertEquals(newMessage, messageInputData.getMessage(), "Message should be updated correctly.");
        assertEquals(newLanguage, messageInputData.getLanguage(), "Language should be updated correctly.");
    }

    @Test
    void testTimeField() {
        // Ensure that the time field is set during object creation and is not null
        LocalDateTime timeBefore = messageInputData.getTime();
        assertNotNull(timeBefore, "Time should not be null after object creation.");

        // Since the time is set to the current time in the constructor, it is difficult to test it for equality.
        // We will instead check that it is not null and that it was initialized correctly.
        assertTrue(timeBefore.isBefore(LocalDateTime.now()) || timeBefore.isEqual(LocalDateTime.now()),
                "Time should be earlier than or equal to the current time.");
    }

    @Test
    void testSetSender() {
        // Create a new sender
        User newSender = new CommonUser("Jane Doe", "password456", "English");

        // Set the sender using the setter
        messageInputData.setSender(newSender);

        // Verify that the sender was updated correctly
        assertEquals(newSender, messageInputData.getSender(), "Sender should be updated correctly.");
    }

    @Test
    void testSetTime() {
        // Create a specific LocalDateTime object
        LocalDateTime newTime = LocalDateTime.of(2024, 12, 1, 10, 0);

        // Set the time using the setter
        messageInputData.setTime(newTime);

        // Verify that the time was updated correctly
        assertEquals(newTime, messageInputData.getTime(), "Time should be updated correctly.");
    }
}
