package usecases.modify_group_name;
// This file is for the
// ModifyGroupName
//  usecase
//
// This is the Output Data for this usecase.

public class ModifyGroupNameOutputData {
    private String oldGroupName;
    private String newGroupName;

    public ModifyGroupNameOutputData(String oldGroupName, String newGroupName) {
        this.oldGroupName = oldGroupName;
        this.newGroupName = newGroupName;
    }

    public String getOldGroupName() {
        return oldGroupName;
    }

    public void setOldGroupName(String oldGroupName) {
        this.oldGroupName = oldGroupName;
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public void setNewGroupName(String newGroupName) {
        this.newGroupName = newGroupName;
    }
}
