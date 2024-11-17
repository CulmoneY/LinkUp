package usecases.message_translation;

import entity.User;
import entity.Group;

import java.time.LocalDateTime;

public class MessageTranslationInputData {
    private User sender;
    private String message;
    private LocalDateTime time;
    private Group group;
    private String language;

    public MessageTranslationInputData(User sender, String message, Group group, String language) {
        this.sender = sender;
        this.message = message;
        this.time = LocalDateTime.now();
        this.group = group;
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return sender;
    }
}
