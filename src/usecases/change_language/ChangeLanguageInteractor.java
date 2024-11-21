package usecases.change_language;

public class ChangeLanguageInteractor implements ChangeLanguageInputBoundary{
    private ChangeLanguageDataAccessInterface dataAccess;
    private ChangeLanguageOutputBoundary outputBoundary;

    public ChangeLanguageInteractor(ChangeLanguageDataAccessInterface dataAccess, ChangeLanguageOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void changeLanguage(ChangeLanguageInputData inputData) {
        String languageCode = extractLanguage(inputData);
        inputData.getUser().setLanguage(languageCode);
        dataAccess.changeUserLanguage(inputData.getUser().getName(), languageCode);
        ChangeLanguageOutputData outputData = new ChangeLanguageOutputData();
        outputData.setLanguage(languageCode);
        outputBoundary.setPassView(outputData);
    }

    private String extractLanguage(ChangeLanguageInputData inputData) {
        switch (inputData.getLanguage()) {
            case "English":
                return "EN-US";
            case "Arabic":
                return "AR";
            case "French":
                return "FR";
            case "Spanish":
                return "ES";
            case "Italian":
                return "IT";
            case "Japanese":
                return "JA";
            case "Korean":
                return "KO";
            case "Russian":
                return "RU";
            case "Chinese":
                return "ZH-HANS";
            case "Greek":
                return "EL";
            case "Portuguese":
                return "PT-BR";
            default:
                return null; // This case won't occur as per the problem statement
        }
    }

}
