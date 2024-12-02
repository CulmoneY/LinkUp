package usecases.modify_group_name;

public class ModifyGroupNameInteractor implements ModifyGroupNameInputBoundary{
    private ModifyGroupNameOutputBoundary outputPresenter;
    private ModifyGroupNameDataAccessInterface dataAccess;

    public ModifyGroupNameInteractor(ModifyGroupNameOutputBoundary outputPresenter, ModifyGroupNameDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;
        this.dataAccess = dataAccess;
    }
    @Override
    public void executeModifyGroupName(ModifyGroupNameInputData inputData) {
        // first check if
        // the new group name is not empty
        if (missingField(inputData)) {
            //error message
            outputPresenter.setFailViewData("Please Fill in all Fields!");
        }
        // the group name is not empty

        // check if

        // the new group name is not already taken

        else if (groupNameTaken(inputData)) {
            outputPresenter.setFailViewData("This group name already exists, Please choose another one!");
        }
        else {
            // gets the arguments
            // from the input Data
            String oldGroupName = inputData.getOldGroupName();
            String newGroupName = inputData.getNewGroupName();

            // calls the DAO to modify the group name
            dataAccess.modifyGroupName(oldGroupName, newGroupName);

            // Output Data for ModifyGroupName

            ModifyGroupNameOutputData outputData = new ModifyGroupNameOutputData(oldGroupName, newGroupName);
            // updates the presenter
            // successview.
            outputPresenter.setPassViewData(outputData);

        }
    }



    public boolean missingField (ModifyGroupNameInputData inputData) {
        // checks whether its an empty groupname
        return (inputData.getNewGroupName().isEmpty());
    }

    public boolean groupNameTaken (ModifyGroupNameInputData inputData) {
        // Calls the DAO to check if group already has
        // this name.
        return dataAccess.groupExist(inputData.getNewGroupName());
    }
}
