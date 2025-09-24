package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public Usuario validar(String nombre, String pass) {
        Usuario user = new Usuario();
        String sql = "SELECT * FROM Usuarios WHERE nombre = ? AND pass = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            
            // Si hay un resultado, significa que las credenciales son correctas
            if (rs.next()) {
                user.setCodigoUsuario(rs.getInt("codigoUsuario"));
                user.setNombre(rs.getString("nombre"));
                user.setPass(rs.getString("pass"));
            } else {

                System.out.println("El usuario o la contraseña son incorrectos");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error en la validación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}