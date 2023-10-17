public class Converter {
    static String monthNumberToString(int num){
        String[] monthNames = {"Январь", "Февраль", "Март",
                "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь",
                "Окрябрь", "Ноябрь", "Декабрь",
        };
        if (num >=1 && num <=12){
            return monthNames[num - 1];
        }
        return "Безвременье настало...";
    }
}
