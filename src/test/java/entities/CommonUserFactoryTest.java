package entities;

import entity.CommonUserFactory;
import entity.User;
import entity.CommonUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommonUserFactoryTest {

    private CommonUserFactory userFactory;

    @BeforeEach
    public void setUp() {
        // Initialize the CommonUserFactory
        userFactory = new CommonUserFactory();
    }

    @Test
    public void testCreate() {
        // Prepare test data
        String name = "John Doe";
        String password = "password123";
        String language = "English";

        // Create a new user using the factory
        User user = userFactory.create(name, password, language);

        // Verify the user was created with the correct parameters
        assertNotNull(user, "User should not be null.");
        assertEquals(name, user.getName(), "User name should match.");
        assertEquals(password, user.getPassword(), "User password should match.");
        assertEquals(language, user.getLanguage(), "User language should match.");
    }

    @Test
    public void testCreateWithEmptyName() {
        // Prepare test data with an empty name
        String name = "";
        String password = "password123";
        String language = "English";

        // Create a new user using the factory
        User user = userFactory.create(name, password, language);

        // Verify the user was created with the correct parameters
        assertNotNull(user, "User should not be null.");
        assertEquals(name, user.getName(), "User name should match.");
        assertEquals(password, user.getPassword(), "User password should match.");
        assertEquals(language, user.getLanguage(), "User language should match.");
    }

    @Test
    public void testCreateWithNullName() {
        // Prepare test data with a null name
        String name = null;
        String password = "password123";
        String language = "English";

        // Create a new user using the factory
        User user = userFactory.create(name, password, language);

        // Verify the user was created with the correct parameters
        assertNotNull(user, "User should not be null.");
        assertEquals(name, user.getName(), "User name should match.");
        assertEquals(password, user.getPassword(), "User password should match.");
        assertEquals(language, user.getLanguage(), "User language should match.");
    }

    @Test
    public void testCreateWithNullPassword() {
        // Prepare test data with a null password
        String name = "John Doe";
        String password = null;
        String language = "English";

        // Create a new user using the factory
        User user = userFactory.create(name, password, language);

        // Verify the user was created with the correct parameters
        assertNotNull(user, "User should not be null.");
        assertEquals(name, user.getName(), "User name should match.");
        assertEquals(password, user.getPassword(), "User password should match.");
        assertEquals(language, user.getLanguage(), "User language should match.");
    }

    @Test
    public void testCreateWithNullLanguage() {
        // Prepare test data with a null language
        String name = "John Doe";
        String password = "password123";
        String language = null;

        // Create a new user using the factory
        User user = userFactory.create(name, password, language);

        // Verify the user was created with the correct parameters
        assertNotNull(user, "User should not be null.");
        assertEquals(name, user.getName(), "User name should match.");
        assertEquals(password, user.getPassword(), "User password should match.");
        assertEquals(language, user.getLanguage(), "User language should match.");
    }
}
