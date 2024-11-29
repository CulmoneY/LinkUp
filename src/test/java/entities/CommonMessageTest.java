package entities;

import entity.CommonMessage;
import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class CommonMessageTest {

    private User sender;
    private String messageText;
    private String language;
    private CommonMessage commonMessage;

    // Setting up the test data
    @BeforeEach
    public void setUp() {
        sender = new CommonUser("John Doe", "password123", "English");
        messageText = "Hello, world!";
        language = "English";

        // Create an instance of CommonMessage
        commonMessage = new CommonMessage(sender, messageText, language);
    }

    // Test the constructor and getters
    @Test
    public void testConstructorAndGetters() {
        assertNotNull(commonMessage.getTime(), "Time should not be null.");
        assertEquals(sender, commonMessage.getSender(), "Sender should be the same as the one passed in the constructor.");
        assertEquals(messageText, commonMessage.getMessage(), "Message should be the same as the one passed in the constructor.");
        assertEquals(language, commonMessage.getLanguage(), "Language should be the same as the one passed in the constructor.");
    }

    // Test setters
    @Test
    public void testSetters() {
        User newSender = new CommonUser("Jane Doe", "password456", "French");
        String newMessage = "Goodbye, world!";
        String newLanguage = "French";
        LocalDateTime newTime = LocalDateTime.now().plusDays(1);

        // Update the message
        commonMessage.setSender(newSender);
        commonMessage.setMessage(newMessage);
        commonMessage.setLanguage(newLanguage);
        commonMessage.setTime(newTime);

        // Assert that the values were updated
        assertEquals(newSender, commonMessage.getSender(), "Sender should be updated.");
        assertEquals(newMessage, commonMessage.getMessage(), "Message should be updated.");
        assertEquals(newLanguage, commonMessage.getLanguage(), "Language should be updated.");
        assertEquals(newTime, commonMessage.getTime(), "Time should be updated.");
    }


    // Test for invalid message (empty message)
    @Test
    public void testEmptyMessage() {
        String emptyMessage = "";

        // Set an empty message
        commonMessage.setMessage(emptyMessage);

        // Assert that the message is empty
        assertEquals(emptyMessage, commonMessage.getMessage(), "Message should be empty.");
    }

}
