package interface_adapter.GroupChat;

import entity.Message;
import interface_adapter.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class GroupChatViewModel extends ViewModel<GroupChatState> {
    private List<Message> messages = new ArrayList<>();

    public GroupChatViewModel() {
        super("groupChatView");
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        messages.add(message);
        firePropertyChanged();
    }
}
