package interface_adapter.Message;

import entity.User;
import usecases.message.MessageInputBoundary;
import usecases.message.MessageInputData;

public class MessageController {
    private final MessageInputBoundary messageInteractor;

    public MessageController(MessageInputBoundary messageInteractor) {
        this.messageInteractor = messageInteractor;
    }

    public void execute(String message, String group, User sender, String language) {
        final MessageInputData messageInputData = new MessageInputData(group, sender, message, language);
        messageInteractor.execute(messageInputData);
    }


}
