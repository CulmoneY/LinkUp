package usecases.message;

import entity.CommonMessageFactory;
import entity.Message;
import entity.MessageFactory;
import usecases.create_group.CreateGroupDataAccessInterface;

public class MessageInteractor {
    private final CreateGroupDataAccessInterface messageDAO;
    private final MessageOutputBoundary presenter;

    public MessageInteractor(CreateGroupDataAccessInterface messageDAO, MessageOutputBoundary presenter) {
        this.messageDAO = messageDAO;
        this.presenter = presenter;
    }

    public void execute(MessageInputData inputData) {
        MessageFactory messageFactory = new CommonMessageFactory();
        Message message = messageFactory.create(inputData.getSender(), inputData.getMessage(), inputData.getLanguage());
        inputData.getGroup().addMessage(message);
        messageDAO.saveGroup(inputData.getGroup());
        presenter.presentMessages(message);
    }

}
