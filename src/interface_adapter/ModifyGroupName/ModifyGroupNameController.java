package interface_adapter.ModifyGroupName;

import usecases.modify_group_name.ModifyGroupNameInputBoundary;
import usecases.modify_group_name.ModifyGroupNameInputData;

public class ModifyGroupNameController {

    private final ModifyGroupNameInputBoundary modifyGroupNameInteractor;


    // Constructor for the Controller.

    public ModifyGroupNameController(ModifyGroupNameInputBoundary modifyGroupNameInteractor) {
        this.modifyGroupNameInteractor = modifyGroupNameInteractor;
    }

    // execute for the controller
    // takes both arguments
    // oldgroupname
    // and new group name
    // both are Strings.
    public void execute(String oldGroupName, String newGroupName) {
        ModifyGroupNameInputData inputData = new ModifyGroupNameInputData(oldGroupName, newGroupName);
        modifyGroupNameInteractor.executeModifyGroupName(inputData);
    }

}
