package ku.cs.services;

import java.io.File;
import java.io.IOException;

public class InitialFileIfNotExist {
    static public void initialFileIfNotExist(String directory, String filename){
        File file = new File(directory); // set directory path
        if (!file.exists()) file.mkdir(); // if file not exist will make new directory
        file = new File(directory + File.separator + filename); // set file path
        if (!file.exists()) { // if file not exist in path
            try {
                file.createNewFile(); // make new file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
