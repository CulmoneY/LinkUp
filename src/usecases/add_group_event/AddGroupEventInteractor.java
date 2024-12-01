package usecases.add_group_event;

import entity.Event;
import entity.EventFactory;
import usecases.add_personal_event.AddPersonalEventInputData;
import usecases.add_personal_event.AddPersonalEventInteractor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddGroupEventInteractor implements AddGroupEventInputBoundary {
    private final AddGroupEventDataAccessInterface dataAccess;
    private final AddGroupEventOutputBoundary outputBoundary;
    private EventFactory eventFactory;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public AddGroupEventInteractor(AddGroupEventDataAccessInterface dataAccess, AddGroupEventOutputBoundary outputBoundary, EventFactory eventFactory) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.eventFactory = eventFactory;
    }

    @Override
    public void executeCreate(AddGroupEventInputData inputData) {
        if (missingFields(inputData)) {
            outputBoundary.setFailView("Fill in all Fields!");
        } else if (!validTime(inputData.getStartTime(), inputData.getEndTime())) {
            outputBoundary.setFailView("Invalid Time Format!");
        } else {
            Event event = eventFactory.create(inputData.getEventName(), parseDateTime(inputData.getStartTime())
                    ,parseDateTime(inputData.getEndTime()), true);
            dataAccess.addGroupEvent(inputData.getGroupName(), event);
            outputBoundary.setPassView(new AddGroupOutputData(inputData.getGroupName(), event.getEventName()));
        }
    }

    private boolean missingFields(AddGroupEventInputData inputData) {
        return inputData.getEventName().isEmpty() || inputData.getStartTime().isEmpty() || inputData.getEndTime().isEmpty();
    }

    private boolean validTime(String startTime, String endTime) {
        LocalDateTime startDateTime = parseDateTime(startTime);
        LocalDateTime endDateTime = parseDateTime(endTime);

        if (startDateTime == null || endDateTime == null) {
            return false;
        }

        return startDateTime.isBefore(endDateTime);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            dateTimeStr = dateTimeStr.trim().toUpperCase();
            return LocalDateTime.parse(dateTimeStr, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
