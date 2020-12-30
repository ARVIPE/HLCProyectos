package web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Conexion;
import model.Claves;
import model.Clave;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private Conexion conexion;

    public void init() {
        conexion = new Conexion();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String password = request.getParameter(Claves.PASS.toString());

        // TODO: Tienes que recuperar de la BD el username dado un email y una pass
        Clave clave = new Clave(password);
        clave.setPass(password);

        try {

            if (conexion.validate(clave)) {

                session.setAttribute("logueado", clave);

                response.sendRedirect("list");

            } else {
                response.sendRedirect("equipoServlet");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
