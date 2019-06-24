package MainClasses;

import MoominClasses.Moomin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Collections;
import java.util.Vector;

/**
 * Class that implement work of Application.
 */
public class MoominManager {
    /**
     * Collection of Moomins.
     */
    private Vector<Moomin> moomins;
    /**
     * Name of the file.
     */
    private String fileName;
    /**
     * JSON parser to parse and convert data.
     */
    private JSONParser parser = new JSONParser();
    /**
     * Command reader that implement reading the command.
     */
    private CommandReader cr;

    /**
     * Boolean flag: true - if we want to exit, false - if you don't want to exit.
     */
    private boolean pause;

    public MoominManager(String fileName) {
        this.fileName = fileName;
        pause = true;
        moomins = new Vector<>();
        cr = new CommandReader(this);
    }

    /**
     * Load data to moomins from file.
     *
     * @param fileName name of the file.
     */
    void loadDataFromFile(String fileName) {
        println("Loading data from file: " + fileName);
        moomins = new Vector<>();
        addDataFromFile(fileName);
        println("Data loading finished.");
    }

    /**
     * Add data to moomins from file.
     *
     * @param fileName name of the file.
     */
    void addDataFromFile(String fileName) {
        char[] fileContent = {};

        println("File reading started.");

        try {
            File inputFile = new File(fileName);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile));
            fileContent = new char[(int) inputFile.length()];
            isr.read(fileContent);
            isr.close();
            println("File reading finished.");
        } catch (IOException e) {
            println("Can't load data from file: " + fileName);
            println("File reading stopped.");
            exit();
            return;
        }

        String dataSTR = new String(fileContent);
        JSONArray dataJSON = null;
        try {
            dataJSON = (JSONArray) parser.parse(dataSTR);
        } catch (ParseException e) {
            println("Data in file is not a correct JSON. Check the data in file.");
            exit();
            return;
        }

        println("Data converting from JSON started.");
        dataJSON.forEach(obj -> addMoomin((JSONObject) obj));
        println("Data converting finished");
    }

    /**
     * Get vector moomins.
     *
     * @return vector moomins.
     */
    public Vector<Moomin> getMoomins() {
        return moomins;
    }

    /**
     * Get fileName.
     *
     * @return name of the file.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Save the data from the vector to the file.
     *
     * @param fileName name of the file where data should be saved.
     */
    void saveDataToFile(String fileName) {
        JSONArray JSONdata = new JSONArray();

        println("Data converting to JSON started.");

        int cnt = 0;

        for (Moomin moomin : moomins) {
            try {
                JSONdata.add(moomin.toJSON());
                println("Successfully convert data of moomin " + moomin + " to JSON.");
                cnt++;
            } catch (Exception e) {
                println("Can't convert data of moomin " + moomin + " to JSON.");
            }
        }

        println("Data of " + cnt + "/" + moomins.size() + " moomins successfully convert to JSON.");

        println("Data saving started.");

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            bos.write(JSONdata.toJSONString().getBytes());
            bos.close();
            println("Data saving finished.");
        } catch (Exception e) {
            println("Can't save data to file: " + fileName);
            println("Data saving stopped.");
        }
    }

    /**
     * Start the program implementation.
     */
    public void start() {
        pause = false;
        loadDataFromFile(fileName);

        while (!pause) {
            implementation();
        }

        cr.close();
        saveDataToFile(fileName);
        System.exit(0);
    }

    /**
     * Application exit function.
     */
    public void exit() {
        pause = true;
    }

    /**
     * Main part of iteration. Reading the command and its implementing.
     */
    private void implementation() {
        try {
            cr.readCommand();
        } catch (Exception e) {
            println("Unknown command!!!");
        }
    }

    /**
     * Add MoominClasses.Moomin to vector moomins.
     *
     * @param objJSON JSONObject with data about the moomin
     */
    private void addMoomin(JSONObject objJSON) {
        try {
            addMoomin(new Moomin(objJSON));
        } catch (Exception e) {
            println("Can't add this element: " + objJSON.toJSONString());
        }
    }

    /**
     * Add MoominClasses.Moomin to vector moomins.
     *
     * @param moomin Already created Moomin.
     */
    void addMoomin(Moomin moomin) {
        if (!moomins.contains(moomin)) {
            moomins.add(moomin);
            println(moomin + " successfully added.");
        } else {
            println("Element " + moomin + " already exist.");
        }
    }

    /**
     * Displays the contents of the vector
     */
    void show() {
        println("Current vector state:");
        moomins.forEach(moomin -> println(moomin.toString()));
    }

    /**
     * Print string.
     *
     * @param str String that should be printed.
     */
    public void print(String str) {
        System.out.print(str);
    }

    /**
     * Print string and move to the next line.
     *
     * @param str String that should be printed.
     */
    private void println(String str) {
        print(str);
        print("\n");
    }


    /**
     * Remove element from moomins by index.
     *
     * @param index index of removed element.
     */
    void remove(int index) {
        try {
            moomins.remove(index);
            println("Element at index " + index + " successfully removed.");
        } catch (Exception e) {
            println("Can't remove element at index " + index + ".");
        }
    }

    /**
     * Delete all elements that lower than parameter.
     *
     * @param moomin element Moomin.
     */
    void removeLower(Moomin moomin) {
        println("Deleting of elements lower than " + moomin + " started.");

        moomins.removeIf(obj -> obj.compareTo(moomin) <= 0);

        println("Deleting of elements lower than " + moomin + " finished.");
    }

    /**
     * Remove current element from moomins.
     *
     * @param moomin element that should be removed.
     */
    void remove(Moomin moomin) {
        try {
            moomins.remove(moomin);
            println("Moomin " + moomin + " successfully remove.");
        } catch (Exception e) {
            println("Can't remove moomin: " + moomin + ".");
        }
    }

    /**
     * Sort the moomins.
     */
    void sort() {
        Collections.sort(moomins);
        println("Moomins successfully sorted.");
    }

    /**
     * Show info about moomins.
     */
    void info() {
        println("Info about collection");
        print("Collection type: ");
        println(moomins.getClass().toString());
        print("Count of elements:");
        println(" " + moomins.size());
    }
}
