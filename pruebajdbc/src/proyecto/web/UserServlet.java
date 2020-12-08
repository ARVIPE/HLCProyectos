package proyecto.web;

import java.io.IOException;			
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import proyecto.dao.ProdDAO;
import proyecto.dao.UserDAO;
import proyecto.model.Claves;
import proyecto.model.ClavesProd;
import proyecto.model.Producto;
import proyecto.model.Usuario;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private ProdDAO prodDAO;
	
	public void init() {
		userDAO = new UserDAO();
		prodDAO = new ProdDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		
		

		try {      
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/listProd":
				listProd(request, response);
				break;
			case "/newProd":
				showNewFormProd(request, response);
				break;
			case "/insertProd":
				insertProd(request, response);
				break;
			case "/deleteProd":
				deleteProd(request, response);
				break;
			case "/editProd":
				showEditFormProd(request, response);
				break;
			case "/updateProd":
				updateProd(request, response);
				break;
			case "/listProdNormal":
				listProdNormal(request, response);
				break;
			case "/agregarProd":
				agregarProd(request, response);
				break;
			case "/limpiarCesta":
				limpiarCesta(request, response);
				break;
			default:
				   listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void limpiarCesta(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		
		Producto existingProd = prodDAO.limpiarCesta(request, response);
	
		
		listProdNormal(request, response);

		
		
	}
	
	private void agregarProd(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Producto existingProd = prodDAO.agregarProd(id, request, response);
		
		listProdNormal(request, response);

		
		
	}
	
	  private void listProdNormal(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
			        List < Producto > listProd = ProdDAO.selectAllProd();
			        request.setAttribute("listProd", listProd);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("prod-list-normal.jsp");
				    dispatcher.forward(request, response);
		        
			    }
	  
	
	  private void listProd(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
			        List < Producto > listProd = ProdDAO.selectAllProd();
			        request.setAttribute("listProd", listProd);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("prod-list.jsp");
				    dispatcher.forward(request, response);
		        
			    }
	  
	  private void showNewFormProd(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("prod-form.jsp");
			dispatcher.forward(request, response);
		}

		private void showEditFormProd(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			Producto existingProd = prodDAO.selectProd(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("prod-form.jsp");
			request.setAttribute("producto", existingProd);
			dispatcher.forward(request, response);

		} 
	                   
		private void insertProd(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			
			String producto = request.getParameter(ClavesProd.PRODUCT.toString());
			Double precio = Double.parseDouble(request.getParameter(ClavesProd.PRECIO.toString()));
			
			Producto newProd = new Producto(0, producto, precio);
			prodDAO.insertProd(newProd);
			response.sendRedirect("listProd");
		}
	   
		private void updateProd(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter(ClavesProd.ID.toString()));
			String producto = request.getParameter(ClavesProd.PRODUCT.toString());
			Double precio = Double.parseDouble(request.getParameter(ClavesProd.PRECIO.toString()));
			
			Producto book = new Producto(id, producto, precio);
			prodDAO.updateProd(book);
			response.sendRedirect("listProd");
		}

		private void deleteProd(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			prodDAO.deleteProd(id);
			response.sendRedirect("listProd");

		}
	
	  private void listUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
			        List < Usuario > listUser = userDAO.selectAllUsers();
			        request.setAttribute("listUser", listUser);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
				    dispatcher.forward(request, response);
		        
			    }


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("usuario", existingUser);
		dispatcher.forward(request, response);

	} 
                   
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		String usuario = request.getParameter(Claves.USERNAME.toString());
		String contrasena = request.getParameter(Claves.CONTRASENA.toString());
		String email = request.getParameter(Claves.EMAIL.toString());
		Boolean admin = Boolean.parseBoolean(request.getParameter(Claves.ADMIN.toString()));
		
		Usuario newUser = new Usuario(0, usuario, contrasena, email, admin);
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}
   
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter(Claves.USERNAME.toString());
		String contrasena = request.getParameter(Claves.CONTRASENA.toString());
		String email = request.getParameter(Claves.EMAIL.toString());
		Boolean admin = Boolean.parseBoolean(request.getParameter(Claves.ADMIN.toString()));
		

		Usuario book = new Usuario(id, nombre, contrasena, email, admin);
		userDAO.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}

}