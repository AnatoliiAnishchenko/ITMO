package MainClasses;

import MoominClasses.Moomin;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class CommandReader {
    private Scanner commandIn;
    private MoominManager manager;

    public CommandReader(MoominManager manager) {
        this.manager = manager;
        commandIn = new Scanner(System.in);
    }

    public void readCommand() {
        String commandName = commandIn.next();
        Command command = parseCommand(commandName);
        command.execute();
    }

    public void close() {
        commandIn.close();
    }

    private Command parseCommand(String str) {
        switch (str) {
            case "remove_last":
                return new RemoveLastCommand(manager);
            case "remove": {
                Moomin moomin = new Moomin(parseElement());
                return new RemoveCommand(manager, moomin);
            }
            case "show":
                return new ShowCommand(manager);
            case "remove_first":
                return new RemoveFirstCommand(manager);
            case "remove_lower": {
                Moomin moomin = new Moomin(parseElement());
                return new RemoveLowerCommand(manager, moomin);
            }
            case "sort":
                return new SortCommand(manager);
            case "info":
                return new InfoCommand(manager);
            case "add": {
                Moomin moomin = new Moomin(parseElement());
                return new AddCommand(manager, moomin);
            }
            case "save":
                return new SaveCommand(manager);
            case "load":
                return new LoadCommand(manager);
            case "save_to_file": {
                String fileName = commandIn.nextLine().trim();
                return new SaveCommand(manager, fileName);
            }
            case "load_from_file": {
                String fileName = commandIn.nextLine().trim();
                return new LoadCommand(manager, fileName);
            }
            case "exit":
                return new ExitCommand(manager);
            case "add_from_file": {
                String fileName = commandIn.nextLine().trim();
                return new AddFromFileCommand(manager, fileName);
            }
            default: {
                throw new IllegalArgumentException("Unknown command");
            }
        }
    }

    private JSONObject parseElement() {
        String dataStr = commandIn.nextLine();
        JSONParser parser = new JSONParser();
        JSONObject dataJSON = null;
        try {
            dataJSON = (JSONObject) parser.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataJSON;
    }
}

