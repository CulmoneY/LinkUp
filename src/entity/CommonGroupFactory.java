package entity;

import java.util.List;

public class CommonGroupFactory implements GroupFactory {

    @Override
    public CommonGroup create(String name, List<User> users) {
        return new CommonGroup(name, users);
    }
}
