package mainClasses;

import commands.FileHandler;
import moominClasses.Moomin;
import commands.Command;
import commands.CommandsManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class MoominManager {
    private Vector<Moomin> moomins;
    private static JSONParser parser = new JSONParser();
    private CommandsManager commandsManager;
    private long initTime;

    public MoominManager() {
        initTime = System.currentTimeMillis();
        moomins = new Vector<>();

        commandsManager = new CommandsManager();
        commandsManager.addCommand(//Добавление необходимых команд
                new Command("add", 1) {
                    @Override
                    public void execute() {
                        if (moomins.addAll(createMoomin(getArguments()))) {
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
                new Command("remove_lowerest", 1) {
                    @Override
                    public void execute(){
                        Moomin moomin = new Moomin(parseElement(getArguments()));
                        moomins.removeIf(obj -> {
                            if (obj.compareTo(moomin) <= 0) {
                                commandsManager.println(obj + " deleted");
                                return true;
                            }
                            commandsManager.println(obj + " not deleted");
                            return false;
                        });
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Delete all elements that lower than parameter.");
                    }
                },
                new Command("show", 0) {
                    @Override
                    public void execute() {
                        moomins.forEach(commandsManager::println);
                        if(moomins.size() == 0) {
                            commandsManager.println("Collection is empty.");
                        }
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Displays the contents of the vector.");
                    }
                },
                new Command("save", 0) {
                    @Override
                    public void execute() {
                        FileHandler.writeFile(moomins);
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Сохраняет коллекцию в файл save.json.");
                    }
                },
                new Command("clear", 0) {
                    @Override
                    public void execute() {
                        moomins.clear();
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Clear the collection.");
                    }
                },
                new Command("info", 0) {
                    @Override
                    public void execute() {
                        commandsManager.println("\nMoomin collections");
                        commandsManager.println("Collection type: " + moomins.getClass().getName());
                        commandsManager.println("Elements type: " + Moomin.class.getName());
                        commandsManager.println("Count of elements: " + moomins.size());
                        commandsManager.println("Initialization date: " + new Date(initTime) + '\n');
                    }
                    @Override
                    public void describe() {
                        commandsManager.println("Show info about collection.");
                    }
                },
                new Command("remove", 1) {
                    @Override
                    public void execute() {
                        if(moomins.remove(new Moomin(parseElement(getArguments())))) {
                            commandsManager.println("Successfuly deleted.");
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

    public Vector<Moomin> getMoomins() {
        return moomins;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

    public void setCommandsManager(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    public synchronized void setMoomins(Vector<Moomin> moomins) {
        this.moomins = moomins;
        initTime = System.currentTimeMillis();
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
            System.err.println(arguments);
            dataJSON = (JSONArray) parser.parse(arguments.trim());
        } catch (ParseException e) {
            e.printStackTrace();
            commandsManager.println("Data in file is not a correct JSON. Check the data in file.");
        }

        commandsManager.println("Data converting from JSON started.");
        dataJSON.forEach(obj -> vector.add(new Moomin((JSONObject) obj)));
        commandsManager.println("Data converting finished");
        return vector;
    }
}
