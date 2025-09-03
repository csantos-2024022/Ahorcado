
package Modelo;

public class palabra {
     int codigoPalabra;
     String Palabra;

    public palabra() {
    }

     
     
     
    public palabra(int codigoPalabra, String Palabra) {
        this.codigoPalabra = codigoPalabra;
        this.Palabra = Palabra;
    }

    public int getCodigoPalabra() {
        return codigoPalabra;
    }

    public void setCodigoPalabra(int codigoPalabra) {
        this.codigoPalabra = codigoPalabra;
    }

    public String getPalabra() {
        return Palabra;
    }

    public void setPalabra(String Palabra) {
        this.Palabra = Palabra;
    }
     
     
}
