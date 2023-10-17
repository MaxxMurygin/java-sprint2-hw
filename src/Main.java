public class Main {

    public static void main(String[] args) {
        UserMenu userChoice = new UserMenu();
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        ReportEngine reportEngine = new ReportEngine();
        while (true){
            userChoice.printMainMenu();
            int menuItem = userChoice.getUserChoice(0, 5);
            if (menuItem == 0){
                System.out.println("Удачи!");
                break;
            } else if (menuItem == 1) {
                monthlyReport.readMonthlyReports();
            } else if (menuItem == 2) {
                yearlyReport.readEarlyReports();
            } else if (menuItem == 3) {
                if (monthlyReport.monthlyDatas == null || yearlyReport.allYearData.isEmpty()){
                    System.out.println("Необходимые для сверки отчеты не загружены. Загрузить?");
                    userChoice.printYesNoMenu();
                    menuItem = userChoice.getUserChoice(1, 2);
                    if (menuItem == 1){
                        if (monthlyReport.monthlyDatas== null ){
                            monthlyReport.readMonthlyReports();
                        } else if (yearlyReport.allYearData.isEmpty()) {
                            yearlyReport.readEarlyReports();
                        }
                    } else {
                        System.out.println("На НЕТ и суда нет...");
                        continue;
                    }
                }
                //reportEngine.compareReports(monthlyReport.monthlyDatas, yearlyReport.allYearData);
            } else if (menuItem == 4) {
                if (monthlyReport.monthlyDatas == null){
                    System.out.println("Месячные отчеты не загружены. Загрузить?");
                    userChoice.printYesNoMenu();
                    menuItem = userChoice.getUserChoice(1, 2);
                    if (menuItem == 1){
                        monthlyReport.readMonthlyReports();
                    } else {
                        System.out.println("На НЕТ и суда нет...");
                        continue;
                    }
                }
                //reportEngine.printMonthlyReports(monthlyReport.monthlyDatas);
            } else if (menuItem == 5) {
                if (yearlyReport.allYearData.isEmpty()){
                    System.out.println("Годовые отчеты не загружены. Загрузить?");
                    userChoice.printYesNoMenu();
                    menuItem = userChoice.getUserChoice(1, 2);
                    if (menuItem == 1){
                        yearlyReport.readEarlyReports();
                    } else {
                        System.out.println("На НЕТ и суда нет...");
                        continue;
                    }
                }
                reportEngine.printYearlyReports(yearlyReport.allYearData);
            }
        }
    }
}

