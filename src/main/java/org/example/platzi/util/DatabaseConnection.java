package org.example.platzi.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/*
    En nuestro paquete util, tenemos la clase DatabaseConnection, que se encarga de manejar la lógica de la conexión a la base de datos.
    Utilizamos el patrón Singleton para garantizar que solo tengamos una instancia de conexión en todo el programa.
    La clase DatabaseConnection expone un método getInstance() que nos permite obtener la instancia de la conexión en cualquier parte del programa.
* */

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/project";
    private static final String user = "root";
    private static final String pass = System.getenv("MYSQL_PASS");

    private static BasicDataSource pool;

    public static BasicDataSource getInstance() {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pass);

            // tamaño inicial del pool de conexiones.
            pool.setInitialSize(3);
            // el tamaño máximo de conexiones que se podrá mantener
            pool.setMaxTotal(10);
            // mínimo número de conexiones inactivas que podemos tener.
            pool.setMinIdle(3);
            // máximo número de conexiones inactivas que podemos tener.
            pool.setMaxIdle(10);

        }
        return pool;
    }

    // método que tendrá una sola conexión del pool, el cual se invocará la 1ra vez desde el getInstance(), siempre y cuando las conexiones estén disponibles devolverá las otras conexiones y no null.
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
