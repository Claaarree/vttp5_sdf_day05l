package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextReader {

    public ArrayList<String> readText(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        ArrayList<String> words = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            line = line.replaceAll("\\p{Punct}", "");
            String [] wordArray = line.replaceAll(" +", " ").split(" ");
            for (int i = 0; i < wordArray.length; i++) {
                words.add(wordArray[i]);
            }

        }
        br.close();
        fr.close();
        return words;      
        
    }  
       
    
}
