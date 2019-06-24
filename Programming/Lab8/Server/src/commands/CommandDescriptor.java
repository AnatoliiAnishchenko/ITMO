package commands;

import java.io.Serializable;
import java.util.StringTokenizer;

public class CommandDescriptor implements Serializable {
    private final String NAME;
    private int ARGS_COUNT;
    private String arguments;

    public CommandDescriptor(String command) {
        arguments = "";

        StringTokenizer tokenizer = new StringTokenizer(command);
        int wordsCount = tokenizer.countTokens();

        if (wordsCount > 0) {
            NAME = tokenizer.nextToken();
            ARGS_COUNT = wordsCount - 1;
            if (ARGS_COUNT != 0) {
                arguments = command.substring(NAME.length()).trim();
            }
        } else {
            NAME = "NULL";
            ARGS_COUNT = -2;
        }
    }

    public String getNAME() {
        return NAME;
    }

    public int getARGS_COUNT() {
        return ARGS_COUNT;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public void setARGS_COUNT(int ARGS_COUNT) {
        this.ARGS_COUNT = ARGS_COUNT;
    }
}
