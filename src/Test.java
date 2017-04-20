import java.io.*;
import java.util.Vector;

public class Test extends Survey implements java.io.Serializable {

    public Test() {
        isSurvey = false;
        extention = ".tst";
        type = "Test";
        //Default for loading
    }

    public Test(String testName) {
        isSurvey = false;
        extention = ".tst";
        type = "Test";
        name = testName;
    }

    public static Test load(String fileName) {
        IO io = new IO();
        io.outputln("Loading...");
        Test loadSurvey = null;
        try {
            FileInputStream fileIn = new FileInputStream("saves/"+fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadSurvey = (Test) in.readObject();
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
        io.outputln("Survey "+ loadSurvey.getName()+" Loaded!");
        return loadSurvey;
    }
}
