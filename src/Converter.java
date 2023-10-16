public class Converter {
    static String monthNumberToString(int num){
        String[] monthNames = {"Январь", "Февраль", "Март",
                "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь",
                "Окрябрь", "Ноябрь", "Декабрь",
        };
        return monthNames[num - 1];
    }
}
