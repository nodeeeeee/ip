import SenpaiException.SenpaiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public Storage (String filePath) {
        f = new File(filePath);
    }
    public String[] load() throws SenpaiException {
        Scanner s = null;
        File f;
        ArrayList<String> lines = new ArrayList<>();
        f = new File("savedTasks.txt"); // create a File for the given file path
        try {
            s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                lines.add(s.nextLine());
            }
            String[] ret = lines.toArray(new String[0]);
            return ret;
        }
        catch (FileNotFoundException e) {
            throw new SenpaiException("File Not Found");
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
    final private File f;
}
