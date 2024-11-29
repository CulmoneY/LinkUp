package usecase.message_translation;

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
}
