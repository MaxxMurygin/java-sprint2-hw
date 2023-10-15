public class Main {

    public static void main(String[] args) {
        int menuItem;
        UserMenu userChoice = new UserMenu();
        MonthlyReport monthlyReport = new MonthlyReport();
        while (true){
            menuItem = userChoice.getUserChoice();
            //System.out.println(menuItem);
            if (menuItem == 0){
                System.out.println("Good luck!");
                break;
            } else if (menuItem == 1) {
                monthlyReport.readMonthlyReports();
            } else if (menuItem == 2) {
                //
            } else if (menuItem == 3) {
                //
            } else if (menuItem == 4) {
                //
            } else if (menuItem == 5) {
                //
            }
        }
    }
}

