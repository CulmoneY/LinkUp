package usecases.message;

import entity.Message;

public interface MessageOutputBoundary {
    void presentMessages(Message message);
}
