package src;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextReaderMain {
    public static void main(String[] args) throws IOException {
        String dirPath = args[0];
        String filename = args[1];

        if (args.length < 2) {
            System.err.println("Please input filepath and filename");
            System.exit(-1);
        }

        File dirPathfilename = new File(dirPath + File.separator + filename);
        TextReader tr = new TextReader();
        ArrayList<String> words = tr.readText(dirPathfilename); 
        System.out.println(words);

        Map <String, Map <String, Integer>> wordSequence = new HashMap<>();
        String currentW;
        String nextW;
        int count = 0;
        Map <String, Integer> wordDistribution = new HashMap<>();
        for (int i = 0; i < words.size() -1; i++) {
            currentW = words.get(i);
            nextW = words.get(i + 1);
            if (!wordSequence.containsKey(currentW)) {
                wordSequence.put(currentW, new HashMap <String, Integer>());  
            }
            wordDistribution = wordSequence.get(currentW);
           
            if (!wordDistribution.containsKey(nextW)) {
                wordDistribution.put(nextW, count);
            }
            count = wordDistribution.get(nextW);
            count++;
            wordDistribution.put(nextW, count);

        System.out.printf("Current words: %s , Next word: %s, count: %d\n", currentW, nextW, count);
            
        }


        
    }
}