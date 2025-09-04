package Controlador;

import Modelo.palabra;
import Modelo.palabraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ObtenerPalabras")
public class ObtenerPalabrasServlet extends HttpServlet {

    private final palabraDAO pdao = new palabraDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        List<palabra> listaPalabras = pdao.listar();
        
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        
        for (int i = 0; i < listaPalabras.size(); i++) {
            palabra p = listaPalabras.get(i);
            
            jsonBuilder.append("{");
            jsonBuilder.append("\"codigoPalabra\": ").append(p.getCodigoPalabra()).append(", ");
            jsonBuilder.append("\"palabra\": \"").append(p.getPalabra()).append("\", ");
            jsonBuilder.append("\"pista\": \"").append(p.getPista()).append("\"");
            jsonBuilder.append("}");
            
            if (i < listaPalabras.size() - 1) {
                jsonBuilder.append(", ");
            }
        }
        
        jsonBuilder.append("]");
        
        out.print(jsonBuilder.toString());
        out.flush();
    }
}