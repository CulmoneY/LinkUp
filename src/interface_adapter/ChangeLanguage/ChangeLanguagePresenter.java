package interface_adapter.ChangeLanguage;
import interface_adapter.AddPersonalEvent.AddPersonalEventViewModel;
import interface_adapter.ViewManagerModel;
import usecases.change_language.ChangeLanguageOutputData;
import usecases.change_language.ChangeLanguageOutputBoundary;

public class ChangeLanguagePresenter implements ChangeLanguageOutputBoundary {
    private final ChangeLanguageViewModel changeLanguageViewModel;

    public ChangeLanguagePresenter(ChangeLanguageViewModel changeLanguageViewModel){
        this.changeLanguageViewModel = changeLanguageViewModel;
    }

    @Override
    public void setPassView(ChangeLanguageOutputData outputData) {
        ChangeLanguageState changeLanguageState = this.changeLanguageViewModel.getState();
        changeLanguageState.setLanguage(outputData.getLanguage());
        changeLanguageViewModel.firePropertyChanged("languageSuccess");
    }

    @Override
    public void setFailView(String error) {

    }
}
