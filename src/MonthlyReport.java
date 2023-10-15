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

                monthlyString.item_name = str.split(",")[0];
                if (str.split(",")[1].equals("TRUE") ){
                    monthlyString.is_expense = true;
                } else {
                    monthlyString.is_expense = false;
                }

                monthlyString.quantity = Integer.parseInt(str.split(",")[2]);
                monthlyString.unit_price = Integer.parseInt(str.split(",")[2]);
                monthlyData.add(monthlyString);

            }
        }
    }

}
