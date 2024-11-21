package usecases.change_language;

public interface ChangeLanguageOutputBoundary {
    void setPassView(ChangeLanguageOutputData outputData);

    void setFailView(String error);
}
