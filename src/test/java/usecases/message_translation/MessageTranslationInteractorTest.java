package usecases.message_translation;

import entity.CommonMessageFactory;
import entity.CommonUser;
import entity.Message;
import entity.MessageFactory;
import entity.CommonMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.deepl.api.DeepLException;
import usecases.message_translation.*;

import java.util.HashMap;
import java.util.Map;

public class MessageTranslationInteractorTest {

    private MessageTranslationDataAccessInterface messageTranslationDataAccess;
    private MessageTranslationOutputBoundary messageTranslationPresenter;
    private MessageFactory messageFactory;
    private MessageTranslationInteractor messageTranslationInteractor;

    private MessageTranslationInputData inputData;

    // Stub class for MessageTranslationDataAccessInterface
    class MessageTranslationDataAccessStub implements MessageTranslationDataAccessInterface {
        private final Map<String, String> translations = new HashMap<>();

        @Override
        public String getTranslatedMessage(String message, String language, String group) {
            return translations.get(language + ":" + message);
        }

        @Override
        public String translateMessage(String message, String language) {
            // Simple translation (for test purposes)
            return message + " (translated to " + language + ")";
        }

        @Override
        public void saveTranslation(String message, String language, String translatedMessage, String group) {
            translations.put(language + ":" + message, translatedMessage);
        }
    }

    // Stub class for MessageTranslationOutputBoundary
    class MessageTranslationOutputBoundaryStub implements MessageTranslationOutputBoundary {
        private String errorMessage;
        private MessageTranslationOutputData translatedMessageData;

        @Override
        public void presentTranslationError(String error) {
            this.errorMessage = error;
        }

        @Override
        public void presentTranslatedMessage(MessageTranslationOutputData outputData) {
            this.translatedMessageData = outputData;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public MessageTranslationOutputData getTranslatedMessageData() {
            return translatedMessageData;
        }
    }

    @BeforeEach
    void setUp() {
        // Initialize the stub implementations
        messageTranslationDataAccess = new MessageTranslationDataAccessStub();
        messageTranslationPresenter = new MessageTranslationOutputBoundaryStub();

        // Use the actual CommonMessageFactory for creating messages
        messageFactory = new CommonMessageFactory();

        // Initialize the interactor with the actual factory and stubs
        messageTranslationInteractor = new MessageTranslationInteractor(
                messageTranslationDataAccess,
                messageTranslationPresenter,
                messageFactory
        );

        // Create a sample input for the test
        inputData = new MessageTranslationInputData(
                new CommonUser("John Doe", "password123", "English"),
                "Hello, how are you?",
                "Test Group",
                "ES" // Spanish
        );
    }

    @Test
    void testExecuteWithValidTranslation() throws DeepLException, InterruptedException {
        // Simulate that no translation exists yet
        messageTranslationDataAccess.saveTranslation(inputData.getMessage(), inputData.getLanguage(), "Hola, ¿cómo estás?", inputData.getGroup());

        // Execute the translation
        messageTranslationInteractor.execute(inputData);

        // Verify that the translated message is presented correctly
        MessageTranslationOutputBoundaryStub presenter = (MessageTranslationOutputBoundaryStub) messageTranslationPresenter;
        assertNotNull(presenter.getTranslatedMessageData(), "Translated message should be present.");
        assertEquals("Hola, ¿cómo estás?", presenter.getTranslatedMessageData().getMessage().getMessage(), "Translated message should match.");
    }

    @Test
    void testExecuteWithInvalidLanguage() throws DeepLException, InterruptedException {
        // Modify inputData to use an invalid language
        inputData = new MessageTranslationInputData(
                new CommonUser("John Doe", "password123", "English"),
                "Hello, how are you?",
                "Test Group",
                "INVALID" // Invalid language
        );

        // Execute the method
        messageTranslationInteractor.execute(inputData);

        // Verify that the error message was presented
        MessageTranslationOutputBoundaryStub presenter = (MessageTranslationOutputBoundaryStub) messageTranslationPresenter;
        assertNotNull(presenter.getErrorMessage(), "Error message should be set.");
        assertEquals("Invalid language", presenter.getErrorMessage(), "Error message should indicate invalid language.");
    }

