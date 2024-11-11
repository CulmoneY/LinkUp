package app;

import daos.GroupDAO;
import daos.UserDAO;
import entity.*;
import interface_adapter.ViewManagerModel;
import views.AccountCreationView;
import views.ViewManager;
import usecases.account_creation.*;

import javax.swing.*;
import java.awt.*;

import java.awt.CardLayout;

import interface_adapter.AccountCreation.*;


public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new CommonUserFactory();
    private final CalendarFactory calendarFactory = new CommonCalendarFactory();
    private final EventFactory eventFactory = new CommonEventFactory();
    private final MessageFactory messageFactory = new CommonMessageFactory();
    private final GroupFactory groupFactory = new CommonGroupFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
    private final UserDAO userDAO = new UserDAO(userFactory, eventFactory, calendarFactory);
    private final GroupDAO groupDAO = new GroupDAO(groupFactory, messageFactory, calendarFactory, userFactory, eventFactory);
    private AccountCreationView accountCreationView;
    private AccountCreationViewModel accountCreationViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addAccountCreationView() {
        accountCreationViewModel = new AccountCreationViewModel();
        accountCreationView = new AccountCreationView(accountCreationViewModel);
        cardPanel.add(accountCreationView, accountCreationView.getViewName());
        return this;

    }

    public AppBuilder addAccountCreationUseCase() {
        final AccountCreationOutputBoundary accountCreationOutputBoundary = new AccountCreationPresenter(accountCreationViewModel,
                viewManagerModel); // TODO: add login view model
        final AccountCreationInputBoundary userAccountCreationInteractor = new AccountCreationInteractor(
                userDAO, accountCreationOutputBoundary, userFactory);

        final AccountCreationController controller = new AccountCreationController(userAccountCreationInteractor);
        accountCreationView.setAccountCreationController(controller);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Linkup");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1280, 720); // Fixed window size
        application.setLocationRelativeTo(null); // Center the window
        application.add(cardPanel);
        viewManagerModel.setState(AccountCreationView.getDefaultLocale().getDisplayName());
        viewManagerModel.firePropertyChanged();
        return application;
    }
}




