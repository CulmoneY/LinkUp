package usecases.message;

import entity.Group;
import entity.User;

import java.time.LocalDateTime;


public class MessageInputData {
    private String groupName;
    private User sender;
    private LocalDateTime time;
    private String message;
    private String language;

    public MessageInputData(String groupName, User sender, String message, String language) {
        this.groupName = groupName;
        this.sender = sender;
        this.message = message;
        this.time = LocalDateTime.now();
        this.language = language;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
