/**
 */
package Class;

public class Parameter {

    String name;
    int val;

    public Parameter(String name, int val) {
        this.name = name;
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setNewValue(String val) {
        this.val = Integer.parseInt(val);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
