import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {

    void printMonthlyReports(ArrayList<ArrayList<MonthlyData>> report){
        int biggestProfitProduct = 0;
        int biggestCost = 0;
        String profitProduct = "";
        String expensiveProduct = "";
        for (ArrayList<MonthlyData> marray : report){
            String monthName = Converter.monthNumberToString(report.indexOf(marray) + 1);
            System.out.printf("%n %s %n", monthName);
            for (MonthlyData m1array : marray){
                if (m1array.is_expense){
                    if (m1array.unit_price * m1array.quantity > biggestCost){
                        biggestCost = m1array.unit_price * m1array.quantity;
                        expensiveProduct = m1array.item_name;
                    }
                } else {
                    if (m1array.unit_price * m1array.quantity > biggestProfitProduct){
                        biggestProfitProduct = m1array.unit_price * m1array.quantity;
                        profitProduct = m1array.item_name;
                    }

                }
            }
            System.out.printf("Самый прибыльный продукт: %s на сумму %d %n", profitProduct, biggestProfitProduct);
            System.out.printf("Самый затратный продукт: %s на сумму %d %n", expensiveProduct, biggestCost);

        }
    }

    void printYearlyReports(HashMap<Integer, ArrayList<YearlyData>> report){
        int[] debtArray = new int[12];
        int[] creditArray = new int[12];
        int summaryDebt = 0;
        int summaryCredit = 0;
        int debtOperationCount = 0;
        int creditOperationCount = 0;
        for (Integer currentYear : report.keySet()){
            ArrayList<YearlyData> currentYearData = report.get(currentYear);
            for (YearlyData data: currentYearData){
                if (true){//data.is_expense
                    creditArray[data.month - 1] += data.amount;
                    creditOperationCount++;
                    summaryCredit += data.amount;
                }else {
                    debtArray[data.month - 1] += data.amount;
                    debtOperationCount++;
                    summaryDebt += data.amount;
                }
            }
            System.out.printf("Показатели за %d год : %n", currentYear);
            System.out.println("Прибыль по месяцам: ");
            for (int i = 0; i < debtArray.length; i++){
                if (debtArray[i] != 0){
                    System.out.printf("%s: %d %n", Converter.monthNumberToString(i + 1), debtArray[i]);
                }

            }
            System.out.printf("Средний приход по всем операциям: %d %n", summaryDebt / debtOperationCount);
            System.out.printf("Средний расход по всем операциям: %d %n", summaryCredit / creditOperationCount);
        }
    }

    void compareReports(ArrayList<ArrayList<MonthlyData>> monthlyReport,
                        HashMap<Integer, ArrayList<YearlyData>> yearlyReport){


    }
}
