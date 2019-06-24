package moominClasses;

import org.json.simple.JSONObject;
import server.Printable;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public abstract class MyObject implements Printable, Saveable, Serializable {
    private String name;
    private ZonedDateTime creationTime;

    public MyObject() {
        name = null;
        creationTime = ZonedDateTime.now();
    }

    public MyObject(String name) {
        this.name = name;
        this.creationTime = ZonedDateTime.now();
    }

    public MyObject(ZonedDateTime time, String name) {
        this.name = name;
        this.creationTime = time;
    }

    public void setCreationTime(ZonedDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJSON () {
        JSONObject result = new JSONObject();
        JSONObject curObjectJSONData = new JSONObject();

        curObjectJSONData.put("name", getName());
        result.put("className", "moominClasses.MyObject");
        result.put("data", curObjectJSONData);

        return result;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof MyObject)) return false;
        MyObject myObject = (MyObject) that;
        return Objects.equals(name, myObject.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}