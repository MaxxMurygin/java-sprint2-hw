import java.util.Scanner;

public class UserMenu {
  //  int minItem;
 //   int maxItem;
    int intItem;
    String item;
    /*    UserMenu(){
        this.minItem = 0;
        this.maxItem = 5;
    }
    UserMenu(int minItem, int maxItem){
        this.minItem = minItem;
        this.maxItem = maxItem;
    }*/

     int getUserChoice(int minItem, int maxItem){
        Scanner scanner = new Scanner(System.in);
        while (true){
            item = scanner.next();
            try {
                intItem = Integer.parseInt(item);
            } catch (NumberFormatException n) {
                System.out.println("Неверный выбор, введите цифру.");
                continue;
            }
            if ((intItem < minItem) || (intItem > maxItem)){
                System.out.printf("Неверный выбор, введите цифру от %d до %d %n", minItem, maxItem);
            } else return intItem;
        }
    }

    void printMainMenu(){
        System.out.println();
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты ");
        System.out.println("2 - Считать годовой отчёт ");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах ");
        System.out.println("5 - Вывести информацию о годовом отчёте ");
        System.out.println("0 - Выход");
    }

    void printYesNoMenu(){
        System.out.println();
        System.out.println("1 - Да ");
        System.out.println("2 - Нет ");
    }
}
