package interface_adapter.ChangeLanguage;
import entity.User;
import usecases.change_language.ChangeLanguageInputBoundary;
import usecases.change_language.ChangeLanguageInputData;

public class ChangeLanguageController {
    private ChangeLanguageInputBoundary inputBoundary;

    public ChangeLanguageController(ChangeLanguageInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void executeChangeLanguage(User user, String language) {
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, language);
        inputBoundary.changeLanguage(inputData);
    }
}
