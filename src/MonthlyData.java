public class MonthlyData {
    int year;
    int month;
    String item_name;
    boolean is_expense;
    int quantity;
    int unit_price;

    public MonthlyData(int year, int month, String item_name, boolean is_expense, int quantity, int unit_price) {
        this.year = year;
        this.month = month;
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }
}