package usecases.message_translation;

import entity.Group;
import entity.User;

import java.time.LocalDateTime;

public class MessageTranslationOutputData {
    private User sender;
    private String message;
    private LocalDateTime time;
    private Group group;
    private String language;

    public MessageTranslationOutputData(User sender, String message, LocalDateTime time, Group group, String language) {
        this.sender = sender;
        this.message = message;
        this.time = time;
        this.group = group;
        this.language = language;
    }

    public User getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Group getGroup() {
        return group;
    }

    public String getLanguage() {
        return language;
    }
}
