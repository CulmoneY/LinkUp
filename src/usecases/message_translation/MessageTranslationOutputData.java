package usecases.message_translation;

import entity.Group;
import entity.User;
import entity.Message;

import java.time.LocalDateTime;

public class MessageTranslationOutputData {
    private Message message;

    public MessageTranslationOutputData(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