    @Test
    void testExecuteWithAlreadyTranslatedMessage() throws DeepLException, InterruptedException {
        // Simulate an already translated message
        messageTranslationDataAccess.saveTranslation(inputData.getMessage(), inputData.getLanguage(), "Hola, ¿cómo estás?", inputData.getGroup());

        // Execute the method
        messageTranslationInteractor.execute(inputData);

        // Verify that the translated message was retrieved from the database, not re-translated
        MessageTranslationOutputBoundaryStub presenter = (MessageTranslationOutputBoundaryStub) messageTranslationPresenter;
        assertNotNull(presenter.getTranslatedMessageData(), "Translated message should be present.");
        assertEquals("Hola, ¿cómo estás?", presenter.getTranslatedMessageData().getMessage().getMessage(), "Translated message should match.");
    }

    @Test
    void testExecuteWithNewTranslation() throws DeepLException, InterruptedException {
        // Simulate that no translation exists yet by returning null from getTranslatedMessage
        MessageTranslationDataAccessStub dataAccessStub = (MessageTranslationDataAccessStub) messageTranslationDataAccess;
        dataAccessStub.translations.clear(); // Ensure no pre-saved translations

        // Execute the translation
        messageTranslationInteractor.execute(inputData);

        // Verify that the translation was performed, saved, and presented
        MessageTranslationOutputBoundaryStub presenter = (MessageTranslationOutputBoundaryStub) messageTranslationPresenter;

        // Ensure the translated message data is present
        assertNotNull(presenter.getTranslatedMessageData(), "Translated message should be present.");

        // Verify the translated message content
        String expectedTranslatedMessage = inputData.getMessage() + " (translated to " + inputData.getLanguage() + ")";
        assertEquals(expectedTranslatedMessage, presenter.getTranslatedMessageData().getMessage().getMessage(), "Translated message should match the expected translation.");

        // Verify that the translation was saved to the data access layer
        String savedTranslation = dataAccessStub.getTranslatedMessage(inputData.getMessage(), inputData.getLanguage(), inputData.getGroup());
        assertEquals(expectedTranslatedMessage, savedTranslation, "Saved translation should match the translated message.");
    }

    @Test
    void testIsValidLanguageWithAllValidLanguages() throws DeepLException, InterruptedException {
        // Test all valid languages defined in isValidLanguage
        String[] validLanguages = {
                "EN-US", // English (American)
                "AR",    // Arabic
                "FR",    // French
                "ES",    // Spanish
                "IT",    // Italian
                "JA",    // Japanese
                "KO",    // Korean
                "RU",    // Russian
                "ZH-HANS", // Chinese (Simplified)
                "EL",    // Greek
                "PT-BR"  // Portuguese (Brazil)
        };

        for (String language : validLanguages) {
            inputData = new MessageTranslationInputData(
                    new CommonUser("John Doe", "password123", "English"),
                    "Hello, how are you?",
                    "Test Group",
                    language
            );

            // Execute the method
            messageTranslationInteractor.execute(inputData);

            // Verify no error message is present
            MessageTranslationOutputBoundaryStub presenter = (MessageTranslationOutputBoundaryStub) messageTranslationPresenter;
            assertNull(presenter.getErrorMessage(), "Valid language should not produce an error: " + language);
            assertNotNull(presenter.getTranslatedMessageData(), "Translated message should be present for language: " + language);
        }
    }

    @Test
    void testIsValidLanguageWithInvalidLanguages() throws DeepLException, InterruptedException {
        // Test invalid languages
        String[] invalidLanguages = {
                "DE",      // German (not in the list)
                "ZH-HANT", // Chinese (Traditional) - not in the list
                "EN-UK",   // English (British) - not in the list
                "XX",      // Completely invalid
                ""         // Empty string
        };

        for (String language : invalidLanguages) {
            inputData = new MessageTranslationInputData(
                    new CommonUser("John Doe", "password123", "English"),
                    "Hello, how are you?",
                    "Test Group",
                    language
            );

            // Execute the method
            messageTranslationInteractor.execute(inputData);

            // Verify that an error message was presented
            MessageTranslationOutputBoundaryStub presenter = (MessageTranslationOutputBoundaryStub) messageTranslationPresenter;
            assertNotNull(presenter.getErrorMessage(), "Error message should be set for invalid language: " + language);
            assertEquals("Invalid language", presenter.getErrorMessage(), "Error message should indicate invalid language for: " + language);
            assertNull(presenter.getTranslatedMessageData(), "No translated message should be present for invalid language: " + language);
        }
    }

}
