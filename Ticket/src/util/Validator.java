package util;

public class Validator {

    private static String REGEX_INTEGER = "^-?\\d+$";
    private static String REGEX_DOUBLE = "^-?\\d+(\\.\\d+)?$";
    private static String REGEX_VALIDNAME = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]+$";
    private static String REGEX_POSITIVENUMBERS = "^\\d+(\\.\\d+)?$";
    private static String REGEX_STRINGSWITHLENGTH = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü ]{1,50}$";
    private static String REGEX_VALIDEMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n";

    public static boolean isInteger(String number){
        return number.matches(REGEX_INTEGER);
    }

    public static boolean isDouble(String number){
        return number.matches(REGEX_DOUBLE);
    }

    public static boolean isPositive(String number){
        return number.matches(REGEX_POSITIVENUMBERS);
    }

    public static boolean isValidName(String string){
        return string.matches(REGEX_VALIDNAME);
    }

    public static boolean isNameWithLength(String string){
        return string.matches(REGEX_STRINGSWITHLENGTH);
    }

    public static boolean isPositiveWithNumber(int number){
        return number > 0;
    }

    public static boolean isValidEmail(String string){
        return string.matches(REGEX_VALIDEMAIL);
    }
}
