package pl.Karmelita.utils;

import java.math.BigDecimal;
import java.util.Scanner;

public class ScannerUtils {
    public static final Scanner scanner = new Scanner(System.in);

    public static int getIntFromScanner(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    public static String getStringFromScanner(String message) {
        System.out.println(message);
        return scanner.next();
    }

    public static float getFloatFromScanner(String message) {
        System.out.println(message);
        return scanner.nextFloat();
    }
    public static BigDecimal getBigDecimalFromScanner(String message) {
        System.out.println(message);
        return scanner.nextBigDecimal();
    }

    public static boolean getBooleanFromScanner(String message) {
        System.out.println(message);
        return scanner.nextBoolean();
    }




}