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


   public CreateGroupInteractor(CreateGroupDataAccessInterface createGroupDataAccessInterface,
                                CreateGroupOutputBoundary createGroupOutputBoundary,
                                GroupFactory groupFactory) {
       this.createGroupDataAccessInterface = createGroupDataAccessInterface;
       this.createGroupOutputBoundary = createGroupOutputBoundary;
       this.groupFactory = groupFactory;

   }


    @Override
    public void execute(CreateGroupInputData inputData) {
       if (groupExist(inputData)) {
           createGroupOutputBoundary.setFailView("This Group Name Already Exists!");
       }
       else if (missingGroupName(inputData)) {
           createGroupOutputBoundary.setFailView("You Are Missing a Group Name!");
       } else if (emptyGroupMembers(inputData)) {
           createGroupOutputBoundary.setFailView("Please Select At Least One Friend");
       }
       else {
           List<String> groupMembersNames = inputData.getGroupMembers();
           List<User> groupMembers = createGroupDataAccessInterface.groupMembersToUsers(groupMembersNames);
           groupMembers.add(inputData.getCurrent_user());
           Group group = groupFactory.create(inputData.getGroupName(), groupMembers);
           createGroupDataAccessInterface.saveGroup(group);
           createGroupDataAccessInterface.addGroupToUser(inputData.getCurrent_user().getName(), group);

           for (String groupmember : groupMembersNames) {
               createGroupDataAccessInterface.addGroupToUser(groupmember, group);
           }
           inputData.getCurrent_user().addGroup(group);
           CreateGroupOutputData outputData = new CreateGroupOutputData(inputData.getGroupName(),
                   inputData.getGroupMembers(), true);
           createGroupOutputBoundary.setPassView(outputData);

       }
    }

    public boolean groupExist(CreateGroupInputData inputData) {
       return createGroupDataAccessInterface.groupExist(inputData.getGroupName());
    }

    public boolean missingGroupName(CreateGroupInputData inputData) {
       return inputData.getGroupName() == null || inputData.getGroupName().isEmpty();
    }

    public boolean emptyGroupMembers(CreateGroupInputData inputData) {
       return inputData.getGroupMembers() == null || inputData.getGroupMembers().isEmpty();
    }


}
