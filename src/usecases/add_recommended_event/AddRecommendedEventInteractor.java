package usecases.add_recommended_event;

import entity.Group;
import entity.Event;

public class AddRecommendedEventInteractor implements AddRecommendedEventInputBoundary {

    private final AddRecommendedEventDataAccessInterface dataAccess;
    private final AddRecommendedEventOutputBoundary outputBoundary;

    public AddRecommendedEventInteractor(AddRecommendedEventDataAccessInterface dataAccess,
                                         AddRecommendedEventOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(AddRecommendedEventInputData inputData) {
        // Step 1: Retrieve the group by its name
        Group group = dataAccess.getGroup(inputData.getGroupName());

        // Step 2: Add the recommended event to the group's calendar
        if (group != null) {
            group.getGroupCalendar().addEvent(inputData.getEvent());

            // Step 3: Create the output data and pass it to the presenter
            AddRecommendedEventOutputData outputData = new AddRecommendedEventOutputData(inputData.getEvent().getEventName());
            outputBoundary.setPassView(outputData);
        }
        else {
            // Handle case where the group is not found (optional)
            // In a real scenario, you might want to return a failure view here.
            // outputBoundary.setFailureView("Group not found");
        }
    }
}
