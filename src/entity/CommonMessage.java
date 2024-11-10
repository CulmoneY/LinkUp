package entity;

import java.time.LocalDateTime;

public class CommonMessage implements Message {

    private User sender;
    private String message;
    private LocalDateTime time;

    public CommonMessage(User sender, String message) {
        this.sender = sender;
        this.message = message;
        this.time = LocalDateTime.now();
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

    public void setTime(LocalDateTime time) {this.time = time;}
}

