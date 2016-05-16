/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDTO;

/**
 *
 * @author Chuot Bach
 */
public class ResultServlet extends HttpServlet {

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
        String WebSite = getInitParameter("ServletParam");
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>"+WebSite+"</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Result</h1>");
            
            String key = request.getParameter("txtKey");
            out.println(String.format("<p>Key: %s</p>",key));
            List<UserDTO> result = (List)request.getAttribute("resultList");
            if (result==null || result.size()==0) {
                out.println("<h2 style='color:purple'>No results.</h2>");
            } else {
                String s = "";
                for(UserDTO item: result) {
                    String urlRew = "ProcessServlet?btAction=Delete&pk="
                            +item.getUsername()+"&lastSearch="+
                            request.getParameter("txtKey");
                    s += "<tr>"+
                        "<td>"+item.getUsername()+"</td>"+
                        "<td>"+item.getPassword()+"</td>"+
                        "<td>"+item.getLastName()+"</td>"+
                        "<td>"+item.isAdmin()+"</td>"+
                        "<td><a href='"+urlRew+"'>Delete</a></td>"+
                        "</tr>";
                }
                out.println("\n" +
                "        <table border='1'>\n" +
                "            <thead>\n" +
                "                <tr>\n" +
                "                    <th>Username</th>\n" +
                "                    <th>Password</th>\n" +
                "                    <th>Lastname</th>\n" +
                "                    <th>Admin</th>\n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n"+
                        s +
                "            </tbody>\n" +
                "        </table>");
            }
            
            out.println("</body>");
            out.println("</html>");
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
