package usecases.modify_group_name;



// This file is for the
// ModifyGroupName usecase
// This is the Data Access Interface for this usecase.

public interface ModifyGroupNameDataAccessInterface {

    // A method that takes two parameters
    // oldGroupName is the old group name String we want to change into
    // NewGroupName String for the new group name.

    void modifyGroupName(String oldGroupName, String newGroupName);


    // A method that takes one parameter
    // returns whether the newGroupName is already taken name.

    boolean groupExist(String newGroupName);
}
