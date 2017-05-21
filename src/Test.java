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
        io.outputln("Test "+ loadSurvey.getName()+" Loaded!");
        return loadSurvey;
    }

    public Response loadResponse() {
        IO io = new IO();

        File saves = new File("responses/");
        File[] allFiles = saves.listFiles();
        Vector<Integer> availableFiles = new Vector<Integer>();
        boolean foundResponses = false;

        io.outputln("Select a response to grade:");
        for (int i = 0; i < allFiles.length; i++) {
            String allFileName = allFiles[i].getName();
            if (allFileName.substring(allFileName.length() - 5).equals(".tstr") &&
                    allFileName.substring(allFileName.indexOf("_") + 1).equals(name.concat(".tstr"))) {
                io.outputln("[" + i + "]\t" + allFiles[i].getName());
                availableFiles.add(i);
                foundResponses = true;
            }
        }
        if (foundResponses) {
            while (true) {
                io.prompt();
                if (!io.getInput().matches("^-?\\d+$"))
                    io.outputln("Enter a number, silly");
                else if (!availableFiles.contains(io.getNumber()))
                    io.outputln("File not found :(\nEnter a number from the list above");
                else
                    break;
            }

            Response loadResponse = null;
            try {
                FileInputStream fileIn = new FileInputStream("responses/" + allFiles[io.getNumber()].getName());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                loadResponse = (Response) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return null;
            } catch (ClassNotFoundException c) {
                io.outputln("File not found :(");
                c.printStackTrace();
                return null;
            }
            return loadResponse;
        } else {
            io.outputln("No responses yet, take this test to create a response!");
            return null;
        }
    }
}
