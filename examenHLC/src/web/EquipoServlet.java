package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EquipDAO;
import dao.PartidoDAO;

import model.Claves;
import model.Equipo;
import model.Partido;

/**
 * Servlet implementation class EquipoServlet
 */
@WebServlet("/")
public class EquipoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EquipDAO equipDAO;
    private PartidoDAO partidoDAO;

    public void init() {
        equipDAO = new EquipDAO();
        partidoDAO = new PartidoDAO();
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
                    insertForm(request, response);
                    break;
                case "/delete":
                    deleteForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateForm(request, response);
                    break;
                case "/list":
                    listMatch(request, response);
                break;
                default:
                    listTeam(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listTeam(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Equipo> listEquip = equipDAO.selectAllTeam();
        request.setAttribute("listEquip", listEquip);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    private void listTeamServlet(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Equipo> listEquip = equipDAO.selectAllTeam();
        request.setAttribute("listEquip", listEquip);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loginsuccess.jsp");
        dispatcher.forward(request, response);

    }
    
      private void listMatch(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Partido> listMatch = partidoDAO.selectAllMatch();
        request.setAttribute("listMatch", listMatch);
        List< Equipo> listEquip = equipDAO.selectAllTeam();
        request.setAttribute("listEquip", listEquip);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loginsuccess.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Partido existingMatch = partidoDAO.selectMatch(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
        request.setAttribute("partido", existingMatch);
        dispatcher.forward(request, response);
        
    }
    
    private void insertForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int e1 = Integer.parseInt(request.getParameter(Claves.E1.toString()));
        int e2 = Integer.parseInt(request.getParameter(Claves.E2.toString()));
        int g1 = Integer.parseInt(request.getParameter(Claves.G1.toString()));
        int g2 = Integer.parseInt(request.getParameter(Claves.G2.toString()));
        
        Partido newMatch = new Partido(e1, e2, g1, g2);
        partidoDAO.insertMatch(newMatch);
        response.sendRedirect("list");
    }
    
    private void updateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int e1 = Integer.parseInt(request.getParameter(Claves.E1.toString()));
        int e2 = Integer.parseInt(request.getParameter(Claves.E2.toString()));
        int g1 = Integer.parseInt(request.getParameter(Claves.G1.toString()));
        int g2 = Integer.parseInt(request.getParameter(Claves.G2.toString()));
        
        Partido book = new Partido(id, e1, e2, g1, g2);
        partidoDAO.updateMatch(book);
        response.sendRedirect("partidoServlet");
    }
    
    private void deleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        partidoDAO.deleteMatch(id);
        response.sendRedirect("partidoServlet");
        
    }
    
}
