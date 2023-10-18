import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class YearReport {
    ArrayList<YearData> yearData = new ArrayList<>();
    FileReader fileReader;
    ArrayList<String> fileList;
    ArrayList<String> fileContent;
    void readEarlyReports(){
        fileReader = new FileReader();
        fileList = new ArrayList<>();
        fileContent = new ArrayList<>();
        File dir = new File("./resources/");
        FilenameFilter filter = (f, name) -> name.startsWith("y.");
        File[] files = dir.listFiles(filter);
        if (files.length == 0){
            System.out.println("Не найдены файлы годовых отчетов...");
            return;
        }
        if (!yearData.isEmpty()){
            yearData.clear();
        }
        for (File file : files){
            fileContent = fileReader.readFileContents(file.getName());
            int year = Integer.parseInt(file.getName().substring(2,6));
            for (String str : fileContent){
                String[] splittedString = str.split(",");
                try {
                    int month = Integer.parseInt(splittedString[0]);
                    int amount = Integer.parseInt(splittedString[1]);
                    boolean is_expense = splittedString[2].equals("true");
                    YearData dataString = new YearData(year, month, amount, is_expense);
                    yearData.add(dataString);
                } catch (NumberFormatException n){
                }
            }
        }
    }
}
