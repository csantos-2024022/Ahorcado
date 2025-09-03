
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;

import Modelo.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Validar extends HttpServlet {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario usuario = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String btnIngresar = request.getParameter("btnIngresar");
        if (btnIngresar.equalsIgnoreCase("Ingresar")) {
            String nombre = request.getParameter("txtCorreo");
            String pass = request.getParameter("txtContrasena");
            usuario = usuarioDAO.validar(nombre, pass);
            if (usuario.getPass() != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioLogueado", usuario);
                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
