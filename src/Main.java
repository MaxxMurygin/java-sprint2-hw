public class Main {

    public static void main(String[] args) {
        int menuItem;
        UserChoice userChoice = new UserChoice();

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

