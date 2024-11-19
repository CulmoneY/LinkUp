package usecases.message;

import entity.Message;

import java.util.List;

public interface MessageInputBoundary {
    void execute(MessageInputData inputData);

    List<Message> getMessages(String groupName);
}
