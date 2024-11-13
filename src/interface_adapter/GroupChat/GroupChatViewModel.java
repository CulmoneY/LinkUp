package interface_adapter.GroupChat;

import interface_adapter.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class GroupChatViewModel extends ViewModel<GroupChatState> {
//    private List<MessageData> messages = new ArrayList<>();

    public GroupChatViewModel() {
        super("groupChatView");
    }

//    public List<MessageData> getMessages() {
//        return messages;
//    }
//
//    public void addMessage(MessageData message) {
//        messages.add(message);
//        firePropertyChanged();
//    }
}
