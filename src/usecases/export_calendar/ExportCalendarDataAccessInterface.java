package usecases.export_calendar;

import entity.Group;

public interface ExportCalendarDataAccessInterface {
    Group getGroup(String groupName);
}
