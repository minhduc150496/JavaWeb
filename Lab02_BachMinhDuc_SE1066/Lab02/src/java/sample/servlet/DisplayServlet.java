/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.Product;
import sample.utils.DBUtils;

/**
 *
 * @author Chuot Bach
 */
public class DisplayServlet extends HttpServlet {

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
            // 1. Prepare
            Connection con = DBUtils.getConnection();
            Product[] products = Product.loadAll(con);
            String rows = "";
            for(Product p: products) {
                String format = "<tr>"
                        + "<td>%s</td>"
                        + "<td>%s</td>"
                        + "<td>%s</td>"
                        + "<td>%s</td>"
                        + "<td>%s</td>"
                        + "</tr>";
                rows += String.format(format, p.getCode(), p.getName(), p.getDescription(), p.getPrice(), p.getCategory());
            }
            String table = "\n" +
"        <table border=\"1\">\n" +
"            <thead>\n" +
"                <tr>\n" +
"                    <th>Code</th>\n" +
"                    <th>Name</th>\n" +
"                    <th>Description</th>\n" +
"                    <th>Price</th>\n" +
"                    <th>Category</th>\n" +
"                </tr>\n" +
"            </thead>\n" +
"            <tbody>\n" +
                    rows+
"            </tbody>\n" +
"        </table>\n";
            
            // 2.Output
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ShowServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>All products</h1>");
            out.println(table);            
            out.println("<br/><a href='index.html'>Home page</a>");
            out.println("</body>");
            out.println("</html>");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
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
