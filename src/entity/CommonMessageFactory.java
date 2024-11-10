package entity;

/**
 * Factory for creating CommonMessage objects.
 */
public class CommonMessageFactory implements MessageFactory {

    @Override
    public Message create(User sender, String message)  {
        return new CommonMessage(sender, message) ;
    }
}
