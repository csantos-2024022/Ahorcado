package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class palabraDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<palabra> listar() {
        String sql = "SELECT * FROM Palabras";
        List<palabra> listaPalabras = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                palabra p = new palabra();
                p.setCodigoPalabra(rs.getInt("codigoPalabra"));
                p.setPalabra(rs.getString("palabra"));
                listaPalabras.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPalabras;
    }

    public void agregar(palabra palabra) {
        String sql = "INSERT INTO Palabras (palabra) VALUES (?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, palabra.getPalabra());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}