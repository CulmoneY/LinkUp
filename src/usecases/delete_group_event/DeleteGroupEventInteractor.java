package usecases.delete_group_event;

public class DeleteGroupEventInteractor implements DeleteGroupEventInputBoundary {
    private DeleteGroupEventDataAccessInterface dataAccess;
    private DeleteGroupEventOutputBoundary presenter;

    public DeleteGroupEventInteractor(DeleteGroupEventDataAccessInterface dataAccess, DeleteGroupEventOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(DeleteGroupEventInputData inputData) {
        dataAccess.removeGroupEvent(inputData.getGroupName(), inputData.getEventName(), inputData.getStartTime(), inputData.getEndTime());
        presenter.setPassView(new DeleteGroupEventOutputData(inputData.getEventName()));
    }
}
