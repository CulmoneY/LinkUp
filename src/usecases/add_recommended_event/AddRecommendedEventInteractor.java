package usecases.add_recommended_event;
import entity.Event;
import entity.EventFactory;
import usecases.add_recommended_event.AddRecommendedEventDataAccessInterface;
import usecases.add_recommended_event.AddRecommendedEventInputBoundary;
import usecases.add_recommended_event.AddRecommendedEventOutputBoundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddRecommendedEventInteractor implements AddRecommendedEventInputBoundary {
    private AddRecommendedEventDataAccessInterface dataAccess;
    private AddRecommendedEventInputData inputData;
    private AddRecommendedEventOutputBoundary outputBoundary;



    public AddRecommendedEventInteractor(AddRecommendedEventDataAccessInterface dataAccess, AddRecommendedEventInputData inputData, AddRecommendedEventOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.inputData = inputData;
        this.outputBoundary = outputBoundary;

    }

    public void addRecommendedEvent(AddRecommendedEventInputData inputData, AddRecommendedEventOutputBoundary outputBoundary) {
        Event event = inputData.getEvent();
        String groupName = inputData.getGroupName();
        dataAccess.addEventToGroup(event, groupName);
        outputBoundary.setPassView(new AddRecommendedEventOutputData(event.getEventName()));
    }

    @Override
    public void execute(AddRecommendedEventInputData inputData) {
        addRecommendedEvent(inputData, outputBoundary);
    }
}