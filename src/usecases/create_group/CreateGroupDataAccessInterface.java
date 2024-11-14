package usecases.create_group;

import entity.Group;
import entity.User;

import java.util.List;

public interface CreateGroupDataAccessInterface {

    boolean groupExist(String groupName);

    void saveGroup(Group group);

    List<User> groupMembersToUsers(List<String> groupName);
}
