package usecases.message;

import entity.CommonMessageFactory;
import entity.Message;
import entity.MessageFactory;

import java.util.List;

public class MessageInteractor implements MessageInputBoundary {
    private final MessageDataAccessInterface messageDAO;

    public MessageInteractor(MessageDataAccessInterface messageDAO) {
        this.messageDAO = messageDAO;
    }

    public void execute(MessageInputData inputData) {
        MessageFactory messageFactory = new CommonMessageFactory();
        Message message = messageFactory.create(inputData.getSender(), inputData.getMessage(), inputData.getLanguage());
        messageDAO.updateGroupMessages(message, inputData.getGroupName());
    }

    public List<Message> getMessages(String groupName) {
        return messageDAO.getMessagesByGroup(groupName);
    }

}
