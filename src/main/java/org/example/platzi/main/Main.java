package org.example.platzi.main;


import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmpoyeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        try(Connection connection = DatabaseConnection.getInstance()) {


            if (connection.getAutoCommit()) {
                connection.setAutoCommit((false));
                /*Para agrupar varias sentencias en una única transacción, debes cambiar la propiedad “autocommit” de la conexión a “false”. Por defecto, esta propiedad está establecida en “true”. Al desactivar el “autocommit”, las sentencias no se ejecutarán de forma inmediata en la base de datos, lo que nos permite realizar varias operaciones en conjunto.*/
            }

            try {
                Repository<Employee> repository = new EmpoyeeRepository(connection);

                System.out.println("----- Insertar nuevo cliente -----");
//                Employee employee = new Employee();
//                employee.setFirst_name("Miguel");
//                employee.setPa_surname("Rojas");
//                employee.setMa_surname("Villa");
//                employee.setEmail("miro@example.com");
//                employee.setSalary(43000F);
//                employee.setCurp("AMRC234Y91JOLP1234");
//                repository.save(employee);

                Employee employee2 = new Employee();
                employee2.setFirst_name("Luciana");
                employee2.setPa_surname("Dominguez");
                employee2.setMa_surname("Mena");
                employee2.setEmail("lu12@example.com");
                employee2.setSalary(55000F);
                employee2.setCurp("AMRC234Y91JOLPSDET");

                repository.save(employee2);

                // realizamos la confirmación de la transacción
                connection.commit();
            } catch (SQLException e) {
                // rollback en caso de que falle
                connection.rollback();
                throw  new RuntimeException(e);
            }

        }


    }
}