package Class;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    public ArrayList<Parameter> users;

    public Attribute() {
        users = new ArrayList<Parameter>();
    }

    public void addParameter(String name, int val) {
        Parameter param = new Parameter(name, val);
        if (users.contains(param)) {
            int index = users.indexOf(param);
            users.get(index).setVal(val);
        } else {
            users.add(param);
        }
    }

    public ArrayList<Parameter> getParametersAll() {
        return users;
    }

    public List<Parameter> getUsers() {
        return users;
    }

}
