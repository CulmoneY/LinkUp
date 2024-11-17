package entity;

/**
 * Factory for creating messages.
 */
public interface MessageFactory {
    /**
     * Creates a new Message.
     * @param name the name of the new message
     * @param  the password of the new message
     * @param the language of the new message
     * @return the new user
     */

    Message create(User sender, String message, String language);

}