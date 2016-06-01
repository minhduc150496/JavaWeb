package sample.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import sample.registration.RegistrationDAO;
import sample.registration.RegistrationSessionBeanRemote;
import sample.registration.RegistrationSessionBeanRemoteHome;

/**
 *
 * @author Chuot Bach
 */
public class LoginServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            RegistrationDAO dao = new RegistrationDAO();
            // 1. lay context
            Context context = new InitialContext();
            // 2. tim home obj
            Object obj = context.lookup("RegJNDI");
            RegistrationSessionBeanRemoteHome homeObj = 
                    (RegistrationSessionBeanRemoteHome)
                    PortableRemoteObject.narrow(obj, RegistrationSessionBeanRemoteHome.class); 
                    // ep kieu dac biet, unmarshaller
            // 3. tao ejb obj tu home obj
            RegistrationSessionBeanRemote ejbObj = homeObj.create();
            // 4. goi biz method tren ejb obj
            boolean loged = ejbObj.checkLogin(username, password);
            // 5. xu ly client
            
            String url = "invalid.html";
            if (loged) {
                url = "search.jsp";
                
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
            }
            
            // Gui phan hoi
            response.sendRedirect(url);
            
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (CreateException e) {
            e.printStackTrace();
        }/* catch (SQLException e) {
            e.printStackTrace();
        }*/ finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
