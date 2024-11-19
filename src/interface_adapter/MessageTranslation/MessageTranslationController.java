package interface_adapter.MessageTranslation;

import com.deepl.api.DeepLException;
import entity.Group;
import entity.User;
import usecases.message_translation.MessageTranslationInputBoundary;
import usecases.message_translation.MessageTranslationInputData;


public class MessageTranslationController {
    private final MessageTranslationInputBoundary messageTranslationInteractor;

    public MessageTranslationController(MessageTranslationInputBoundary messageTranslationInteractor) {
        this.messageTranslationInteractor = messageTranslationInteractor;
    }

    public void execute(String message, String group, User sender, String language) throws DeepLException, InterruptedException {
        final MessageTranslationInputData messageInputData = new MessageTranslationInputData(sender, message, group, language);
        messageTranslationInteractor.execute(messageInputData);
    }
}
