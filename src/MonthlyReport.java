import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<MonthlyData> monthlyData;
    FileReader fileReader;
    ArrayList<String> fileList;
    ArrayList<String> fileContent;
    void readMonthlyReports(){
        String contentString;
        String[] spltStr;
        MonthlyData monthlyString = new MonthlyData();

        fileReader = new FileReader();
        fileList = new ArrayList<>();
        fileContent = new ArrayList<>();
        monthlyData = new ArrayList<>();
        fileList.add("m.202101.csv");
        fileList.add("m.202102.csv");
        fileList.add("m.202103.csv");

        for (String file : fileList){
            fileContent = fileReader.readFileContents(file);
            for (String str : fileContent){
                spltStr = str.split(",");
                System.out.println(spltStr);
                monthlyString.item_name = spltStr[0];
                monthlyString.is_expense = true;
                monthlyString.quantity = Integer.parseInt(spltStr[2]);
                monthlyString.unit_price = Integer.parseInt(spltStr[2]);
                monthlyData.add(monthlyString);

            }
        }
    }

}
