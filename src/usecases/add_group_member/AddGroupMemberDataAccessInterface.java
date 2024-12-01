package usecases.add_group_member;

import entity.Group;
import entity.User;

public interface AddGroupMemberDataAccessInterface {
    void addGroupToUser(String username, String groupname);

    void addUserToGroup(String groupname, String username);

}
