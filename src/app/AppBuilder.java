package app;

import daos.MongoDAO;
import entity.*;
import interface_adapter.AddFriend.AddFriendController;
import interface_adapter.AddFriend.AddFriendPresenter;
import interface_adapter.AddFriend.AddFriendViewModel;
import interface_adapter.AddGroupMember.AddGroupMemberController;
import interface_adapter.AddGroupMember.AddGroupMemberPresenter;
import interface_adapter.AddGroupMember.AddGroupMemberViewModel;
import interface_adapter.AddPersonalEvent.AddPersonalEventController;
import interface_adapter.AddPersonalEvent.AddPersonalEventPresenter;
import interface_adapter.AddPersonalEvent.AddPersonalEventViewModel;
import interface_adapter.AddRecommendedEvent.AddRecommendedEventController;
import interface_adapter.AddRecommendedEvent.AddRecommendedEventPresenter;
import interface_adapter.AddRecommendedEvent.AddRecommendedEventViewModel;
import interface_adapter.ChangeLanguage.ChangeLanguageController;
import interface_adapter.ChangeLanguage.ChangeLanguagePresenter;
import interface_adapter.ChangeLanguage.ChangeLanguageViewModel;
import interface_adapter.DeletePersonalEvent.DeletePersonalEventController;
import interface_adapter.DeletePersonalEvent.DeletePersonalEventViewModel;
import interface_adapter.DeletePersonalEvent.DeletePersonalEventPresenter;
import interface_adapter.CreateGroup.CreateGroupController;
import interface_adapter.CreateGroup.CreateGroupPresenter;
import interface_adapter.CreateGroup.CreateGroupViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Message.MessageController;
import interface_adapter.MessageTranslation.MessageTranslationController;
import interface_adapter.MessageTranslation.MessageTranslationPresenter;
import interface_adapter.MessageTranslation.MessageTranslationViewModel;
import interface_adapter.RemoveFriend.RemoveFriendController;
import interface_adapter.RemoveFriend.RemoveFriendPresenter;
import interface_adapter.RemoveFriend.RemoveFriendViewModel;
import interface_adapter.RemoveGroupMember.RemoveGroupMemberController;
import interface_adapter.RemoveGroupMember.RemoveGroupMemberPresenter;
import interface_adapter.RemoveGroupMember.RemoveGroupMemberState;
import interface_adapter.RemoveGroupMember.RemoveGroupMemberViewModel;
import interface_adapter.TimeslotSelection.TimeslotSelectionController;
import interface_adapter.TimeslotSelection.TimeslotSelectionPreseter;
import interface_adapter.TimeslotSelection.TimeslotSelectionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.GroupChat.GroupChatViewModel;
import usecases.add_friend.AddFriendInputBoundary;
import usecases.add_friend.AddFriendInteractor;
import usecases.add_friend.AddFriendOutputBoundary;
import usecases.add_group_member.AddGroupMemberInputBoundary;
import usecases.add_group_member.AddGroupMemberInteractor;
import usecases.add_group_member.AddGroupMemberOutputBoundary;
import usecases.add_personal_event.AddPersonalEventInputBoundary;
import usecases.add_personal_event.AddPersonalEventInteractor;
import usecases.add_personal_event.AddPersonalEventOutputBoundary;
import usecases.add_recommended_event.AddRecommendedEventInputBoundary;
import usecases.add_recommended_event.AddRecommendedEventInteractor;
import usecases.add_recommended_event.AddRecommendedEventOutputBoundary;
import usecases.change_language.ChangeLanguageInputBoundary;
import usecases.change_language.ChangeLanguageInteractor;
import usecases.change_language.ChangeLanguageOutputBoundary;
import usecases.delete_personal_event.DeletePersonalEventInputBoundary;
import usecases.delete_personal_event.DeletePersonalEventOutputBoundary;
import usecases.delete_personal_event.DeletePersonalEventInteractor;
import usecases.create_group.CreateGroupInputBoundary;
import usecases.create_group.CreateGroupInteractor;
import usecases.create_group.CreateGroupOutputBoundary;
import usecases.message.MessageInputBoundary;
import usecases.message.MessageInteractor;
import usecases.message_translation.MessageTranslationInputBoundary;
import usecases.message_translation.MessageTranslationInteractor;
import usecases.remove_group_member.RemoveGroupMemberInputBoundary;
import usecases.remove_group_member.RemoveGroupMemberInteractor;
import usecases.remove_group_member.RemoveGroupMemberOutputBoundary;
import usecases.timeslot_selection.TimeslotSelectionInputBoundary;
import usecases.timeslot_selection.TimeslotSelectionInteractor;
import usecases.timeslot_selection.TimeslotSelectionOutputBoundary;
import usecases.remove_friend.RemoveFriendInputBoundary;
import usecases.remove_friend.RemoveFriendInteractor;
import usecases.remove_friend.RemoveFriendOutputBoundary;
import usecases.add_group_event.AddGroupEventInputBoundary;
import usecases.add_group_event.AddGroupEventInteractor;
import usecases.add_group_event.AddGroupEventOutputBoundary;
import interface_adapter.AddGroupEvent.AddGroupEventController;
import interface_adapter.AddGroupEvent.AddGroupEventPresenter;
import interface_adapter.AddGroupEvent.AddGroupEventViewModel;
import usecases.delete_group_event.DeleteGroupEventInputBoundary;
import usecases.delete_group_event.DeleteGroupEventInteractor;
import usecases.delete_group_event.DeleteGroupEventOutputBoundary;
import interface_adapter.DeleteGroupEvent.DeleteGroupEventController;
import interface_adapter.DeleteGroupEvent.DeleteGroupEventViewModel;
import interface_adapter.DeleteGroupEvent.DeleteGroupEventPresenter;
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
    private final MongoDAO mongoDAO = new MongoDAO(groupFactory, messageFactory, calendarFactory, userFactory, eventFactory);

    // Instance variables for reusable components
    private final AccountCreationViewModel accountCreationViewModel = new AccountCreationViewModel();
    private final AccountCreationOutputBoundary accountCreationOutputBoundary =
            new AccountCreationPresenter(accountCreationViewModel, viewManagerModel);
    private final AccountCreationInputBoundary userAccountCreationInteractor =
            new AccountCreationInteractor(mongoDAO, accountCreationOutputBoundary, userFactory);
    private final AccountCreationController accountCreationController =
            new AccountCreationController(userAccountCreationInteractor);

    private final LoginViewModel loginViewModel = new LoginViewModel();
    private final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel);
    private final LoginInputBoundary loginInteractor = new LoginInteractor(mongoDAO, loginOutputBoundary, userFactory);
    private final LoginController loginController = new LoginController(loginInteractor);

    private final GroupChatViewModel groupChatViewModel = new GroupChatViewModel(mongoDAO);
    private final MessageTranslationViewModel messageTranslationViewModel = new MessageTranslationViewModel();
    private final MessageInputBoundary messageInteractor = new MessageInteractor(mongoDAO);
    private final MessageTranslationPresenter messageTranslationPresenter =
            new MessageTranslationPresenter(messageTranslationViewModel, viewManagerModel);
    private final MessageController messageController = new MessageController(messageInteractor);
    private final MessageTranslationInputBoundary messageTranslationInteractor =
            new MessageTranslationInteractor(mongoDAO, messageTranslationPresenter, messageFactory);
    private final MessageTranslationController messageTranslationController =
            new MessageTranslationController(messageTranslationInteractor);

    // AddPersonalEventUsecase
    private final AddPersonalEventViewModel addPersonalEventViewModel = new AddPersonalEventViewModel();
    private final AddPersonalEventOutputBoundary addPersonalEventOutputBoundary = new AddPersonalEventPresenter(viewManagerModel, addPersonalEventViewModel);
    private final AddPersonalEventInputBoundary addPersonalEventInteractor = new AddPersonalEventInteractor(mongoDAO, addPersonalEventOutputBoundary, eventFactory);
    private final AddPersonalEventController addPersonalEventController = new AddPersonalEventController(addPersonalEventInteractor);

    // DeletePersonalEventUsecase
    private final DeletePersonalEventViewModel deletePersonalEventViewModel = new DeletePersonalEventViewModel();
    private final DeletePersonalEventOutputBoundary deletePersonalEventOutputBoundary = new DeletePersonalEventPresenter(viewManagerModel, deletePersonalEventViewModel);
    private final DeletePersonalEventInputBoundary deletePersonalEventInteractor = new DeletePersonalEventInteractor(mongoDAO, deletePersonalEventOutputBoundary);
    private final DeletePersonalEventController deletePersonalEventController = new DeletePersonalEventController(deletePersonalEventInteractor);

    // AddGroupEventUsecase
    private final AddGroupEventViewModel addGroupEventViewModel = new AddGroupEventViewModel();
    private final AddGroupEventOutputBoundary addGroupEventOutputBoundary = new AddGroupEventPresenter(viewManagerModel, addGroupEventViewModel);
    private final AddGroupEventInputBoundary addGroupEventInteractor = new AddGroupEventInteractor(mongoDAO, addGroupEventOutputBoundary, eventFactory);
    private final AddGroupEventController addGroupEventController = new AddGroupEventController(addGroupEventInteractor);

    // DeleteGroupEventUsecase
    private final DeleteGroupEventViewModel deleteGroupEventViewModel = new DeleteGroupEventViewModel();
    private final DeleteGroupEventOutputBoundary deleteGroupEventOutputBoundary = new DeleteGroupEventPresenter(viewManagerModel, deleteGroupEventViewModel);
    private final DeleteGroupEventInputBoundary deleteGroupEventInteractor = new DeleteGroupEventInteractor(mongoDAO, deleteGroupEventOutputBoundary);
    private final DeleteGroupEventController deleteGroupEventController = new DeleteGroupEventController(deleteGroupEventInteractor);

    // AddFriendUsecase
    private final AddFriendViewModel addFriendViewModel = new AddFriendViewModel();
    private final AddFriendOutputBoundary addFriendOutputBoundary = new AddFriendPresenter(addFriendViewModel);
    private final AddFriendInputBoundary addFriendInteractor = new AddFriendInteractor(addFriendOutputBoundary, mongoDAO);
    private final AddFriendController addFriendController = new AddFriendController(addFriendInteractor);

    // AddGroupMemberUsecase
    private final AddGroupMemberViewModel addGroupMemberViewModel = new AddGroupMemberViewModel();
    private final AddGroupMemberOutputBoundary addGroupMemberOutputBoundary = new AddGroupMemberPresenter(addGroupMemberViewModel);
    private final AddGroupMemberInputBoundary addGroupMemberInteractor = new AddGroupMemberInteractor(addGroupMemberOutputBoundary, mongoDAO);
    private final AddGroupMemberController addGroupMemberController = new AddGroupMemberController(addGroupMemberInteractor);


    // RemoveGroupMemberUsecase

    private final RemoveGroupMemberViewModel removeGroupMemberViewModel = new RemoveGroupMemberViewModel();
    private final RemoveGroupMemberOutputBoundary removeGroupMemberOutputBoundary = new RemoveGroupMemberPresenter(removeGroupMemberViewModel);
    private final RemoveGroupMemberInputBoundary removeGroupMemberInteractor = new RemoveGroupMemberInteractor(removeGroupMemberOutputBoundary, mongoDAO);
    private final RemoveGroupMemberController removeGroupMemberController = new RemoveGroupMemberController(removeGroupMemberInteractor);

    // ChangeLanguageUsecase
    private final ChangeLanguageViewModel changeLanguageViewModel = new ChangeLanguageViewModel();
    private final ChangeLanguageOutputBoundary changeLanguageOutputBoundary = new ChangeLanguagePresenter(changeLanguageViewModel);
    private final ChangeLanguageInputBoundary changeLanguageInteractor = new ChangeLanguageInteractor(mongoDAO, changeLanguageOutputBoundary);
    private final ChangeLanguageController changeLanguageController = new ChangeLanguageController(changeLanguageInteractor);

    // createGroupUseCase
    private final CreateGroupViewModel createGroupViewModel = new CreateGroupViewModel();
    private final CreateGroupOutputBoundary createGroupOutputBoundary = new CreateGroupPresenter(createGroupViewModel, viewManagerModel);
    private final CreateGroupInputBoundary createGroupInteractor = new CreateGroupInteractor(mongoDAO, createGroupOutputBoundary, groupFactory);
    private final CreateGroupController createGroupController = new CreateGroupController(createGroupInteractor);

    // removeFriendUseCase
    private final RemoveFriendViewModel removeFriendViewModel = new RemoveFriendViewModel();
    private final RemoveFriendOutputBoundary removeFriendOutputBoundary = new RemoveFriendPresenter(removeFriendViewModel, viewManagerModel);
    private final RemoveFriendInputBoundary removeFriendInputBoundary = new RemoveFriendInteractor(mongoDAO, removeFriendOutputBoundary);
    private final RemoveFriendController removeFriendController = new RemoveFriendController(removeFriendInputBoundary);

    // timeSelectionUseCase
    private final TimeslotSelectionViewModel timeslotSelectionViewModel = new TimeslotSelectionViewModel();
    private final TimeslotSelectionOutputBoundary timeslotSelectionOutputBoundary = new TimeslotSelectionPreseter(timeslotSelectionViewModel);
    private final TimeslotSelectionInputBoundary timeslotSelectionInteractor = new TimeslotSelectionInteractor(mongoDAO, timeslotSelectionOutputBoundary, eventFactory);
    private final TimeslotSelectionController timeslotSelectionController = new TimeslotSelectionController(timeslotSelectionInteractor);

    // addRecommendedUseCase
    private final AddRecommendedEventViewModel addRecommendedEventViewModel = new AddRecommendedEventViewModel();
    private final AddRecommendedEventOutputBoundary addRecommendedEventOutputBoundary = new AddRecommendedEventPresenter(viewManagerModel, addRecommendedEventViewModel);
    private final AddRecommendedEventInputBoundary addRecommendedEventInteractor = new AddRecommendedEventInteractor(mongoDAO, addRecommendedEventOutputBoundary);
    private final AddRecommendedEventController addRecommendedEventController = new AddRecommendedEventController(addRecommendedEventInteractor);

    // AddGroupEventUsecase
    private final AddGroupEventViewModel addGroupEventViewModel = new AddGroupEventViewModel();
    private final AddGroupEventOutputBoundary addGroupEventOutputBoundary = new AddGroupEventPresenter(viewManagerModel, addGroupEventViewModel);
    private final AddGroupEventInputBoundary addGroupEventInteractor = new AddGroupEventInteractor(mongoDAO, addGroupEventOutputBoundary, eventFactory);
    private final AddGroupEventController addGroupEventController = new AddGroupEventController(addGroupEventInteractor);

    // DeleteGroupEventUsecase
    private final DeleteGroupEventViewModel deleteGroupEventViewModel = new DeleteGroupEventViewModel();
    private final DeleteGroupEventOutputBoundary deleteGroupEventOutputBoundary = new DeleteGroupEventPresenter(viewManagerModel, deleteGroupEventViewModel);
    private final DeleteGroupEventInputBoundary deleteGroupEventInteractor = new DeleteGroupEventInteractor(mongoDAO, deleteGroupEventOutputBoundary);
    private final DeleteGroupEventController deleteGroupEventController = new DeleteGroupEventController(deleteGroupEventInteractor);

    // Instance variables for views
    private final AccountCreationView accountCreationView = new AccountCreationView(accountCreationViewModel, viewManager);
    private final LoginView loginView = new LoginView(loginViewModel, viewManager);
    private final GroupChatView groupChatView = new GroupChatView(groupChatViewModel, viewManager, messageTranslationViewModel);
    private final UserSettingsView userSettingsView = new UserSettingsView(viewManager, addPersonalEventViewModel, addFriendViewModel,
            changeLanguageViewModel, deletePersonalEventViewModel, removeFriendViewModel);
    private final CreateGroupView createGroupView = new CreateGroupView(createGroupViewModel, viewManager);
    private final GroupSettingsView groupSettingsView = new GroupSettingsView(viewManager, timeslotSelectionViewModel, addGroupMemberViewModel, removeGroupMemberViewModel, addRecommendedEventViewModel);
    public AppBuilder() {
        cardPanel.setLayout(cardLayout);

        // Add views to the ViewManager immediately
        viewManager.addView(loginView.getViewName(), loginView);
        viewManager.addView(accountCreationView.getViewName(), accountCreationView);
        viewManager.addView(groupChatView.getViewName(), groupChatView);
        viewManager.addView(userSettingsView.getViewName(), userSettingsView);
        viewManager.addView(createGroupView.getViewName(), createGroupView);
        viewManager.addView(groupSettingsView.getViewName(), groupSettingsView);
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

    public AppBuilder addPersonalEventUseCase() {
        userSettingsView.setAddPersonalEventController(addPersonalEventController);
        return this;
    }

    public AppBuilder addFriendUseCase() {
        userSettingsView.setAddFriendController(addFriendController);
        return this;
    }

    public AppBuilder addGroupMemberUseCase() {
        groupSettingsView.setAddGroupMemberController(addGroupMemberController);
        return this;
    }

    public AppBuilder addRemoveGroupMemberUseCase() {
        groupSettingsView.setRemoveGroupMemberController(removeGroupMemberController);
        return this;
    }


    public AppBuilder addChangeLanguageUseCase() {
        userSettingsView.setChangeLanguageController(changeLanguageController);
        return this;
    }

    public AppBuilder addDeletePersonalEventUserCase() {
        userSettingsView.setDeletePersonalEventController(deletePersonalEventController);
        return this;
    }
  
    public AppBuilder addCreateGroupUseCase() {
        createGroupView.setCreateGroupController(createGroupController);
        return this;
    }

    public AppBuilder addTimeslotSelectionUseCase() {
        groupSettingsView.setTimeslotSelectionController(timeslotSelectionController);
        return this;
    }

    public AppBuilder addRemoveFriendUseCase() {
        userSettingsView.setRemoveFriendController(removeFriendController);
        return this;
    }

    public AppBuilder addGroupEventUseCase() {
        groupSettingsView.setAddGroupEventController(addGroupEventController);
        return this;
    }

//    public AppBuilder addDeleteGroupEventUseCase() {
//        groupSettingsView.setDeleteGroupEventController(deleteGroupEventController);
//        return this;
//    }

//    public AppBuilder addAddRecommendedEventUseCase() {
//        groupSettingsView.setAddRecommendedEventController(addRecommendedEventController);
//        return this;
//    }

    public JFrame build() {
        final JFrame application = new JFrame("Linkup");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1280, 720); // Fixed window size
        application.setLocationRelativeTo(null); // Center the window
        application.add(cardPanel);

        return application;
    }
}
