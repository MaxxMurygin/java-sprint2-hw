public class Main {
    public static void main(String[] args) {
        int menuItem;
        UserMenu userChoice = new UserMenu();
        MonthReport monthReport = new MonthReport();
        YearReport yearReport = new YearReport();
        ReportEngine reportEngine = new ReportEngine();
        while (true){
            userChoice.printMainMenu();
            menuItem = userChoice.getUserChoice(0, 5);
            if (menuItem == 0){
                System.out.println("Удачи!");
                break;

            } else if (menuItem == 1) {
                monthReport.readMonthlyReports();

            } else if (menuItem == 2) {
                if (!yearReport.yearData.isEmpty()){
                    yearReport.yearData.clear();
                }
                yearReport.readEarlyReports();

            } else if (menuItem == 3) {
                if (monthReport.monthData.isEmpty() || yearReport.yearData.isEmpty()){
                    System.out.println("Необходимые для сверки отчеты не загружены. Загрузить?");
                    userChoice.printYesNoMenu();
                    menuItem = userChoice.getUserChoice(1, 2);
                    if (menuItem == 1){
                        if (monthReport.monthData.isEmpty()){
                            monthReport.readMonthlyReports();
                        }
                        if (yearReport.yearData.isEmpty()) {
                            yearReport.readEarlyReports();
                        }
                    } else {
                        System.out.println("На НЕТ и суда нет...");
                        continue;
                    }
                }
                reportEngine.checkReports(monthReport.monthData, yearReport.yearData);

            } else if (menuItem == 4) {
                if (monthReport.monthData.isEmpty()){
                    System.out.println("Месячные отчеты не загружены. Загрузить?");
                    userChoice.printYesNoMenu();
                    menuItem = userChoice.getUserChoice(1, 2);
                    if (menuItem == 1){
                        monthReport.readMonthlyReports();
                    } else {
                        System.out.println("На НЕТ и суда нет...");
                        continue;
                    }
                }
                reportEngine.printMonthReports(monthReport.monthData);

            } else if (menuItem == 5) {
                if (yearReport.yearData.isEmpty()){
                    System.out.println("Годовые отчеты не загружены. Загрузить?");
                    userChoice.printYesNoMenu();
                    menuItem = userChoice.getUserChoice(1, 2);
                    if (menuItem == 1){
                        yearReport.readEarlyReports();
                    } else {
                        System.out.println("На НЕТ и суда нет...");
                        continue;
                    }
                }
                reportEngine.printYearReports(yearReport.yearData);
            }
        }
    }
}

