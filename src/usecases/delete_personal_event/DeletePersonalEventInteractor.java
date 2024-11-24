package usecases.delete_personal_event;

import entity.Calendar;
import entity.Event;
import entity.User;

import java.time.format.DateTimeFormatter;

public class DeletePersonalEventInteractor implements DeletePersonalEventInputBoundary {
    private final DeletePersonalEventDataAccessInterface dataAccessInterface;
    private final DeletePersonalEventOutputBoundary outputBoundary;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DeletePersonalEventInteractor(DeletePersonalEventDataAccessInterface dataAccessInterface,
                                         DeletePersonalEventOutputBoundary deletePersonalEventOutputBoundary){
        this.dataAccessInterface = dataAccessInterface;
        this.outputBoundary = deletePersonalEventOutputBoundary;
    }

    public void executeDelete(DeletePersonalEventInputData inputData){
        User user = inputData.getUser();
        String eventName = inputData.getEventName();
        String startTime = inputData.getStartTime();
        String endTime = inputData.getEndTime();

        dataAccessInterface.removeUserEvent(user.getName(), eventName, startTime, endTime);
        outputBoundary.setPassView(new DeletePersonalEventOutputData(eventName));
        Calendar calendar = user.getUserCalendar();
        for (Event event : calendar.getEvents()) {
            if (event.getEventName().equals(eventName) && event.getStartTime().format(formatter).equals(startTime) && event.getEndTime().format(formatter).equals(endTime)) {
                calendar.removeEvent(event);
                break;
            }
        }
    }
}
