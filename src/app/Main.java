package app;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        // first added view becomes the default view
        final JFrame application = appBuilder.addAccountCreationView()
                .addAccountCreationUseCase()
                .addLoginView()
                .addGroupChatView()
                .build();


        application.pack();
        application.setVisible(true);
    }
}
