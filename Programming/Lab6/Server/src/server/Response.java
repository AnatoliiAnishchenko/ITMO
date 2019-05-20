package server;

import moominClasses.Moomin;

import java.io.Serializable;
import java.util.Vector;
import java.util.stream.Collectors;


public class Response implements Serializable {

    private String doings;
    private Vector<Moomin> moomins;

    public Response(String doings, Vector<Moomin> moomins){
        this.doings = doings;
        setMoomins(moomins);
    }

    public String getDoings() {
        return doings;
    }

    public void setDoings(String doings) {
        this.doings = doings;
    }

    public Vector<Moomin> getMoomins() {
        return moomins;
    }

    public void setMoomins(Vector<Moomin> moomins) {
        this.moomins = moomins.stream()
                              .sorted()
                              .collect(Collectors.toCollection(Vector::new));
    }
}