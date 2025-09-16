
package Modelo;

public class palabra {
     int codigoPalabra;
     String Palabra;
         private String pista; 


    public palabra() {
    }

    public palabra(int codigoPalabra, String Palabra, String pista) {
        this.codigoPalabra = codigoPalabra;
        this.Palabra = Palabra;
        this.pista = pista;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
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
