package com.pluralsight;
import java.io.*;
import java.util.Scanner;

public class Payroll {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter the name of the file employee file to process: ");
        String employeeFile = myScanner.nextLine();

        System.out.println("Enter the name of the payroll file to create: ");
        String payrollFile = myScanner.nextLine();

        try {
            BufferedReader myReader = new BufferedReader(new FileReader(employeeFile));
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(payrollFile));

            String line;
            String text;
            boolean firstRecord = true;

            if (payrollFile.endsWith(".json")) {
                myWriter.write("[\n");
            }

            while ((line = myReader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee employee = new Employee(id, name, hoursWorked, payRate);

                System.out.printf("Employee ID: %d, Name: %s, Gross Pay: $%.2f%n",
                        employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

                if (payrollFile.endsWith(".json")) {
                    if (!firstRecord) {
                        myWriter.write(",\n");
                    }
                    firstRecord = false;

                    myWriter.write("  { \"employeeID\": " + employee.getEmployeeId() + ",");
                    myWriter.write(" \"name\": \"" + employee.getName() + "\",");
                    myWriter.write(" \"grossPay\": " + String.format("%.2f", employee.getGrossPay()));
                    myWriter.write(" }");
                } else {
                    text = String.format("%d | %s | %.2f\n",
                            employee.getEmployeeId(), employee.getName(), employee.getGrossPay());
                    myWriter.write(text);
                }
            }

            if (payrollFile.endsWith(".json")) {
                myWriter.write("\n]\n");
            }

            myScanner.close();
            myWriter.close();
            myReader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + employeeFile);
        }
    }
}