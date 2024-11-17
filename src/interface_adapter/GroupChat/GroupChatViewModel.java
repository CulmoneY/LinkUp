package interface_adapter.GroupChat;

import daos.UserGroupDAO;
import entity.Message;
import interface_adapter.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class GroupChatViewModel extends ViewModel<GroupChatState> {
    private List<Message> messages = new ArrayList<>();
    private final UserGroupDAO userGroupDAO;

    public GroupChatViewModel(UserGroupDAO userGroupDAO) {
        super("groupChatView");
        this.userGroupDAO = userGroupDAO;
    }

    public List<Message> getMessages(String groupName) {
        return userGroupDAO.getMessagesByGroup(groupName);
    }

    public void addMessage(Message message) {
        messages.add(message);
        firePropertyChanged();
    }
}
