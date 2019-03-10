package MoominClasses;

import org.json.simple.JSONObject;

import java.util.Objects;

public abstract class MyObject implements MainClasses.Printable, Saveable {
    private String name;

    public MyObject() {
        name = null;
    }

    public MyObject(String name) {
        this.name = name;
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
        result.put("className", "MoominClasses.MyObject");
        result.put("data", curObjectJSONData);

        return result;
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