package app;

import daos.UserGroupDAO;
import entity.*;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Message.MessageController;
import interface_adapter.MessageTranslation.MessageTranslationController;
import interface_adapter.MessageTranslation.MessageTranslationPresenter;
import interface_adapter.MessageTranslation.MessageTranslationViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.GroupChat.GroupChatViewModel;
import usecases.message.MessageInputBoundary;
import usecases.message.MessageInteractor;
import usecases.message_translation.MessageTranslationInputBoundary;
import usecases.message_translation.MessageTranslationInteractor;
import views.*;
import interface_adapter.Login.*;
import usecases.login.*;
import usecases.account_creation.*;

import javax.swing.*;
import java.awt.*;

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
    private final UserGroupDAO userGroupDAO = new UserGroupDAO(groupFactory, messageFactory, calendarFactory, userFactory, eventFactory);

    // Instance variables for reusable components
    private final AccountCreationViewModel accountCreationViewModel = new AccountCreationViewModel();
    private final AccountCreationOutputBoundary accountCreationOutputBoundary =
            new AccountCreationPresenter(accountCreationViewModel, viewManagerModel);
    private final AccountCreationInputBoundary userAccountCreationInteractor =
            new AccountCreationInteractor(userGroupDAO, accountCreationOutputBoundary, userFactory);
    private final AccountCreationController accountCreationController =
            new AccountCreationController(userAccountCreationInteractor);

    private final LoginViewModel loginViewModel = new LoginViewModel();
    private final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel);
    private final LoginInputBoundary loginInteractor = new LoginInteractor(userGroupDAO, loginOutputBoundary, userFactory);
    private final LoginController loginController = new LoginController(loginInteractor);

    private final GroupChatViewModel groupChatViewModel = new GroupChatViewModel(userGroupDAO);
    private final MessageTranslationViewModel messageTranslationViewModel = new MessageTranslationViewModel();
    private final MessageInputBoundary messageInteractor = new MessageInteractor(userGroupDAO);
    private final MessageTranslationPresenter messageTranslationPresenter =
            new MessageTranslationPresenter(messageTranslationViewModel, viewManagerModel);
    private final MessageController messageController = new MessageController(messageInteractor);
    private final MessageTranslationInputBoundary messageTranslationInteractor =
            new MessageTranslationInteractor(userGroupDAO, messageTranslationPresenter, messageFactory);
    private final MessageTranslationController messageTranslationController =
            new MessageTranslationController(messageTranslationInteractor);

    // Instance variables for views
    private final AccountCreationView accountCreationView = new AccountCreationView(accountCreationViewModel, viewManager);
    private final LoginView loginView = new LoginView(loginViewModel, viewManager);
    private final GroupChatView groupChatView =
            new GroupChatView(groupChatViewModel, viewManager, messageTranslationViewModel);
    private final UserSettingsView userSettingsView = new UserSettingsView(viewManager);

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);

        // Add views to the ViewManager immediately
        viewManager.addView(loginView.getViewName(), loginView);
        viewManager.addView(accountCreationView.getViewName(), accountCreationView);
        viewManager.addView(groupChatView.getViewName(), groupChatView);
        viewManager.addView(userSettingsView.getViewName(), userSettingsView);
    }

    public AppBuilder addAccountCreationUseCase() {
        accountCreationView.setAccountCreationController(accountCreationController);
        return this;
    }

    public AppBuilder addLoginUseCase() {
        loginView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addMessageUseCase() {
        groupChatView.setMessageController(messageController);
        groupChatView.setMessageTranslationController(messageTranslationController);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Linkup");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1280, 720); // Fixed window size
        application.setLocationRelativeTo(null); // Center the window
        application.add(cardPanel);

        return application;
    }
}
