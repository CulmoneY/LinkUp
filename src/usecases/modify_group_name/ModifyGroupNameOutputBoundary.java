package usecases.modify_group_name;
// This file is for the
// ModifyGroupName
//  usecase
//
// This is the Input Boundary for this usecase.

public interface ModifyGroupNameOutputBoundary {
    void setPassViewData(ModifyGroupNameOutputData outputData);

    void setFailViewData(String error);
}
