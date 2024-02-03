package org.example.platzi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
    En nuestro paquete util, tenemos la clase DatabaseConnection, que se encarga de manejar la lógica de la conexión a la base de datos.
    Utilizamos el patrón Singleton para garantizar que solo tengamos una instancia de conexión en todo el programa.
    La clase DatabaseConnection expone un método getInstance() que nos permite obtener la instancia de la conexión en cualquier parte del programa.
* */

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String user = "root";
    private static final String pass = "#M3t4l&c4";

    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, pass);
        }
        return connection;
    }
}
