package usecases.create_group;

import usecases.account_creation.AccountCreationInputData;

public interface CreateGroupInputBoundary {
    void execute(CreateGroupInputData inputData);
}