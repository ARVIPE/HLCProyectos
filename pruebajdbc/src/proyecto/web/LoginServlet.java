package proyecto.web;

import java.io.IOException;		
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import proyecto.dao.Conexion;
import proyecto.model.Claves;
import proyecto.model.Usuario;



@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private Conexion conexion;

    public void init() {
        conexion = new Conexion();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();

     
        String password = request.getParameter(Claves.CONTRASENA.toString());
        String email = request.getParameter(Claves.EMAIL.toString());
        Boolean admin = Boolean.parseBoolean(request.getParameter(Claves.ADMIN.toString()));
     
        // TODO: Tienes que recuperar de la BD el username dado un email y una pass
        Usuario usuario = new Usuario(0, null, email, password, admin); 
        usuario.setContrasena(password);
        usuario.setEmail(email);
       
        

	        try {
	        	
	        	 if (conexion.validate(usuario)) {
	        		 session.setAttribute("email", email);
	        		 if(conexion.validateAdmin(usuario)) {
	        			 session.setAttribute("logueado", admin);
	        			 response.sendRedirect("loginsuccessAdmin.jsp");
	        		 }else {
	        			 response.sendRedirect("loginsuccess.jsp");
	        		 }
	        			 
	        		 
	                    
	                
	            } else {
	                response.sendRedirect("login.jsp");
	            }
	            
	
	           
	
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        
    }
}