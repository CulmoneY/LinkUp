package entity;

import java.time.LocalDateTime;

public class CommonMessage implements Message {

    private User sender;
    private String message;
    private LocalDateTime time;
    private String language;

    public CommonMessage(User sender, String message, String language) {
        this.sender = sender;
        this.message = message;
        this.time = LocalDateTime.now();
        this.language = language;
    }

    @Override
    public User getSender() {
        return sender;
    }

    @Override
    public void setSender(User sender) {
        this.sender = sender;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTime(LocalDateTime time) {this.time = time;}
}

