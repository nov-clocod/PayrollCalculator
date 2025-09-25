package com.pluralsight;
import java.util.Scanner;

public class PayrollCalculator {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("What is your name?");
        String inputName = myScanner.nextLine();

        System.out.println("How many hours have your worked?");
        float hoursWorked = myScanner.nextFloat();

        System.out.println("What is your pay rate?");
        float payRate = myScanner.nextFloat();

        float employeePay;

        if (hoursWorked > 40) {
            float regularHours = 40;
            float overtimeHours = hoursWorked - 40;
            float overtimePay = overtimeHours * payRate * 1.5f;
            employeePay = regularHours * payRate + overtimePay;
        } else {
            employeePay = hoursWorked * payRate;
        }

        System.out.printf(inputName + " your pay is: $%.2f", employeePay);
    }
}
