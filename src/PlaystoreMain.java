package src;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PlaystoreMain {
    public static void main(String[] args) throws IOException {
        String dirPath = args[0];
        String filename = args[1];
        if (args.length < 2) {
            System.err.println("Please input file path and file name");
            System.exit(-1);
        }
        File file = new File(dirPath + File.separator + filename);
    
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> appName = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        ArrayList<Double> rating = new ArrayList<>();
        //Getting rid of first line which are table headers
        br.readLine();
        
        while ((line = br.readLine()) != null) {
            String [] lineData = line.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
            //System.out.println(lineData[1]);
            appName.add(lineData[0]);
            category.add(lineData[1]);
            String ratingString = lineData[2].trim();
            if (ratingString.equals("NaN")) {
                ratingString = ratingString.replace("NaN", "0.0");
            }
            
            double ratingD = Double.parseDouble(ratingString);
            rating.add(ratingD);
        }

        Map <String, Map <String, Double>> categoryMap = new HashMap<>();
        Map <String, Double> appRatingMap = new HashMap<>();
        for (int i = 0; i < appName.size(); i++) {
            String categoryString = category.get(i);
            String name = appName.get(i);

            // System.out.printf("category: %s\n", category.get(i));
            // System.out.printf("App name: %s\n rating: %f", name, appRatingMap.get(name));
            if (!categoryMap.containsKey(categoryString)) {
                categoryMap.put(categoryString, new HashMap<String , Double>());
            }
            appRatingMap = categoryMap.get(categoryString);
            if (!appRatingMap.containsKey(appName)) {
                appRatingMap.put(name, rating.get(i));
            }
            
            
        }
        for (String c : categoryMap.keySet()) {
            System.out.println("Category: " + c);
            appRatingMap = categoryMap.get(c);
            String bestApp = getHighestRating(appRatingMap);
            String worstApp = getLowestRating(appRatingMap);
            System.out.println("Highest rated app: " + bestApp + " rated at " + appRatingMap.get(bestApp));
            System.out.println("Lowest rated app: " + worstApp + " rated at " + appRatingMap.get(worstApp));
            Double averageRating = getAverageRating(appRatingMap);
            System.out.println("Average rating: " + averageRating);            
        }
        
        System.out.println(appRatingMap.get("Anime Avatar Creator: Make Your Own Avatar"));
        

    }

    public static String getHighestRating(Map <String, Double> arm) {
        double ratings = 0.0;
        String bestApp = "";
        for (String a : arm.keySet()) {
            if (arm.get(a) > ratings) {
                ratings = arm.get(a);
                bestApp = a;
            }
        }
        return bestApp;    
    }

    public static String getLowestRating(Map <String, Double> arm) {
        double ratings = 5.0;
        String worstApp = "";
        for (String a : arm.keySet()) {
            if (arm.get(a) < ratings) {
                ratings = arm.get(a);
                worstApp = a;
            }
        }
        return worstApp;    
    }
    
    public static Double getAverageRating(Map <String, Double> arm) {
        double average = 0;
        double sum = 0;
        for (String a : arm.keySet()) {
            sum += arm.get(a);
            average = sum / arm.keySet().size();
        }
        return average;
    }
}