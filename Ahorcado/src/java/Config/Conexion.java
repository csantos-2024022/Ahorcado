
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection conexion;

    public Connection Conexion(){
        try {
          Class.forName("com.mysql.jdbc.Driver");
<<<<<<< HEAD
          String url = "jdbc:mysql://localhost:3306/DBAhorcado?useSSL=false&allowPublicKeyRetrieval=true";
=======
          String url = "jdbc:mysql://localhost:3306/DB_Ahorcado?useSSL=false&allowPublicKeyRetrieval=true";
>>>>>>> origin/develop
          String user = "root";
          String password = "admin";
          conexion = (Connection) DriverManager.getConnection(url, user, password);
      } catch (ClassNotFoundException error) {
          StackTraceElement elemento = error.getStackTrace()[0];
          System.out.println("Error en: " + elemento.getClassName() + " linea " + elemento.getLineNumber());
          System.out.println("Mensaje: " + error.getMessage());
          error.printStackTrace();
      } catch (SQLException error) {
          StackTraceElement elemento = error.getStackTrace()[0];
          System.out.println("Error en: " + elemento.getClassName() + " linea " + elemento.getLineNumber());
          System.out.println("Mensaje: " + error.getMessage());
          error.printStackTrace();
      }
       return conexion;
   }
}