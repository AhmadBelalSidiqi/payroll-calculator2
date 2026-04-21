package com.pluralsight;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileLocation = "src/main/resources/";
        String employeeFiles;
        String payrollFile;
        Employee[] employees = new Employee[8];
        System.out.println("Enter the name of the file employee to process: ");
        employeeFiles = fileLocation + scanner.nextLine();
        System.out.println("Enter the name of the payRoll file to create: ");
        payrollFile = fileLocation + scanner.nextLine();


        // Creating employees objects
        try {
            FileReader myFileReader = new FileReader(employeeFiles);
            BufferedReader myBufferedReader = new BufferedReader(myFileReader);
            populateObjectArray(myBufferedReader, employees);

        } catch (FileNotFoundException e) {
            System.err.println("File Not found: " + employeeFiles);
        } catch (IOException e) {
            System.out.println("IO Exception. ");
        }

        // Writing the objects data  in a text file.
        try {
            FileWriter myWriter = new FileWriter(payrollFile, true);
            BufferedWriter myBufferedWriter = new BufferedWriter(myWriter);
            String header = "id|name|Gross Pay";
            myBufferedWriter.write(header);
            myBufferedWriter.newLine();
            String text;
            for (Employee s : employees) {
                if (s != null) {
                    text = String.format("ID: %d - Name: %s - Gross pay: %.2f  %n", s.getEmployeeId(), s.getName(), s.getGrossPay());
                    myBufferedWriter.append(text);
                }
            }

            myBufferedWriter.close();

        } catch (IOException e) {
            System.err.println("IO Exceptions");
        }


    }

    private static void populateObjectArray(BufferedReader myBufferedReader, Employee[] employees) throws IOException {
        int index = 0;
        String currentLine;
        while ((currentLine = myBufferedReader.readLine()) != null) {
            //Testing the first line of code for titles
            if (currentLine.contains("id|name|hours-worked|pay-rate")) {
                continue;
            }
            String[] currentEmployeeInfo = currentLine.split("\\|");
            int employeeId = Integer.parseInt(currentEmployeeInfo[0]);
            String employeeName = currentEmployeeInfo[1];
            double employeeHoursWorked = Double.parseDouble(currentEmployeeInfo[2]);
            double employeePayRate = Double.parseDouble(currentEmployeeInfo[3]);
            employees[index] = new Employee(employeeId, employeeName, employeeHoursWorked, employeePayRate);
            index++;

        }
    }
}

