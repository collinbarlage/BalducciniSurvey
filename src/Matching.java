import java.util.Collections;
import java.util.Vector;

public class Matching extends Question implements java.io.Serializable {
    private Vector<String> choicesA = new Vector<String>();
    private Vector<String> choicesB = new Vector<String>();
    private Vector<String> displayA = new Vector<String>();
    private Vector<String> displayB = new Vector<String>();

    public Matching(boolean isSurvey) {
        io.prompt("\tEnter the question prompt:");
        this.setPrompt(io.getInput());

        io.prompt("\tEnter first list of choices: (enter nothing when finished)");
        while (!io.getInput().equals("")) {
            choicesA.add(io.getInput());
            io.prompt();
        }

        io.prompt("\tEnter corresponding list of answers:");
        for (int i = 1; i < choicesA.size(); i++) {
            choicesB.add(io.getInput());
            io.prompt();
        }
        choicesB.add(io.getInput());

        displayA.addAll(choicesA);
        displayB.addAll(choicesB);
        Collections.shuffle(displayA);
        Collections.shuffle(displayB);
    }

    public void modify() {
        for (int i = 0; i < choicesA.size(); i++) {
            io.prompt("Change item in 1st list '" + choicesA.elementAt(i) + "': (enter nothing to skip)");
            if (!io.getInput().equals(""))
                choicesA.set(i, io.getInput());
        }
        for (int i = 0; i < choicesB.size(); i++) {
            io.prompt("Change item in 2nd list '" + choicesB.elementAt(i) + "': (enter nothing to skip)");
            if (!io.getInput().equals(""))
                choicesB.set(i, io.getInput());
        }
        //reset all choices
        displayA = new Vector<>();
        displayB = new Vector<>();
        displayA.addAll(choicesA);
        displayB.addAll(choicesB);
        Collections.shuffle(displayA);
        Collections.shuffle(displayB);
    }

    public void display() {
        io.outputln("Matching:\n\t" + this.getPrompt());
        for (int i = 0; i < displayA.size(); i++) {
            io.outputColumns("\t\t(" + toLetter(i) + ") " + displayA.elementAt(i), "(" + i + ") " + displayB.elementAt(i));
        }
        io.output("\n");
    }

    public void take(Response res) {
        QuestionResponse userResponse = new QuestionResponse();
        for (int i = 0; i < choicesA.size(); i++) {
            io.prompt("Enter number for "+toLetter(i));
            userResponse.add(displayB.elementAt(io.getNumber()));
        }

        res.addResponse(userResponse);
    }

    public Boolean isCorrect(Vector<String> user) {
        for(int i=0; i<user.size(); i++) {
            if(!choicesB.contains(user.elementAt(i)))
                return false;
        }
        return (user.size() == choicesB.size());
    }

    public void tabulate(Vector<Response> responses, int q) {
        int correctCount = 0;
        Vector<Vector<String>> otherResponses = new Vector<Vector<String>>();
        for (int i = 0; i < responses.size(); i++) {
            boolean wasCorrect = false;
            if (choicesB.containsAll(responses.elementAt(i).getQuestionResponse(q).getResponses())) {
                correctCount++;
                wasCorrect = true;
            }
            if(!wasCorrect)
                otherResponses.add(responses.elementAt(i).getQuestionResponse(q).getResponses());
        }
        io.output("\t\t");
        for (int i = 0; i < choicesB.size(); i++)
            io.output(    choicesB.elementAt(i) + ", ");
        io.output("\t"+correctCount+"\n");

        io.output("\t\tOther responses:\n\t\t\t");
        for(Vector<String> ss: otherResponses) {
            for(String s: ss)
                io.output(s+", ");
            io.output("\n\t\t\t");
        }
    }

}
