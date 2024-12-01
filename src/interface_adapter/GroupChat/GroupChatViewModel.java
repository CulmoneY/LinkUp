package interface_adapter.GroupChat;

import daos.MongoDAO;
import entity.Group;
import entity.Message;
import entity.User;
import interface_adapter.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class GroupChatViewModel extends ViewModel<GroupChatState> {
    private List<Message> messages = new ArrayList<>();
    private final MongoDAO mongoDAO;

    public GroupChatViewModel(MongoDAO mongoDAO) {
        super("groupChatView");
        setState(new GroupChatState());
        this.mongoDAO = mongoDAO;
    }

    public List<Message> getMessages(String groupName) {
        return mongoDAO.getMessagesByGroup(groupName);
    }

    public void addMessage(Message message) {
        messages.add(message);
        firePropertyChanged();
    }

    public List<Group> getUserGroups(String username) {
        return mongoDAO.getUser(username).getGroups();
    }

    public List<User> getUserFriends(String username) {
        return mongoDAO.getUser(username).getFriends();
    }
}
