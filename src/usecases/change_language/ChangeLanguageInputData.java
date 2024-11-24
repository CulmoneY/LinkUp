package usecases.change_language;

import entity.User;

public class ChangeLanguageInputData {
    User user;
    String language;

    public ChangeLanguageInputData(User user, String language) {
        this.user = user;
        this.language = language;
    }

    public User getUser() {
        return user;
    }

    public String getLanguage() {
        return language;
    }
}
