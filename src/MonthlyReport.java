import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    ArrayList<MonthlyData> monthlyDatas = new ArrayList<>();
    FileReader fileReader;
    ArrayList<String> fileList;
    ArrayList<String> fileContent;

    void readMonthlyReports(){
        fileReader = new FileReader();
        fileList = new ArrayList<>();
        fileContent = new ArrayList<>();
        File dir = new File("./resources/");
        FilenameFilter filter = (f, name) -> name.startsWith("m.");
        File[] files = dir.listFiles(filter);
        if (files.length == 0){
            System.out.println("Не найдены файлы месячных отчетов...");
            return;
        }
        for (File file : files){
            int year = Integer.parseInt(file.getName().substring(2,6));
            int month = Integer.parseInt(file.getName().substring(6,8));
            fileContent = fileReader.readFileContents(file.getName());
            for (String str : fileContent){
                String[] splittedString = str.split(",");
                try {
                    String item_name = splittedString[0];
                    boolean is_expense = splittedString[1].equals("TRUE");
                    int quantity = Integer.parseInt(splittedString[2]);
                    int unit_price = Integer.parseInt(splittedString[3]);
                    MonthlyData monthlyData = new MonthlyData(year, month, item_name, is_expense, quantity, unit_price);
                    monthlyDatas.add(monthlyData);
                } catch (NumberFormatException n){
                }
            }
        }
    }
}
