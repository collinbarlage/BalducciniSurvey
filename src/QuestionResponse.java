import java.util.Vector;

public class QuestionResponse implements java.io.Serializable {
    IO io = new IO();
    private Vector<String> responses = new Vector<>();

    public QuestionResponse(String firstResponse) {
        responses.add(firstResponse);
    }

    public QuestionResponse() {
    }

    public void add(String s) {
        responses.add(s);
    }

    public String getOne() {
        if(responses.size() == 1)
            return responses.elementAt(0);
        else
            return null;
    }

    public Vector<String> getResponses(){
        return responses;
    }
}
