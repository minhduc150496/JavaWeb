/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cart.Cart;
import sample.utils.Global;

/**
 *
 * @author Chuot Bach
 */
public class ViewCartServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewCartServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            HttpSession session = request.getSession(false);
            
            if (session==null) {
                out.println("Gio hang khong ton tai!");
            } else {
                Cart cart = (Cart) session.getAttribute(Global.cart);
                if (cart==null) {                    
                    out.println("Gio hang khong ton tai!");
                } else {
                    Map<String, Integer> items = cart.getItems();
                    if (items==null || items.isEmpty()) {
                        out.println("Gio hang ko ton tai!");
                    } else {                        
                        String sRows = "";
                        for (Map.Entry item: items.entrySet()) {
                            sRows += "<tr>"
                                    + "<td>"+item.getKey()+"</td>"
                                    + "<td>"+item.getValue()+"</td>"
                                    + "<td><input type='checkbox' name='chkItem' value='"+item.getKey()+"'/></td>"
                                    + "</tr>";
                        }
                        String sTable = "";
                        sTable += "<form action='ProcessServlet'>" +
                                "<table border=\"1\">\n" +
                            "            <thead>\n" +
                            "                <tr>\n" +
                            "                    <th>Title</th>\n" +
                            "                    <th>Quantity</th>\n" +
                            "                </tr>\n" +
                            "            </thead>\n" +
                            "            <tbody>\n" +
                                            sRows +
                            "            </tbody>\n" +
                            "        </table>" +
                            "<a href=''>Add more</a>" +
                            "<button type='submit' name='btAction' value='RemoveItem'>Remove</button>" +
                            "</form>";
                        out.println(sTable);
                    }
                }                
            }
            /*
            if (session!=null) {
                // Ko ton tai
                Cart cart = (Cart) session.getAttribute(Global.cart);
                if (cart!=null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items!=null) {
                        String sRows = "";
                        for (Map.Entry item: items.entrySet()) {
                            sRows += "<tr>"
                                    + "<td>"+item.getKey()+"</td>"
                                    + "<td>"+item.getValue()+"</td>"
                                    + "</tr>";
                        }
                        String sTable = "";
                        sTable += "<table border=\"1\">\n" +
                            "            <thead>\n" +
                            "                <tr>\n" +
                            "                    <th>Title</th>\n" +
                            "                    <th>Quantity</th>\n" +
                            "                </tr>\n" +
                            "            </thead>\n" +
                            "            <tbody>\n" +
                                sRows +
                            "            </tbody>\n" +
                            "        </table>";
                        out.println(sTable);
                    }
                }                
            } else {
                out.println("Khong ton tai gio hang.");
            }/**/
            
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
