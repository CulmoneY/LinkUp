package app;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        // Ensure addMessageUseCase is called before addGroupChatView
        final JFrame application = appBuilder
//                .addAccountCreationView()
//                .addLoginView()
                .addMessageUseCase() // Initialize messageTranslationViewModel here
//                .addGroupChatView()  // Now this will have a valid messageTranslationViewModel
//                .addUserSettingsView()
                .addAccountCreationUseCase()
                .addLoginUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
