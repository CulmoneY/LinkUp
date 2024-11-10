import entity.CommonUserFactory;
import entity.User;
import daos.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private UserDAO userDAO;
    private User user;

    @BeforeEach
    public void setUp() {
        CommonUserFactory userFactory = new CommonUserFactory();
        userDAO = new UserDAO();
        user = userFactory.create("Samy Asnoun", "ilovetren123", "Arabic");
    }

    @Nested
    class AccountSetupTests {
        @Test
        public void testSaveUser() {
            userDAO.saveUser(user);
            assertTrue(userDAO.accountExists(user.getName()));
        }

        @Test
        public void testDeleteUser() {
            userDAO.deleteUser(user.getName());
            assertFalse(userDAO.accountExists(user.getName()));
        }
    }

    @Nested
    class AccountFieldsTests {
        @BeforeEach
        public void setUp() {
            userDAO.saveUser(user);
        }

        @AfterEach
        public void tearDown() {
            userDAO.deleteUser(user.getName());
        }

        @Test
        public void testAccountExists() {
            assertTrue(userDAO.accountExists(user.getName()));
        }

        @Test
        public void testAccountFieldsCorrect() {
            User userFromDB = userDAO.getUser(user.getName());
            assertEquals(user.getName(), userFromDB.getName());
            assertEquals(user.getPassword(), userFromDB.getPassword());
            assertEquals(user.getLanguage(), userFromDB.getLanguage());
        }
    }


}
