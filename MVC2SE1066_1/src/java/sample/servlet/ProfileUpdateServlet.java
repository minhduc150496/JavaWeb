/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.registration.RegistrationDAO;

/**
 *
 * @author Chuot Bach
 */
public class ProfileUpdateServlet extends HttpServlet {
    private final String updateFailPage = "update-fail.html";
    private final String updateSuccessPage = "update-success.html";
    private final String profilePage = "profile.html";
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
            String button = request.getParameter("btAction");
            if (button.equals("Submit")) {
                // 1. Get values from Form
                String username = request.getParameter("txtUsername");
                String password = request.getParameter("txtPassword");
                String confirm = request.getParameter("txtConfirm");
                String lastName = request.getParameter("txtLastname");
                boolean isAdmin = request.getParameter("chkAdmin")!=null;
                // 2. Do update
                RegistrationDAO dao = new RegistrationDAO();
                boolean updated = dao.updateProfile(username, password, confirm, lastName, isAdmin);
                // 3. Redirect
                String url = updateFailPage;
                if (updated) {
                    url = updateSuccessPage;
                }
                response.sendRedirect(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
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
