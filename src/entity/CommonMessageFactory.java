package entity;

/**
 * Factory for creating CommonMessage objects.
 */
public class CommonMessageFactory implements MessageFactory {

    @Override
    public Message create(User sender, String message, String language)  {
        return new CommonMessage(sender, message, language);
    }
}
