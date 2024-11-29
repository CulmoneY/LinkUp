package entities;

import entity.CommonMessageFactory;
import entity.CommonUser;
import entity.Message;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonMessageFactoryTest {

    private CommonMessageFactory messageFactory;
    private User sender;
    private String messageText;
    private String language;

    @BeforeEach
    void setUp() {
        // Creating a mock user (you could use a mock or a simple implementation of User)
        sender = new CommonUser("John", "password123", "English");

        // The message and language
        messageText = "Hello, this is a test message!";
        language = "English";

        // Initialize the message factory
        messageFactory = new CommonMessageFactory();
    }

    @Test
    void testCreateMessage() {
        // Creating a message using the factory
        Message message = messageFactory.create(sender, messageText, language);

        // Verifying that the message is created properly
        assertNotNull(message, "The message should not be null.");
        assertEquals(sender, message.getSender(), "The sender of the message should match the expected sender.");
        assertEquals(messageText, message.getMessage(), "The message content should match the expected content.");
        assertEquals(language, message.getLanguage(), "The message language should match the expected language.");
    }


    @Test
    void testCreateMessageWithNullMessage() {
        // Creating a message with a null message (if allowed)
        Message message = messageFactory.create(sender, null, language);

        // Verifying that the message is created with null content
        assertNotNull(message, "The message should not be null.");
        assertNull(message.getMessage(), "The message content should be null.");
    }

    @Test
    void testCreateMessageWithNullLanguage() {
        // Creating a message with a null language (if allowed)
        Message message = messageFactory.create(sender, messageText, null);

        // Verifying that the message is created with null language
        assertNotNull(message, "The message should not be null.");
        assertNull(message.getLanguage(), "The message language should be null.");
    }
}
