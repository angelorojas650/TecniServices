package clases;

import java.sql.*;
/**
 *
 * @author Angelo
 */
public class Conexion {
    //Conexion Local
    public static Connection conectar(){
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_ts", "root", "");
            return cn;
            
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
        }
        return (null);
    }
}
