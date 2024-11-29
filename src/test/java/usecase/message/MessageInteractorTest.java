package usecase.message;

import entity.CommonMessageFactory;
import entity.Message;

import entity.MessageFactory;
import entity.CommonUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.message.MessageDataAccessInterface;
import usecases.message.MessageInputData;
import usecases.message.MessageInteractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageInteractorTest {

    private MessageDataAccessInterface messageDAO;
    private MessageInteractor messageInteractor;
    private MessageInputData messageInputData;
    private CommonUser sender;
    private String groupName;
    private String messageContent;
    private String language;

    @BeforeEach
    void setUp() {
        // Mock the MessageDataAccessInterface with a simple implementation
        messageDAO = new MessageDataAccessInterface() {
            private List<Message> messages = new ArrayList<>();

            @Override
            public void updateGroupMessages(Message message, String groupName) {
                // Simulate adding the message to the group (message storage)
                messages.add(message);
            }

            @Override
            public List<Message> getMessagesByGroup(String groupName) {
                // Return all messages for the group
                return new ArrayList<>(messages);
            }
        };

        // Create a MessageInteractor instance
        messageInteractor = new MessageInteractor(messageDAO);

        // Initialize test data
        sender = new CommonUser("John Doe", "password123", "English");
        groupName = "Test Group";
        messageContent = "This is a test message";
        language = "English";

        // Create MessageInputData
        messageInputData = new MessageInputData(groupName, sender, messageContent, language);
    }

    @Test
    void testExecuteMethod() {
        // Call the execute method to simulate sending a message
        messageInteractor.execute(messageInputData);

        // Retrieve the messages for the group
        List<Message> messages = messageInteractor.getMessages(groupName);

        // Ensure the message was added
        assertEquals(1, messages.size(), "There should be one message in the group.");
        Message message = messages.get(0);
        assertEquals(sender, message.getSender(), "Sender should be correct.");
        assertEquals(messageContent, message.getMessage(), "Message content should be correct.");
        assertEquals(language, message.getLanguage(), "Language should be correct.");
    }

    @Test
    void testGetMessages() {
        // First, execute a message send
        messageInteractor.execute(messageInputData);

        // Now call getMessages for the group
        List<Message> messages = messageInteractor.getMessages(groupName);

        // Ensure the retrieved messages are correct
        assertNotNull(messages, "Messages list should not be null.");
        assertEquals(1, messages.size(), "There should be one message in the list.");
        assertEquals(sender, messages.get(0).getSender(), "Sender should be correct.");
        assertEquals(messageContent, messages.get(0).getMessage(), "Message content should be correct.");
    }
}
