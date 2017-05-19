import java.io.Serializable;
import java.util.Vector;

public class Response implements java.io.Serializable {
    private Vector<String> responses = new Vector<>();
    private String name;

    public Response(String n) {
        name = n;
    }

    public String getString() {
        return responses.elementAt(0);
    }

    public String getString(int i) {
        return responses.elementAt(i);
    }

    public void addResponse(String s) {
        responses.add(s);
    }

    public boolean checkOne(String correct) {
        return (this.getString().equals(correct));
    }

    public boolean checkMany(Vector<String> correct) {
        for (int i = 0; i < correct.size(); i++) { //loop through correct answers and check if they are in responses
            if (!responses.contains(correct.elementAt(i)))
                return false;
        }
        return (responses.size() == correct.size()); //double check that both vectors are identical
    }

    public boolean checkOrder(Vector<String> correct) {
        for (int i = 0; i < correct.size(); i++) {
            if (!responses.elementAt(i).equals(correct.elementAt(i)))
                return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }
}
