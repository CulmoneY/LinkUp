package entity;

import java.time.LocalDateTime;

public class CommonMessage implements Message {

    private User sender;
    private Group receiver;
    private String message;
    private final LocalDateTime time;

    public CommonMessage(User sender, Group receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
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
    public Group getReceiver() {
        return receiver;
    }

    @Override
    public void setReceiver(Group receiver) {
        this.receiver = receiver;
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
}
