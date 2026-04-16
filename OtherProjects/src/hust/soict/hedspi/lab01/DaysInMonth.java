package hust.soict.hedspi.lab01;

import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String monthInput;
        int year = -1;
        int month = -1;
        while (true) {
            System.out.print("Enter month: ");
            monthInput = sc.next();
            System.out.print("Enter year: ");
            if (sc.hasNextInt()) {
                year = sc.nextInt();
                if (year < 0) {
                    System.out.println("Invalid year. Please try again.");
                    continue;
                }
            }
            else {
                System.out.println("Invalid year. Please try again.");
                sc.next();
                continue;
            }
            month = getMonth(monthInput);
            if (month != -1) {
                break;
            }
            else {
                System.out.println("Invalid month. Please try again.");
            }
        }
        int days = calculateDays(month, year);
        System.out.println("Number of days: " + days);
    }
    private static int getMonth(String input) {
        input = input.toLowerCase();
        if (input.equals("january") || input.equals("jan.") || input.equals("jan") || input.equals("1")) return 1;
        if (input.equals("february") || input.equals("feb.") || input.equals("feb") || input.equals("2")) return 2;
        if (input.equals("march") || input.equals("mar.") || input.equals("mar") || input.equals("3")) return 3;
        if (input.equals("april") || input.equals("apr.") || input.equals("apr") || input.equals("4")) return 4;
        if (input.equals("may") || input.equals("5")) return 5;
        if (input.equals("june") || input.equals("jun.") || input.equals("jun") || input.equals("6")) return 6;
        if (input.equals("july") || input.equals("jul.") || input.equals("jul") || input.equals("7")) return 7;
        if (input.equals("august") || input.equals("aug.") || input.equals("aug") || input.equals("8")) return 8;
        if (input.equals("september") || input.equals("sept.") || input.equals("sep") || input.equals("9")) return 9;
        if (input.equals("october") || input.equals("oct.") || input.equals("oct") || input.equals("10")) return 10;
        if (input.equals("november") || input.equals("nov.") || input.equals("nov") || input.equals("11")) return 11;
        if (input.equals("december") || input.equals("dec.") || input.equals("dec") || input.equals("12")) return 12;
        return -1;
    }
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    private static int calculateDays(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11: return 30;
            case 2: return isLeapYear(year) ? 29 : 28;
            default: return 31;
        }
    }
}