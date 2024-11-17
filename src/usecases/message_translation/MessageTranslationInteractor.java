package usecases.message_translation;

import com.deepl.api.DeepLException;
import daos.UserGroupDAO;
import entity.Message;
import entity.MessageFactory;

public class MessageTranslationInteractor implements MessageTranslationInputBoundary {
    final MessageTranslationDataAccessInterface messageTranslationDataAccess;
    final MessageTranslationOutputBoundary messageTranslationPresenter;
    final MessageFactory messageFactory;

    public MessageTranslationInteractor(MessageTranslationDataAccessInterface messageTranslationDataAccess,
                                        MessageTranslationOutputBoundary messageTranslationPresenter,
                                        MessageFactory messageFactory) {
        this.messageTranslationDataAccess = messageTranslationDataAccess;
        this.messageTranslationPresenter = messageTranslationPresenter;
        this.messageFactory = messageFactory;
    }

    @Override
    public void execute(MessageTranslationInputData inputData) throws DeepLException, InterruptedException {
        // if message is already translated, return the message from the db
        // else, translate the message and store it in the db
        // if language is invalid, set fail view
        if (!isValidLanguage(inputData.getLanguage())) {
            messageTranslationPresenter.presentTranslationError("Invalid language");
        }
        else if(messageAlreadyTranslated(inputData.getMessage(), inputData.getLanguage())) {
            String storedMessage = messageTranslationDataAccess.getTranslatedMessage(inputData.getMessage(), inputData.getLanguage());
            Message translatedMessage = messageFactory.create(inputData.getUser(), storedMessage, inputData.getLanguage());
            messageTranslationPresenter.presentTranslatedMessage(translatedMessage);
        }
        else {
            String translatedMessageString = messageTranslationDataAccess.translateMessage(inputData.getMessage(), inputData.getLanguage());
            Message translatedMessage = messageFactory.create(inputData.getUser(), translatedMessageString, inputData.getLanguage());
            messageTranslationDataAccess.saveTranslation(inputData.getMessage(), inputData.getLanguage(), translatedMessageString);
            messageTranslationPresenter.presentTranslatedMessage(translatedMessage);
        }
    }

    private boolean isValidLanguage(String language) {
        return language.equals("EN-US") || // English (American)
                language.equals("AR") || // Arabic
                language.equals("FR") || // French
                language.equals("ES") || // Spanish
                language.equals("IT") || // Italian
                language.equals("JA") || // Japanese
                language.equals("KO") || // Korean
                language.equals("RU") || // Russian
                language.equals("ZH-HANS") || // Chinese (Simplified)
                language.equals("EL") || // Greek
                language.equals("PT-BR"); // Portuguese (Brazil)
    }

    private boolean messageAlreadyTranslated(String message, String language) {
        return messageTranslationDataAccess.messageAlreadyTranslated(message, language);
    }
}
