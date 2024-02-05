package org.example.platzi.main;


import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmpoyeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("----- Listar todos ------");
        Repository<Employee> repository = new EmpoyeeRepository();
        repository.findAll().forEach(System.out::println);

        System.out.println("----- Listar por ID ------");
        System.out.println(repository.getById(2));
        System.out.println(repository.getById(4));

    }
}