import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {
    BestProduct[] maxAmount = new BestProduct[13];
    Integer[] amountArray = new Integer[13];
    Integer[] amountArrayFromMonthly = new Integer[13];
    HashMap<Boolean, BestProduct[]> productByAmount = new HashMap<>();
    HashMap<Integer, HashMap<Boolean,Integer[]>> amountByYearFromMonthly = new HashMap<>();
    HashMap<Boolean,Integer[]> amountByMonthFromMonthly = new HashMap<>();
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

    void printMonthReports(ArrayList<MonthData> data){
        makeMonthData(data);
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

    void printYearReports(ArrayList<YearData> data){
        makeYearData(data);
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

    void checkReports(ArrayList<MonthData> monthData, ArrayList<YearData> yearData){
        HashMap<Boolean,Integer[]> reportYear;
        HashMap<Boolean,Integer[]> reportMonth;
        Integer[] amountsFromYear;
        Integer[] amountsFromMonth;
        int errorCount = 0;

        makeYearData(yearData);
        makeMonthData(monthData);
        for (Integer year : amountByYear.keySet()) {
            reportYear = amountByYear.get(year);
            if (!amountByYearFromMonthly.containsKey(year)){
                System.out.printf("Нет данных из месячных отчетов за %d год. ", year);
                continue;
            }
            reportMonth = amountByYearFromMonthly.get(year);
            for (Boolean isExpense : reportYear.keySet()) {
                amountsFromYear = reportYear.get(isExpense);
                amountsFromMonth = reportMonth.get(isExpense);
                for (int i = 1; i <= 12; i++) {
                    if (amountsFromYear[i] == null || amountsFromMonth[i] == null){
                        continue;
                    }
                    int comparator = amountsFromYear[i].compareTo(amountsFromMonth[i]);
                    if ( comparator < 0){
                        System.out.printf("Данные  по %s в месячном отчете за %s %d года больше, чем годовом отчете%n",
                                Converter.booleanToExpense(isExpense), Converter.monthNumberToString(i), year);
                        errorCount++;
                    } else if (comparator > 0) {
                        System.out.printf("Данные  по %s в месячном отчете за %s %d года меньше, чем годовом отчете%n",
                                Converter.booleanToExpense(isExpense), Converter.monthNumberToString(i), year);
                        errorCount++;
                    }
                }
            }
        }
        if (errorCount == 0){
            System.out.println("Проверка прошла успешно");
        }
    }

    void makeMonthData(ArrayList<MonthData> report){
        if (!amountByMonthFromMonthly.isEmpty()){
            amountByMonthFromMonthly.clear();
        }
        for (MonthData data : report){
            int year = data.year;
            int month = data.month;
            String productName = data.item_name;
            Boolean isExpense = data.is_expense;
            int quantity = data.quantity;
            int price = data.unit_price;
            int amount = quantity * price;
            BestProduct product = new BestProduct(productName, amount);
            amountArrayFromMonthly = new Integer[13];

            if (!amountByYearFromMonthly.containsKey(year)){
                if (!amountByMonthFromMonthly.isEmpty()){
                    amountByMonthFromMonthly.clear();
                }
            }
            if (!amountByMonthFromMonthly.containsKey(isExpense)){          //ФОрмируем данные для сверки
                amountArrayFromMonthly[month] = amount;
                amountByMonthFromMonthly.put(isExpense, amountArrayFromMonthly);
            }else {
                if (amountByMonthFromMonthly.get(isExpense)[month] == null){
                    amountArrayFromMonthly = amountByMonthFromMonthly.get(isExpense);
                    amountArrayFromMonthly[month] = amount;
                    amountByMonthFromMonthly.put(isExpense, amountArrayFromMonthly);
                }else {
                    amountArrayFromMonthly = amountByMonthFromMonthly.get(isExpense);
                    amountArrayFromMonthly[month] += amount;
                    amountByMonthFromMonthly.put(isExpense, amountArrayFromMonthly);
                }
            }
            amountByYearFromMonthly.put(year,amountByMonthFromMonthly);

            if (!productByAmount.containsKey(isExpense)){                   //Формируем данные для отчета
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
        if(!amountByMonth.isEmpty()){
            amountByMonth.clear();
        }
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
            amountByYear.put(year, amountByMonth);
        }
    }
}
