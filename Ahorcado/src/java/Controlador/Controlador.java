package Controlador;

import Modelo.palabra;
import Modelo.palabraDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {
    
    palabraDAO pdao = new palabraDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        if (menu == null) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
            return; 
        }
        
        if (menu.equalsIgnoreCase("Principal")) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }
        
        if (menu.equalsIgnoreCase("Palabras")) {
            if (accion != null) {
                switch (accion) {
                    case "Agregar":
                        String palabraTexto = request.getParameter("txtPalabra");
                        String PistaTexto = request.getParameter("txtPista");
                        palabra p = new palabra();
                        p.setPalabra(palabraTexto);
                        p.setPista(PistaTexto);
                        pdao.agregar(p);
                        break;
                }
            }
            List<palabra> lista = pdao.listar();
            request.setAttribute("listaPalabras", lista);
            request.getRequestDispatcher("palabras.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador principal para el manejo de acciones del sistema.";
    }
}