package duke;

import duke.SenpaiException.SenpaiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handle loading tasks from the storage file.
 */
public class Storage {
    /**
     * Create a storage helper.
     *
     * @param filePath Path to save file.
     */
    public Storage (String filePath) {
        f = new File(filePath);
    }
    /**
     * Load all saved tasks.
     *
     * @return Array of saved task strings.
     * @throws SenpaiException If the file cannot be found.
     */
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
