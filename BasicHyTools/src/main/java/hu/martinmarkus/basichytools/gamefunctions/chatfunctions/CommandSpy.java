package hu.martinmarkus.basichytools.gamefunctions.chatfunctions;

import hu.martinmarkus.basichytools.gamefunctions.GameFunction;
import hu.martinmarkus.basichytools.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommandSpy extends GameFunction {

    @Autowired
    public CommandSpy(@Value("commandSpy") String functionName) {
        super(functionName);
    }

    @Override
    public void setRequiredParams(String rawCommand, User executor) {
        super.rawCommand = rawCommand;
        super.executor = executor;
        initializeCooldownContainer();
    }

    @Override
    public Object executeWithReturnValue() {
        execute();
        return null;
    }

    @Override
    public void execute() {
        super.runFunction(() -> {
            boolean commandSpyState;
            String message;

            if (executor.isCommandSpyActive()) {
                commandSpyState = false;
                message = languageConfig.getCommandSpyDeactivated();
            } else {
                commandSpyState = true;
                message = languageConfig.getCommandSpyActivated();
            }

            executor.setCommandSpyActive(commandSpyState);
            executor.sendMessage(message);
        });
    }
}
