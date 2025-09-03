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
        
        // --- INICIO DE LA CORRECCIÓN ---
        // Si el menú es nulo, redirige a la página principal y no hagas nada más.
        if (menu == null) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
            return; // Detiene la ejecución del método
        }
        // --- FIN DE LA CORRECCIÓN ---
        
        if (menu.equalsIgnoreCase("Principal")) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }
        
        if (menu.equalsIgnoreCase("Palabras")) {
            // El 'switch' para la acción debe estar dentro del 'if' del menú 'Palabras'
            if (accion != null) {
                switch (accion) {
                    case "Agregar":
                        String palabraTexto = request.getParameter("txtPalabra");
                        palabra p = new palabra();
                        p.setPalabra(palabraTexto);
                        pdao.agregar(p);
                        break;
                }
            }
            // Después de cualquier acción (incluyendo ninguna), lista y muestra la tabla
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