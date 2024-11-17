package usecases.message;

import entity.Group;
import entity.Message;

import java.util.List;

public interface MessageDataAccessInterface {
    void updateGroupMessages(Message message, String groupName);

    List<Message> getMessagesByGroup(String groupName);
}
