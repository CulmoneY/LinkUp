package usecase.message_translation;

import entity.CommonMessage;
import entity.Message;
import entity.User;
import entity.CommonUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.message_translation.MessageTranslationOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTranslationOutputDataTest {

    private User sender;
    private String messageContent;
    private String language;
    private CommonMessage message;
    private MessageTranslationOutputData translationOutputData;

    @BeforeEach
    void setUp() {
        // Initialize test data
        sender = new CommonUser("John Doe", "password123", "English");
        messageContent = "This is a translated message!";
        language = "Spanish";

        // Create the message object using the CommonMessage constructor
        message = new CommonMessage(sender, messageContent, language);

        // Create the MessageTranslationOutputData object
        translationOutputData = new MessageTranslationOutputData(message);
    }

    @Test
    void testConstructorAndGetter() {
        // Test that the constructor correctly initializes the message field
        assertEquals(message, translationOutputData.getMessage(), "The message should be correctly initialized.");
    }

    @Test
    void testGetMessage() {
        // Test the getter to ensure it returns the correct message
        Message returnedMessage = translationOutputData.getMessage();
        assertNotNull(returnedMessage, "The returned message should not be null.");
        assertEquals(message, returnedMessage, "The returned message should be the same as the one passed to the constructor.");
    }
}
