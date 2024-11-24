package usecases.message_translation;

import com.deepl.api.DeepLException;
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
        String storedMessage;
        if (!isValidLanguage(inputData.getLanguage())) {
            messageTranslationPresenter.presentTranslationError("Invalid language");
        }
        else if((storedMessage = messageTranslationDataAccess.getTranslatedMessage(inputData.getMessage(), inputData.getLanguage(), inputData.getGroup())) != null) {
            Message translatedMessage = messageFactory.create(inputData.getUser(), storedMessage, inputData.getLanguage());
            MessageTranslationOutputData outputData = new MessageTranslationOutputData(translatedMessage);
            messageTranslationPresenter.presentTranslatedMessage(outputData);
        }
        else {
            String translatedMessageString = messageTranslationDataAccess.translateMessage(inputData.getMessage(), inputData.getLanguage());
            Message translatedMessage = messageFactory.create(inputData.getUser(), translatedMessageString, inputData.getLanguage());
            MessageTranslationOutputData outputData = new MessageTranslationOutputData(translatedMessage);
            messageTranslationDataAccess.saveTranslation(inputData.getMessage(), inputData.getLanguage(), translatedMessageString, inputData.getGroup());
            messageTranslationPresenter.presentTranslatedMessage(outputData);
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

}
