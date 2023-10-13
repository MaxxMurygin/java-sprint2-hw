public class Main {

    public static void main(String[] args) {
        int menuItem;
        UserMenu userChoice = new UserMenu();

        while (true){
            menuItem = userChoice.choice();
            System.out.println(menuItem);
            if (menuItem == 0){
                System.out.println("Good luck!");
                break;

            }
        }
    }
}

