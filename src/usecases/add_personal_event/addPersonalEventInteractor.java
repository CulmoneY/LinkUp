package usecases.add_personal_event;
import entity.Event;
import entity.User;
import entity.EventFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class addPersonalEventInteractor implements addPersonalEventInputBoundary {
    private addPersonalEventDataAccessInterface dataAccess;
    private addPersonalEventOutputBoundary outputBoundary;
    private EventFactory eventFactory;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");


    public addPersonalEventInteractor(addPersonalEventDataAccessInterface dataAccess, addPersonalEventOutputBoundary outputBoundary, EventFactory eventFactory) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
        this.eventFactory = eventFactory;
    }

    @Override
    public void executeCreate(addPersonalEventInputData inputData) {
        if (missingFields(inputData)) {
            outputBoundary.setFailView("missing_fields");
        } else if (!validTime(inputData.getStartTime(), inputData.getEndTime())) {
            outputBoundary.setFailView("invalid_time");
        } else {
            Event event = eventFactory.create(inputData.getEventName(), parseDateTime(inputData.getStartTime())
                    ,parseDateTime(inputData.getEndTime()), false);
            dataAccess.addEvent(inputData.getUser(), event);
            addPersonalEventOutputData outputData = new addPersonalEventOutputData(event.getEventName(), event.getStartTime().toString(), event.getEndTime().toString());
            inputData.getUser().getUserCalendar().addEvent(event);
            outputBoundary.setPassView(outputData);

        }
    }

    private boolean missingFields(addPersonalEventInputData inputData) {
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
            return LocalDateTime.parse(dateTimeStr, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
