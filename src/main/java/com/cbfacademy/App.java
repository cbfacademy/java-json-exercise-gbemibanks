package com.cbfacademy;

import java.util.List;

import com.cbfacademy.Employee.Employee;
import com.cbfacademy.FileHandler.FilenameException;
import com.cbfacademy.FileHandler.JSONFileHandler;

public class App {
    public static void main(String[] args) {
        try {
            // Reading from example.json
            List<Employee> employees = JSONFileHandler.readFile("java-json-exercise-gbemibanks\\src\\main\\resources\\example.json");
            for (Employee employee : employees) {
                System.out.println(employee);
            }

            // Creating a new employee and saving to employees.json
            Employee newEmployee = new Employee(17, "John", "Doe", List.of("Developer"));
            JSONFileHandler.save(newEmployee, "employees.json");
        } catch (FilenameException e) {
            System.err.println("Filename Exception: " + e.getMessage());
        }
    }
    
}
