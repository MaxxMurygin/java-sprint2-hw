import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    HashMap<Integer, HashMap<Integer, HashMap<Boolean,Integer>>> dataByYear = new HashMap<>();
    ArrayList<YearlyData> currentYearData;
    HashMap<Integer, ArrayList<YearlyData>> allYearData = new HashMap<>();
    FileReader fileReader;
    ArrayList<String> fileList;
    ArrayList<String> fileContent;
    void readEarlyReports(){
        fileReader = new FileReader();
        fileList = new ArrayList<>();
        fileContent = new ArrayList<>();
        currentYearData = new ArrayList<>();
        File dir = new File("./resources/");
        FilenameFilter filter = (f, name) -> name.startsWith("y.");
        File[] files = dir.listFiles(filter);
        if (files.length == 0){
            System.out.println("Не найдены файлы годовых отчетов...");
            return;
        }
        for (File file : files){
            fileContent = fileReader.readFileContents(file.getName());
            int currentYear = Integer.parseInt(file.getName().substring(2,6));
            for (String str : fileContent){


                String[] splittedString = str.split(",");
                try {
                    int month = Integer.parseInt(splittedString[0]);
                    int amount = Integer.parseInt(splittedString[1]);
                    boolean is_expense = splittedString[2].equals("true");
                    YearlyData yearlyString = new YearlyData(month, amount);
                    currentYearData.add(yearlyString);
                } catch (NumberFormatException n){
                    continue;
                }


            }
            allYearData.put(currentYear,currentYearData);
        }
    }
}
