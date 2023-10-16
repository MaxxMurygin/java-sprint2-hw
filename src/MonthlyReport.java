import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<ArrayList<MonthlyData>> monthlyData;
    FileReader fileReader;
    ArrayList<String> fileList;
    ArrayList<String> fileContent;
    ArrayList<MonthlyData> currentData;

    void readMonthlyReports(){
        fileReader = new FileReader();
        fileList = new ArrayList<>();
        fileContent = new ArrayList<>();
        monthlyData = new ArrayList<>(12);
        currentData = new ArrayList<>();
        File dir = new File("./resources/");
        FilenameFilter filter = (f, name) -> name.startsWith("m.");
        File[] files = dir.listFiles(filter);
        if (files.length == 0){
            System.out.println("Не найдены файлы месячных отчетов...");
            return;
        }
        for (File file : files){
            currentData = new ArrayList<>();
            //int currentYear = Integer.parseInt(file.getName().substring(2,6));
            int currentMonth = Integer.parseInt(file.getName().substring(6,8));
            fileContent = fileReader.readFileContents(file.getName());
            for (String str : fileContent){
                String[] splittedString = str.split(",");
                MonthlyData monthlyString = new MonthlyData();
                try {
                    monthlyString.item_name = splittedString[0];
                    monthlyString.is_expense = splittedString[1].equals("TRUE");
                    monthlyString.quantity = Integer.parseInt(splittedString[2]);
                    monthlyString.unit_price = Integer.parseInt(splittedString[3]);
                } catch (NumberFormatException n){
                    continue;
                }
                currentData.add(monthlyString);
            }
            monthlyData.add(currentMonth - 1, currentData);
        }
    }
}
