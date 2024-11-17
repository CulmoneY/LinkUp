package interface_adapter.MessageTranslation;
import entity.Message;

public class MessageTranslationState {
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
