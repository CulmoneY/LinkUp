package interface_adapter.CreateGroup;

// have the user in Input Data

import entity.User;
import usecases.create_group.CreateGroupInputBoundary;
import usecases.create_group.CreateGroupInputData;

import java.util.List;

public class CreateGroupController {
    private CreateGroupInputBoundary inputBoundary;

    public CreateGroupController(CreateGroupInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void execute(String groupName, List<String> groupMembers, User user) {
        CreateGroupInputData inputData = new CreateGroupInputData(groupName, groupMembers, user);
        inputBoundary.execute(inputData);

    }


}