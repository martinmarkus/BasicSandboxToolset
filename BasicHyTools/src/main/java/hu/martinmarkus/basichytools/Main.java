package hu.martinmarkus.basichytools;

import hu.martinmarkus.basichytools.initializers.ModuleInitializer;
import hu.martinmarkus.basichytools.eventmanagement.ChatEventHandler;
import hu.martinmarkus.basichytools.eventmanagement.CommandEventHandler;
import hu.martinmarkus.basichytools.eventmanagement.UserConnectionEventHandler;
import hu.martinmarkus.basichytools.eventmanagement.UserValidationEventHandler;

public class Main {
    public static void main(String[] args) {
        ModuleInitializer.load();

        UserValidationEventHandler validationEventHandler = new UserValidationEventHandler();
        validationEventHandler.OnInvalidUserInteraction();
        UserConnectionEventHandler handler = new UserConnectionEventHandler();
        handler.onUserJoin();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            CommandEventHandler commandEventHandler = new CommandEventHandler();
            commandEventHandler.onUserExecuteCommand();

            ModuleInitializer.unload();
        });
        thread.start();
    }
}
