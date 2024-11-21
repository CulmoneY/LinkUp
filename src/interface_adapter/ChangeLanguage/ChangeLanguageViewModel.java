package interface_adapter.ChangeLanguage;
import interface_adapter.ViewModel;

public class ChangeLanguageViewModel extends ViewModel<ChangeLanguageState> {

    public ChangeLanguageViewModel() {
        super("changeLanguageView");
        setState(new ChangeLanguageState());
    }
}
