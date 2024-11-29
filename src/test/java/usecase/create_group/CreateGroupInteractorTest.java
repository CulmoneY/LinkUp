package usecase.create_group;

import entity.*;
import usecases.account_creation.AccountCreationUserDataAccessInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.create_group.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupInteractorTest {

    private CreateGroupInteractor interactor;
    private CreateGroupDataAccessInterface createGroupDataAccessInterface;
    private CreateGroupOutputBoundary createGroupOutputBoundary;
    private GroupFactory groupFactory;
    private AccountCreationUserDataAccessInterface accountCreationUserDataAccessInterface;

    // Concrete implementation of CreateGroupDataAccessInterface
    class CreateGroupDataAccess implements CreateGroupDataAccessInterface {

        @Override
        public boolean groupExist(String groupName) {
            // Simulate group existence check (for example, "existingGroup" exists)
            return "existingGroup".equals(groupName);
        }

        @Override
        public void saveGroup(Group group) {
            // Simulate saving the group (just print or do nothing for testing)
            System.out.println("Group saved: " + group.getName());
        }

        @Override
        public List<User> groupMembersToUsers(List<String> groupMembersNames) {
            // Simulate conversion of group member names to User objects
            return Arrays.asList(new CommonUser("user1", "password1", "English"),
                    new CommonUser("user2", "password2", "English"));
        }
    }

    // Concrete implementation of CreateGroupOutputBoundary
    class CreateGroupOutput implements CreateGroupOutputBoundary {
        private String failView;
        private CreateGroupOutputData outputData;

        @Override
        public void setFailView(String errorType) {
            this.failView = errorType;
        }

        @Override
        public void setPassView(CreateGroupOutputData outputData) {
            this.outputData = outputData;
        }

        public String getFailView() {
            return failView;
        }

        public CreateGroupOutputData getPassView() {
            return outputData;
        }
    }



    @BeforeEach
    void setUp() {
        // Initialize dependencies
        createGroupDataAccessInterface = new CreateGroupDataAccess();
        createGroupOutputBoundary = new CreateGroupOutput();
        groupFactory = new CommonGroupFactory();
        accountCreationUserDataAccessInterface = new AccountCreationUserDataAccessInterface() {
            @Override
            public boolean accountExists(String username) {
                return false;  // Simulate non-existent account
            }

            @Override
            public void saveUser(User user) {
                // Do nothing for now
            }
        };

        interactor = new CreateGroupInteractor(createGroupDataAccessInterface, createGroupOutputBoundary, groupFactory, accountCreationUserDataAccessInterface);
    }

    @Test
    void testGroupAlreadyExists() {
        // Prepare input data for an existing group
        CreateGroupInputData inputData = new CreateGroupInputData("existingGroup", Arrays.asList("user1", "user2"));

        // Execute the interactor
        interactor.execute(inputData);

        // Verify the fail view is set to "group_exists"
        assertEquals("group_exists", ((CreateGroupOutput) createGroupOutputBoundary).getFailView());
    }

    @Test
    void testMissingGroupName() {
        // Prepare input data with a missing group name
        CreateGroupInputData inputData = new CreateGroupInputData("", Arrays.asList("user1", "user2"));

        // Execute the interactor
        interactor.execute(inputData);

        // Verify the fail view is set to "missing_group_name"
        assertEquals("missing_group_name", ((CreateGroupOutput) createGroupOutputBoundary).getFailView());
    }

    @Test
    void testSuccessfulGroupCreation() {
        // Prepare input data for a valid group creation
        CreateGroupInputData inputData = new CreateGroupInputData("NewGroup", Arrays.asList("user1", "user2"));

        // Execute the interactor
        interactor.execute(inputData);

        // Verify the pass view is set correctly
        CreateGroupOutputData outputData = ((CreateGroupOutput) createGroupOutputBoundary).getPassView();
        assertNotNull(outputData);
        assertEquals("NewGroup", outputData.getGroupName());
        assertEquals(Arrays.asList("user1", "user2"), outputData.getGroupMembers());
        assertTrue(outputData.getSuccess());
    }

}
