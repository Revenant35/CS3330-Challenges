
package zcbmkwfinalprojectf21;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class SaveToFile {
    
    static public boolean saveToFile(String filePath, ArrayList<Book> contents){
        try {
            try (FileWriter writer = new FileWriter(filePath)) {
                for (Book book : contents) {
                    writer.write("\"" + book.getTitle() + "\" \"" + book.getAuthor() + "\" \"" + book.getDate() + "\"\n");
                }
            }
            return true;
        } catch (IOException e){
            System.err.println(e);
            return false;
        }
    }
}