package mainClasses;

import commands.FileHandler;
import moominClasses.Moomin;
import commands.Command;
import commands.CommandsManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.DBConnector;

import java.util.*;

public class MoominManager {
    private static JSONParser parser = new JSONParser();
    private CommandsManager commandsManager;

    public MoominManager() {
        commandsManager = new CommandsManager();
        commandsManager.addCommand(//Добавление необходимых команд
                new Command("add", 1, true) {
                    @Override
                    public void execute(int userID) {
                        if (DBConnector.addAllMooomins(createMoomin(getArguments()), userID)) {
                            commandsManager.println("Successfuly added.");
                        } else {
                            commandsManager.println("Adding is failed!!!");
                        }
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Add element to the collection.");
                    }
                },
                new Command("remove_lowerest", 1, true) {
                    @Override
                    public void execute(int userID) {
                        if (DBConnector.removeLowerest(new Moomin(parseElement(getArguments())), userID)) {
                            commandsManager.println("Successfully deleted.");
                        } else {
                            commandsManager.println("Deleting is failed!!!");
                        }
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Delete all elements that lower than parameter.");
                    }
                },
                new Command("show", 0, true) {
                    @Override
                    public void execute(int userID) {
                        if(DBConnector.getCountOfMoomins() == 0) {
                            commandsManager.println("Collection is empty.");
                        } else {
                            DBConnector.getMoomins().forEach(commandsManager::println);
                        }
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Displays the contents of the vector.");
                    }
                },
                new Command("save", 0, true) {
                    @Override
                    public void execute(int userID) {
                        FileHandler.writeFile(DBConnector.getMoomins());
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Save collection to the file save.json.");
                    }
                },
                new Command("clear", 0, true) {
                    @Override
                    public void execute(int userID) {
                        DBConnector.clearMoomins(userID);
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Clear the collection.");
                    }
                },
                new Command("info", 0, true) {
                    @Override
                    public void execute(int userID) {
                        commandsManager.println("\nMoomin collections");
                        commandsManager.println("Collection type: " + Vector.class.getName());
                        commandsManager.println("Elements type: " + Moomin.class.getName());
                        commandsManager.println("Count of elements: " + DBConnector.getCountOfMoomins());
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Show info about collection.");
                    }
                },
                new Command("remove", 1, true) {
                    @Override
                    public void execute(int userID) {
                        if(DBConnector.remove(new Moomin(parseElement(getArguments())), userID)) {
                            commandsManager.println("Successfully deleted.");
                        } else {
                            commandsManager.println("Deleting is failed!!!");
                        }
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Delete element from the collection.");
                    }
                }
        );
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

    public void setCommandsManager(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    private JSONObject parseElement(String dataStr) {
        JSONParser parser = new JSONParser();
        JSONObject dataJSON = null;
        try {
            dataJSON = (JSONObject) parser.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataJSON;
    }

    public synchronized Vector<Moomin> createMoomin(String arguments) {
        System.err.println(arguments);
        Vector<Moomin> vector = new Vector<>();
        JSONArray dataJSON = null;
        try {
            dataJSON = (JSONArray) parser.parse(arguments.trim());
        } catch (ParseException e) {
            e.printStackTrace();
            commandsManager.println("Data in file is not a correct JSON. Check the data in file.");
            return vector;
        }

        commandsManager.println("Data converting from JSON started.");
        dataJSON.forEach(obj -> vector.add(new Moomin((JSONObject) obj)));
        commandsManager.println("Data converting finished");
        return vector;
    }

    public Vector<Moomin> getMoomins() {
        return DBConnector.getMoomins();
    }
}
