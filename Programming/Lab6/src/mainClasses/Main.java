package mainClasses;

import commands.CommandsManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Good day!");
        MoominManager manager = new MoominManager();
        CommandsManager commandsManager = manager.getCommandsManager();

        Scanner scanner = new Scanner(System.in);

        String pathToFile = "save.json";

        commandsManager.doCommand("import " + pathToFile);

        while (commandsManager.isActive()){
            System.out.print("Your command: ");
            synchronized (manager) {
                commandsManager.doCommand(scanner.nextLine());
            }
        }
        commandsManager.doCommand("save");
    }
}
