package entity;

import java.time.LocalDateTime;

/**
 * The Message interface represents a contract for a message with sender, receiver, content, and timestamp.
 */
public interface Message {

    /**
     * Gets the sender of the message.
     *
     * @return the sender
     */
    User getSender();

    /**
     * Sets the sender of the message.
     *
     * @param sender the sender of the message
     */
    void setSender(User sender);

    /**
     * Gets the receiver of the message.
     *
     * @return the receiver
     */
    Group getReceiver();

    /**
     * Sets the receiver of the message.
     *
     * @param receiver the receiver of the message
     */
    void setReceiver(Group receiver);

    /**
     * Gets the message content.
     *
     * @return the message content
     */
    String getMessage();

    /**
     * Sets the message content.
     *
     * @param message the message content
     */
    void setMessage(String message);

    /**
     * Gets the timestamp of the message.
     *
     * @return the timestamp
     */
    LocalDateTime getTime();
}
