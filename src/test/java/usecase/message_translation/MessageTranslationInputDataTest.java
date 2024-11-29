package usecase.message_translation;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.message_translation.MessageTranslationInputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTranslationInputDataTest {

    private User sender;
    private String message;
    private String group;
    private String language;
    private MessageTranslationInputData translationInputData;

    @BeforeEach
    void setUp() {
        // Initialize test data
        sender = new CommonUser("John Doe", "password123", "English");
        message = "Hello, this is a test message!";
        group = "Test Group";
        language = "Spanish";

        // Create the MessageTranslationInputData object
        translationInputData = new MessageTranslationInputData(sender, message, group, language);
    }

    @Test
    void testConstructorAndGetters() {
        // Test that the constructor correctly initializes the fields
        assertEquals(sender, translationInputData.getUser(), "Sender should be initialized correctly.");
        assertEquals(message, translationInputData.getMessage(), "Message should be initialized correctly.");
        assertEquals(group, translationInputData.getGroup(), "Group should be initialized correctly.");
        assertEquals(language, translationInputData.getLanguage(), "Language should be initialized correctly.");
    }

    @Test
    void testTimeField() {
        // Test that the time is set correctly in the constructor using the new getter
        LocalDateTime timeBefore = LocalDateTime.now();  // Capture the current time
        assertNotNull(translationInputData.getTime(), "Time should not be null after object creation.");

        // Check that the time is earlier than or equal to the current time
        assertTrue(translationInputData.getTime().isBefore(timeBefore) || translationInputData.getTime().equals(timeBefore),
                "Time should be earlier than or equal to the current time.");
    }
}
