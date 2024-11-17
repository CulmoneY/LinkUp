package usecases.message;

import entity.Group;
import entity.Message;

import java.util.List;

public interface MessageDataAccessInterface {
    List<Message> getMessagesByGroup(Group group);
}
