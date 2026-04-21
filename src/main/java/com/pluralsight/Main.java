package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String employeeFiles = "src/main/resources/employees.csv";
        String currentLine;
        String[] employeeInfo;
        Employee[] employees = new Employee[10];
        try {
            FileReader myFileReader = new FileReader(employeeFiles);
            BufferedReader myBufferedReader = new BufferedReader(myFileReader);


            int i = 0;
            //Printing the title
            System.out.println(myBufferedReader.readLine());
            while (( currentLine = myBufferedReader.readLine())!= null) {
                employeeInfo = currentLine.split("\\|");
                int employeeId = Integer.parseInt(employeeInfo[0]);
                String employeeName = employeeInfo[1];
                double employeeHoursWorked = Double.parseDouble(employeeInfo[2]);
                double employeePayRate = Double.parseDouble(employeeInfo[3]);
                employees[i] = new Employee(employeeId, employeeName, employeeHoursWorked, employeePayRate);
                i++;

            }
        } catch (FileNotFoundException e) {
            System.err.println("File Not found: " + employeeFiles);
        } catch (IOException e) {
            System.out.println("IO Exception. ");
        }

        for (Employee s : employees) {
            if(s!=null) {
                    System.out.printf("%s,ID- %d Gross pay: %.2f  \n",s.getName(),s.getEmployeeId(),s.getGrossPay());

            }
        }

    }
}

