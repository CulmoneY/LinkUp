package entity;

public interface CalendarFactory {
    /**
     * Creates a new User.
     * @param name the name of the new user
     * @return the new user
     */
    Calendar create(String name);
}
