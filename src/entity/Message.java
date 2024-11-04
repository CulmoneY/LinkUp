package entity;

/**
 * This is a marker interface for all messages.
 */
public interface Message {
    String getMessage(String translationLanguage);

    void editMessage(String message);
}
