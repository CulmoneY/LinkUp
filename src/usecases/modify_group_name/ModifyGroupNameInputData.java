package usecases.modify_group_name;

// This file is for the
//
// ModifyGroupName usecase
//
// This is the Input Data for this usecase.

public class ModifyGroupNameInputData {
    // Instance attributes for Input Data
    // oldGroupName :
    // the String that corresponds to the old group name.
    private String oldGroupName;

    // newGroupName :
    // the String that corresponds to the new group name.
    private String newGroupName;


    // the constructor for the modifyGroupName input data
    // 2 arguments
    // they are the instance attribute.


    public ModifyGroupNameInputData(String oldGroupName, String newGroupName) {
        this.oldGroupName = oldGroupName;
        this.newGroupName = newGroupName;
    }

    // Getter for oldGroupName
    // Returns the oldGroupName attribute

    public String getOldGroupName() {
        return oldGroupName;
    }

    // Getter for newGroupName
    // Returns the newGroupName attribute

    public String getNewGroupName() {
        return newGroupName;
    }
}
