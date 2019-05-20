package commands;

import java.io.PrintStream;
import java.util.LinkedList;

public class CommandsManager {
    private LinkedList<Command> commands;
    private CommandsRecognizer recognizer;
    private boolean active;
    private PrintStream printStream;

    public CommandsManager(){
        printStream = System.out;
        commands = new LinkedList<>();
        addCommand(
                new Command("help",0) {
                    @Override
                    public void execute() {
                        println("All commands:");
                        for (Command command : commands) {
                            print(command);
                            print("\t\t");
                            command.describe();
                        }
                    }

                    @Override
                    public void describe() {
                        println("Get list of commands with description.");
                    }
                },
                new Command("NULL",-2) {
                    @Override
                    public void execute() {
                        println("Nothing happened");
                    }
                    @Override
                    public void describe() {
                        println("Do nothing.");
                    }
                });
        recognizer = new CommandsRecognizer(commands);
        active = true;
    }

    public void addCommand(Command... commandList) {
        for(Command command:commandList) {
            commands.add(command);
        }
    }

    public void print(Object o) {
        printStream.print(o);
    }

    public void println(Object o) {
        printStream.println(o) ;
    }

    public void println() {
        printStream.println() ;
    }


    public void doCommand(String command) {
        recognizer.recognizeCommand(command);
    }

    public void doCommand(CommandDescriptor descriptor) {
        recognizer.recognizeCommand(descriptor);
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
        recognizer.setPrintStream(printStream);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CommandsRecognizer getRecognizer() {
        return recognizer;
    }

    public void setRecognizer(CommandsRecognizer recognizer) {
        this.recognizer = recognizer;
    }

    public LinkedList<Command> getCommands() {
        return commands;
    }

    public void setCommands(LinkedList<Command> commands) {
        this.commands = commands;
    }
}
