import java.io.*;
import java.util.Vector;

public class Test implements java.io.Serializable {
    IO io = new IO();
    private String name;
    private boolean gradeable;
    private Vector<Question> questions = new Vector<Question>();

    public Test() {
        //Default for loading
    }

    public Test(String surveyName) {
        name = surveyName;
        gradeable = true; //default value for a test
    }

    public void makeTest() {
        while(true) {

            io.prompt("Add a question:\n[1]\tTrue/False\n[2]\tMultiple Choice\n[3]\tShort Answer" +
                    "\n[4]\tEssay\n[5]\tRanking\n[6]\tMatching\n[7]\tDone");
            switch (io.response()) {
                case "1": //TrueFalse
                    break;

                case "2": //Multiple Choice
                    MultipleChoice newMc = new MultipleChoice();
                    newMc.makeQuestion(); //Prompt user for question
                    this.addQuestion(newMc); //Add this question to test/survey
                    break;

                case "3": //Short Answer
                    break;

                case "4": //Essay
                    break;

                case "5": //Ranking
                    break;

                case "6": //Matching
                    break;

                case "7": //exit
                    break;

                default:
                    break;

            }
            if(io.response().equals("7"))
                break;
        }
    }

    public void display() {
        io.outputln("\n\n"+this.getType()+" "+this.getName()+"\n");
        for(int i=0; i<questions.size(); i++)
            questions.elementAt(i).display();
    }

    public void save() {
        try {
            FileOutputStream file = new FileOutputStream("saves/"+name+".tst");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
            io.outputln("Saved "+getType()+" "+name+" in /saves/"+name+".tst");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public static Test load(String fileName) {
        IO io = new IO();
        io.outputln("Loading...");
        Test loadTest = null;
        try {
            FileInputStream fileIn = new FileInputStream("saves/"+fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadTest = (Test) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c) {
            io.outputln("File not found :(");
            c.printStackTrace();
            return null;
        }
        io.outputln(loadTest.getType()+" "+loadTest.getName()+" Loaded!");
        return loadTest;
    }


    public void addQuestion(Question q) {
        questions.add(q);
    }

    public Vector<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public void typeSurvey() {
        gradeable = false;
    }

    public String getType() {
        if(gradeable)
            return "Survey";
        else
            return "Test";
    }

    public boolean isTest() {
        return gradeable;
    }

}
