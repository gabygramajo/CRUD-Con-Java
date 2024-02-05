package org.example.platzi.main;

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmpoyeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoCrudRepository {
//    public static void main(String[] args) throws SQLException {
//        try(Connection myConn = DatabaseConnection.getInstance()) {
//
//            // instancia del crud repository
//            Repository<Employee> repository = new EmpoyeeRepository();
//
//            // GET: obtener un empleado mediante su ID
//            Employee e = repository.getById(2);
//
//            // GET: obtener todos lo empleados
//            repository.findAll();
//
//            // CREATE: dar de alta un empleado
//            Employee newEmployee = new Employee("Dustin", "William", "Scarlett", "dastin.scarlett@gmail.com", 65000.00F, "");
//            repository.save(newEmployee);
//
//            // UPDATE: actualizar un registro/empleado
//            newEmployee = repository.getById(16);
//            newEmployee.setPa_surname("Miller");
//            repository.save(newEmployee);
//
//            // DELETE: borrar un registro/empleado
//            repository.delete(16);
//
//        }
//    }
}
