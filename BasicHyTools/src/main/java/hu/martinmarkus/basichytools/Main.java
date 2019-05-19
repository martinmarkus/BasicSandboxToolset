package hu.martinmarkus.basichytools;

import hu.martinmarkus.basichytools.configmanagement.initializers.HyToolsInitializer;
import hu.martinmarkus.basichytools.configmanagement.managers.UserManager;
import hu.martinmarkus.basichytools.eventmanagement.UserConnectionEventHandler;

import java.io.IOException;
import java.util.Properties;

import static com.sun.org.apache.xalan.internal.utils.SecuritySupport.getResourceAsStream;

public class Main {
    private static void printProjectProperties() {
        try {
            Properties properties = new Properties();
            properties.load(getResourceAsStream("project.properties"));

            String message = properties.getProperty("artifactId") + " v" + properties.getProperty("version")
                    + " by '" + properties.getProperty("author") + "' is loaded."
                    + "\nProject repository: " + properties.getProperty("repo");

            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HyToolsInitializer.initialize();

        UserManager userManager = UserManager.getInstance();
        String birdemic = "birdemic";

        UserConnectionEventHandler handler = new UserConnectionEventHandler();
        //handler.onUserJoin();
        handler.onUserJoin();
        /*

        userManager.registerUser(birdemic);
        ConfigReader<User> reader = new YamlConfigReader<>(User.class, HyToolsInitializer.getUsersPath());
        reader.read("mockUser12345", user -> {

            String permission = "group.default";
            boolean hasPermission = user.hasPermission(permission);
        });*/

    }
}
