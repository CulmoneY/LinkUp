package usecases.create_group;

import entity.Group;
import entity.GroupFactory;
import entity.User;
import usecases.account_creation.AccountCreationUserDataAccessInterface;

import java.util.List;

public class CreateGroupInteractor implements CreateGroupInputBoundary {
    final CreateGroupDataAccessInterface createGroupDataAccessInterface;
    final CreateGroupOutputBoundary createGroupOutputBoundary;
    final GroupFactory groupFactory;
    final AccountCreationUserDataAccessInterface accountCreationUserDataAccessInterface;


   public CreateGroupInteractor(CreateGroupDataAccessInterface createGroupDataAccessInterface,
                                CreateGroupOutputBoundary createGroupOutputBoundary,
                                GroupFactory groupFactory,
                                AccountCreationUserDataAccessInterface accountCreationUserDataAccessInterface) {
       this.createGroupDataAccessInterface = createGroupDataAccessInterface;
       this.createGroupOutputBoundary = createGroupOutputBoundary;
       this.groupFactory = groupFactory;
       this.accountCreationUserDataAccessInterface = accountCreationUserDataAccessInterface;

   }


    @Override
    public void execute(CreateGroupInputData inputData) {
       if (groupExist(inputData)) {
           createGroupOutputBoundary.setFailView("group_exists");
       }
       else if (missingGroupName(inputData)) {
           createGroupOutputBoundary.setFailView("missing_group_name");
       } else {
           List<String> groupMembersNames = inputData.getGroupMembers();
           List<User> groupMembers = createGroupDataAccessInterface.groupMembersToUsers(groupMembersNames);
           Group group = groupFactory.create(inputData.getGroupName(), groupMembers);
           createGroupDataAccessInterface.saveGroup(group);
           createGroupDataAccessInterface.addGroupToUser(inputData.getCurrent_user(), group);
           for (String groupmember : groupMembersNames) {
               createGroupDataAccessInterface.addGroupToUser(groupmember, group);
           }
           CreateGroupOutputData outputData = new CreateGroupOutputData(inputData.getGroupName(),
                   inputData.getGroupMembers(), true);
           createGroupOutputBoundary.setPassView(outputData);

       }
    }

    @Override
    public void switchToMainView() {
       //TODO: Implement this method.
    }

    public boolean groupExist(CreateGroupInputData inputData) {
       return createGroupDataAccessInterface.groupExist(inputData.getGroupName());
    }

    public boolean missingGroupName(CreateGroupInputData inputData) {
       return inputData.getGroupName() == null || inputData.getGroupName().isEmpty();
    }


}
