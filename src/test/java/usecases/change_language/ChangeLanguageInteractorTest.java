package usecases.change_language;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ChangeLanguageInteractorTest {

    private ChangeLanguageDataAccessInterface dataAccess;
    private ChangeLanguageOutputBoundary outputBoundary;
    private ChangeLanguageInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccess = mock(ChangeLanguageDataAccessInterface.class);
        outputBoundary = mock(ChangeLanguageOutputBoundary.class);
        interactor = new ChangeLanguageInteractor(dataAccess, outputBoundary);
    }

    @Test
    void testChangeLanguageEnglish() {
        // Arrange
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "English");
        when(user.getName()).thenReturn("TestUser");

        // Act
        interactor.changeLanguage(inputData);

        // Assert
        verify(user).setLanguage("EN-US");
        verify(dataAccess).changeUserLanguage("TestUser", "EN-US");

        ArgumentCaptor<ChangeLanguageOutputData> captor = ArgumentCaptor.forClass(ChangeLanguageOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("EN-US", captor.getValue().getLanguage());
    }

    @Test
    void testChangeLanguageArabic() {
        // Arrange
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Arabic");
        when(user.getName()).thenReturn("TestUser");

        // Act
        interactor.changeLanguage(inputData);

        // Assert
        verify(user).setLanguage("AR");
        verify(dataAccess).changeUserLanguage("TestUser", "AR");

        ArgumentCaptor<ChangeLanguageOutputData> captor = ArgumentCaptor.forClass(ChangeLanguageOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("AR", captor.getValue().getLanguage());
    }

    @Test
    void testChangeLanguageDefaultCase() {
        // Arrange
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "UnknownLanguage");
        when(user.getName()).thenReturn("TestUser");

        // Act
        interactor.changeLanguage(inputData);

        // Assert
        verify(user).setLanguage(null);
        verify(dataAccess).changeUserLanguage("TestUser", null);

        ArgumentCaptor<ChangeLanguageOutputData> captor = ArgumentCaptor.forClass(ChangeLanguageOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals(null, captor.getValue().getLanguage());
    }

    @Test
    void testChangeLanguageFrench() {
        // Arrange
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "French");
        when(user.getName()).thenReturn("TestUser");

        // Act
        interactor.changeLanguage(inputData);

        // Assert
        verify(user).setLanguage("FR");
        verify(dataAccess).changeUserLanguage("TestUser", "FR");

        ArgumentCaptor<ChangeLanguageOutputData> captor = ArgumentCaptor.forClass(ChangeLanguageOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("FR", captor.getValue().getLanguage());
    }

    @Test
    void testChangeLanguageSpanish() {
        // Arrange
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Spanish");
        when(user.getName()).thenReturn("TestUser");

        // Act
        interactor.changeLanguage(inputData);

        // Assert
        verify(user).setLanguage("ES");
        verify(dataAccess).changeUserLanguage("TestUser", "ES");

        ArgumentCaptor<ChangeLanguageOutputData> captor = ArgumentCaptor.forClass(ChangeLanguageOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("ES", captor.getValue().getLanguage());
    }

    @Test
    void testChangeLanguageJapanese() {
        // Arrange
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Japanese");
        when(user.getName()).thenReturn("TestUser");

        // Act
        interactor.changeLanguage(inputData);

        // Assert
        verify(user).setLanguage("JA");
        verify(dataAccess).changeUserLanguage("TestUser", "JA");

        ArgumentCaptor<ChangeLanguageOutputData> captor = ArgumentCaptor.forClass(ChangeLanguageOutputData.class);
        verify(outputBoundary).setPassView(captor.capture());
        assertEquals("JA", captor.getValue().getLanguage());
    }

    // Add similar tests for remaining languages (Korean, Russian, Chinese, Greek, Portuguese, Italian)
    @Test
    void testChangeLanguageKorean() {
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Korean");
        interactor.changeLanguage(inputData);
        verify(user).setLanguage("KO");
    }

    @Test
    void testChangeLanguageRussian() {
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Russian");
        interactor.changeLanguage(inputData);
        verify(user).setLanguage("RU");
    }

    @Test
    void testChangeLanguageChinese() {
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Chinese");
        interactor.changeLanguage(inputData);
        verify(user).setLanguage("ZH-HANS");
    }

    @Test
    void testChangeLanguageGreek() {
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Greek");
        interactor.changeLanguage(inputData);
        verify(user).setLanguage("EL");
    }

    @Test
    void testChangeLanguagePortuguese() {
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Portuguese");
        interactor.changeLanguage(inputData);
        verify(user).setLanguage("PT-BR");
    }

    @Test
    void testChangeLanguageItalian() {
        User user = mock(User.class);
        ChangeLanguageInputData inputData = new ChangeLanguageInputData(user, "Italian");
        interactor.changeLanguage(inputData);
        verify(user).setLanguage("IT");
    }
}
