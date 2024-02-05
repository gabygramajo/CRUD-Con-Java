package org.example.platzi.repository;

import org.example.platzi.model.Employee;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpoyeeRepository implements Repository <Employee>{

    // para que en cada acción se tome como una sola conexión, en el pool, y se pueda cerrar
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    //abre una conexión a la BD y ejecuta una query para traer todos los empleados almacenándolos en un List
    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection(); // Para que la conexion se pueda cerrar
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees") ) {
            while (resultSet.next()) {
                employees.add(createEmployee(resultSet));
            }
        }
        return employees;
    }

    //abre una conexión a la BD y ejecuta una query para traer un empleado por su ID
    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;

        try (Connection connection = getConnection(); // Para que la conexion se pueda cerrar
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?")){
            preparedStatement.setInt(1, id);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                if (resultset.next()) {
                    employee = createEmployee(resultset);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {

        String sql;

        if(employee.getId() != null && employee.getId() > 0) {
            sql = "UPDATE employees SET first_name=?, pa_surname=?, ma_surname=?, email=?, salary=?, curp=? WHERE id=?";
        } else {
            sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES (?, ?, ?, ?, ?, ?)";
        }



        try (Connection connection = getConnection();// Para que la conexion se pueda cerrar
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getPa_surname());
            preparedStatement.setString(3, employee.getMa_surname());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setFloat(5, employee.getSalary());
            preparedStatement.setString(6, employee.getCurp());

            if(employee.getId() != null && employee.getId() > 0) {
                preparedStatement.setInt(7, employee.getId());
            }

            // ejecutamos el insert
            int rowsAffected = preparedStatement.executeUpdate();

            String msg = (rowsAffected > 0)
                    ? "Inserted employee successfully"
                    : "employee already exists";

            System.out.println(msg);
        }

    }

    @Override
    public void delete(Integer id) throws SQLException {

        String sql = "DELETE FROM employees WHERE id=?";

        try (Connection connection = getConnection();// Para que la conexion se pueda cerrar
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }

    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee e = new Employee();
        e.setId(resultSet.getInt("id"));
        e.setFirst_name(resultSet.getString("first_name"));
        e.setPa_surname(resultSet.getString("pa_surname"));
        e.setMa_surname(resultSet.getString("ma_surname"));
        e.setEmail(resultSet.getString("email"));
        e.setSalary(resultSet.getFloat("salary"));
        e.setCurp(resultSet.getString("curp"));
        return e;
    }
}
