package usecases.login;

import entity.Calendar;
import entity.CommonUser;
import entity.Event;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginInteractorTest {

    private LoginUserDataAccessInterface userDataAccess;
    private LoginOutputBoundary presenter;
    private UserFactory userFactory;
    private LoginInteractor interactor;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        userDataAccess = mock(LoginUserDataAccessInterface.class);
        presenter = mock(LoginOutputBoundary.class);
        userFactory = mock(UserFactory.class);

        // Initialize the interactor
        interactor = new LoginInteractor(userDataAccess, presenter, userFactory);
    }

    @Test
    void testExecute_MissingFields() {
        // Prepare input data with missing fields
        LoginInputData inputData = new LoginInputData("", "");

        // Execute the interactor
        interactor.execute(inputData);

        // Verify that the fail view was set for missing fields
        verify(presenter).setFailView("Please Fill in All Fields!");
    }

    @Test
    void testExecute_UserDoesNotExist() {
        // Prepare input data for a non-existent user
        LoginInputData inputData = new LoginInputData("john_doe", "password123");

        // Simulate that the user does not exist
        when(userDataAccess.accountExists("john_doe")).thenReturn(false);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify that the fail view was set for non-existent user
        verify(presenter).setFailView("This User Does Not Exist!");
    }

    @Test
    void testExecute_IncorrectPassword() {
        // Prepare input data with incorrect password
        LoginInputData inputData = new LoginInputData("john_doe", "wrong_password");

        // Simulate that the user exists but the password is incorrect
        when(userDataAccess.accountExists("john_doe")).thenReturn(true);
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("correct_password");
        when(userDataAccess.getUser("john_doe")).thenReturn(user);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify that the fail view was set for incorrect password
        verify(presenter).setFailView("Incorrect Password!");
    }

    @Test
    void testExecute_SuccessfulLogin() {
        // Prepare input data for a successful login
        LoginInputData inputData = new LoginInputData("john_doe", "password123");

        // Simulate that the user exists and the password is correct
        when(userDataAccess.accountExists("john_doe")).thenReturn(true);
        Calendar mockCalendar = mock(Calendar.class);
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("password123");
        when(user.getUserCalendar()).thenReturn(mockCalendar);
        when(mockCalendar.getEvents()).thenReturn(new ArrayList<>());
        when(userDataAccess.getUser("john_doe")).thenReturn(user);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify that the pass view was set with the correct data
        verify(presenter).setPassView(any(LoginOutputData.class));
    }

    @Test
    void testExecute_CleansDatabase() {
        // Prepare input data for a user with past events
        LoginInputData inputData = new LoginInputData("john_doe", "password123");

        // Simulate that the user exists and the password is correct
        when(userDataAccess.accountExists("john_doe")).thenReturn(true);

        // Mock a past event
        Event pastEvent = mock(Event.class);
        LocalDateTime pastTime = LocalDateTime.now().minusDays(1);
        when(pastEvent.getEndTime()).thenReturn(pastTime);
        when(pastEvent.getEventName()).thenReturn("Past Event");
        when(pastEvent.getStartTime()).thenReturn(pastTime);

        // Create a list of events containing the past event
        List<Event> events = new ArrayList<>();
        events.add(pastEvent);

        // Mock the calendar and user
        Calendar mockCalendar = mock(Calendar.class);
        when(mockCalendar.getEvents()).thenReturn(events);

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("password123");
        when(user.getName()).thenReturn("john_doe");
        when(user.getUserCalendar()).thenReturn(mockCalendar);

        when(userDataAccess.getUser("john_doe")).thenReturn(user);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify that the past event was removed from the database with correct arguments
        verify(userDataAccess).removeUserEvent(
                eq("john_doe"),
                eq("Past Event"),
                anyString(),
                anyString()
        );
    }
}
