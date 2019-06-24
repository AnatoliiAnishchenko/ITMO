package commands;

import moominClasses.Moomin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.util.Vector;

public class FileHandler {
    private static JSONParser parser = new JSONParser();
    private static String fileName = "save.json";

    public static Vector<Moomin> readFile(String fileName) {
        Vector<Moomin> vector = new Vector<>();

        char[] fileContent = {};

        try {
            File inputFile = new File(fileName);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile));
            fileContent = new char[(int) inputFile.length()];
            isr.read(fileContent);
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dataSTR = new String(fileContent);
        JSONArray dataJSON = null;
        try {
            dataJSON = (JSONArray) parser.parse(dataSTR);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dataJSON.forEach(obj -> vector.add(new Moomin((JSONObject) obj)));

        return vector;
    }

    public static void writeFile(Vector<Moomin> moomins) {
        JSONArray JSONdata = new JSONArray();
        for (Moomin moomin : moomins) {
            try {
                JSONdata.add(moomin.toJSON());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            bos.write(JSONdata.toJSONString().getBytes());
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}