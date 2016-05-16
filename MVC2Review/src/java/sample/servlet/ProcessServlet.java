/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.registration.RegistrationDAO;

/**
 *
 * @author Chuot Bach
 */
public class ProcessServlet extends HttpServlet {
    private final String invalidPage = "invalid.html";
    private final String searchPage = "search.html";
    
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Init");
    }
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
        
        try {/*
            String button = request.getParameter("btAction");
            if (button.equals("Login")) {                
                // 1. Input
                String username = request.getParameter("txtUsername");
                String password = request.getParameter("txtPassword");
                
                // 2. Process
                RegistrationDAO dao = new RegistrationDAO();
                boolean valid = dao.checkLogin(username, password);
                String url = invalidPage;
                if (valid) {
                    url = searchPage;
                }
                // 3. Output
                response.sendRedirect(url);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();/**/
        } finally {
            out.close();
        }
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        System.out.println("Service");
    }
    public void destroy() {
        super.destroy();
        System.out.println("Destroy");
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
    @Override
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
