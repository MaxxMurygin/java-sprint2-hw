import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {
    BestProduct[] maxAmount = new BestProduct[12];
    Integer[] amountArray = new Integer[13];
    HashMap<Boolean, BestProduct[]> productByAmount = new HashMap<>();
    HashMap<Integer, HashMap<Boolean,Integer[]>> amountByProductYear = new HashMap<>();
    HashMap<Boolean,Integer[]> amountByProductMonth = new HashMap<>();
    HashMap<Boolean,Integer[]> amountByMonth = new HashMap<>();
    HashMap<Integer, HashMap<Boolean,Integer[]>> amountByYear = new HashMap<>();
    static class BestProduct{
        String productName;
        int profit;

        public BestProduct(String productName, int profit) {
            this.productName = productName;
            this.profit = profit;
        }
    }

    void printMonthReports(ArrayList<MonthData> report){
        makeMonthData(report);
        for (int i = 1; i <= 12; i++) {
            if (productByAmount.get(true)[i] == null){
                continue;
            }
            System.out.printf("За %s месяц: %n", Converter.monthNumberToString(i));
            System.out.printf("Самый прибыльный продукт: %s на сумму %d %n", productByAmount.get(false)[i].productName,
                    productByAmount.get(false)[i].profit);
            System.out.printf("Самый затратный продукт: %s на сумму %d %n", productByAmount.get(true)[i].productName,
                    productByAmount.get(true)[i].profit);
        }
    }

    void printYearReports(ArrayList<YearData> report){
        makeYearData(report);
        for (Integer year : amountByYear.keySet()) {
            HashMap<Boolean,Integer[]> yearData;

            yearData = amountByYear.get(year);
            System.out.printf("Информация за %d год:%n", year);
            System.out.println();
            for (Boolean isExpense : yearData.keySet()) {
                if (isExpense){
                    continue;
                }
                Integer[] amounts = yearData.get(isExpense);
                for (int i = 1; i <= 12 ; i++) {
                    if (amounts[i] != null){
                        System.out.printf("Приыль за %s была %d %n", Converter.monthNumberToString(i), amounts[i]);
                    }
                }
            }
        }
    }

    void checkReports(ArrayList<MonthData> monthReport, ArrayList<YearData> yearReport){
        makeYearData(yearReport);
        //makeMonthData(monthReport);
        for (MonthData data : monthReport) {
            Integer[] monthlyAmount;
            int year = data.year;
            int month = data.month;
            //String productName = data.item_name;
            Boolean isExpense = data.is_expense;
            int quantity = data.quantity;
            int price = data.unit_price;
            int amount = quantity * price;

            monthlyAmount = amountByProductMonth.get(isExpense);
            monthlyAmount[month] +=  amount;
            amountByProductMonth.put(isExpense,monthlyAmount);



        }

        for (Integer year : amountByYear.keySet()) {
            amountByMonth = amountByYear.get(year);

        }




    }

    void makeMonthData(ArrayList<MonthData> report){
        for (MonthData data : report){
            int month = data.month;
            String productName = data.item_name;
            Boolean isExpense = data.is_expense;
            int quantity = data.quantity;
            int price = data.unit_price;
            int amount = quantity * price;
            BestProduct product = new BestProduct(productName, amount);
            if (!productByAmount.containsKey(isExpense)){
                maxAmount = new BestProduct[13];
                maxAmount[month] = product;
                productByAmount.put(isExpense, maxAmount);
                continue;
            }
            if (productByAmount.get(isExpense)[month] == null){
                maxAmount = new BestProduct[13];
                maxAmount = productByAmount.get(isExpense);
                maxAmount[month] = product;
                productByAmount.put(isExpense, maxAmount);
                continue;
            }
            if (product.profit > productByAmount.get(isExpense)[month].profit){
                maxAmount = new BestProduct[13];
                maxAmount = productByAmount.get(isExpense);
                maxAmount[month] = product;
                productByAmount.put(isExpense, maxAmount);
            }
        }
    }

    void makeYearData(ArrayList<YearData> report){
        for (YearData data : report) {
            int year = data.year;
            int month = data.month;
            int amount = data.amount;
            boolean isExpense = data.is_expense;

            if (!amountByMonth.containsKey(isExpense)){
                amountArray = new Integer[13];
                amountArray[month] = amount;
                amountByMonth.put(isExpense, amountArray);
                continue;
            }
            if (amountByMonth.get(isExpense)[month] == null){
                amountArray = new Integer[13];
                amountArray = amountByMonth.get(isExpense);
                amountArray[month] = amount;
                amountByMonth.put(isExpense, amountArray);
            }
            amountByMonth.put(isExpense,amountArray);
            if (!amountByYear.containsKey(year)){
                amountByYear.put(year, amountByMonth);
                continue;
            }
            amountByYear.put(year, amountByMonth);
        }


    }

}
