package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.senpaiexception.SenpaiException;

/**
 * Handle loading tasks from the storage file.
 */
public class Storage {
    private final File file;

    /**
     * Create a storage helper.
     *
     * @param filePath Path to save file.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Load all saved tasks.
     *
     * @return Array of saved task strings.
     * @throws SenpaiException If the file cannot be found.
     */
    public String[] load() throws SenpaiException {
        Scanner s = null;
        ArrayList<String> lines = new ArrayList<>();

        try {
            s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                lines.add(s.nextLine());
            }
            return lines.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            throw new SenpaiException("File Not Found");
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}
