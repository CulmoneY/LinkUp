package usecases.delete_personal_event;

import entity.Calendar;
import entity.Event;
import entity.Group;

import java.time.format.DateTimeFormatter;

public class DeleteGroupEventInteractor implements DeleteGroupEventInputBoundary {
    private final DeleteGroupEventDataAccessInterface dataAccessInterface;
    private final DeleteGroupEventOutputBoundary outputBoundary;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DeleteGroupEventInteractor(DeleteGroupEventDataAccessInterface dataAccessInterface,
                                         DeleteGroupEventOutputBoundary deleteGroupEventOutputBoundary){
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = deleteGroupEventOutputBoundary;
    }

    public void executeDelete(DeleteGroupEventInputData inputData){
        Group group = inputData.getGroup();
        String eventName = inputData.getEventName();
        String startTime = inputData.getStartTime();
        String endTime = inputData.getEndTime();

        dataAccessInterface.removeGroupEvent(group.getName(), eventName, startTime, endTime);
        outputBoundary.setPassView(new DeleteGroupEventOutputData(eventName));
        Calendar calendar = group.getGroupCalendar();
        for (Event event : calendar.getEvents()) {
            if (event.getEventName().equals(eventName) && event.getStartTime().format(formatter).equals(startTime) && event.getEndTime().format(formatter).equals(endTime)) {
                calendar.removeEvent(event);
                break;
            }
        }
    }
}
