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

    public void execute(String groupName, List<String> groupMembers, String current_user) {
        CreateGroupInputData inputData = new CreateGroupInputData(groupName, groupMembers, current_user);
        inputBoundary.execute(inputData);

    }


}