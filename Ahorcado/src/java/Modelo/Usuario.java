
package Modelo;

public class Usuario {
    int codigoUsuario;
    String nombre;
    String pass;

    public Usuario() {
    }

    public Usuario(int codigoUsuario, String nombre, String pass) {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.pass = pass;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
