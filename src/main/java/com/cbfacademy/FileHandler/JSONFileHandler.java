package com.cbfacademy.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.cbfacademy.Employee.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONFileHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Employee> readFile(String filename) throws FilenameException {
        try (Reader reader = new FileReader(filename)) {
            Employee[] employees = gson.fromJson(reader, Employee[].class);
            if (employees != null) {
                return List.of(employees);
            }
        } catch (FileNotFoundException e) {
            throw new FilenameException("File not found: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }   

    // public static void save(Employee employee, String outputFile) throws FilenameException {
    //     List<Employee> employees = new ArrayList<>();
    //     File file = new File(outputFile);

    //     if (file.exists()) {
    //         employees = readFile(outputFile);
    //     }

    //     employees.add(employee);

    //     try (Writer writer = new FileWriter(outputFile)) {
    //         gson.toJson(employees, writer);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
   
    public static void save(Employee employee, String outputFile) throws FilenameException {
        List<Employee> employees = new ArrayList<>();
        File file = new File(outputFile);

        if (file.exists()) {
            employees = readFile(outputFile);

            // Check if an employee with the same ID already exists
            boolean employeeExists = false;
            for (Employee existingEmployee : employees) {
                if (existingEmployee.getId() == employee.getId()) {
                    // Update the existing employee's information
                    existingEmployee.setFirstName(employee.getFirstName());
                    existingEmployee.setLastName(employee.getLastName());
                    existingEmployee.setRoles(employee.getRoles());
                    employeeExists = true;
                    break;
                }
            }

            // If the employee doesn't exist, add the new employee to the list
            if (!employeeExists) {
                employees.add(employee);
            }
        } else {
            // If the file doesn't exist, simply add the new employee to the list
            employees.add(employee);
        }

        try (Writer writer = new FileWriter(outputFile)) {
            gson.toJson(employees, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
