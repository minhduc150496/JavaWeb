/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cart.Cart;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.utils.Global;

/**
 *
 * @author Chuot Bach
 */
public class CartServlet extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            Cart cart = (Cart)session.getAttribute(Global.PR_CART);
            String htmlContent = "";
            if (cart==null) {
                htmlContent = "<p>Gio hang khong co gi</p>";
            } else {
                Map<String, Integer> items = cart.getItems();
                String htmlList = "";
                int count = 0;
                double totalPrice = 0;
                
                for(Map.Entry<String, Integer> item: items.entrySet()) {
                    String id = item.getKey();
                    int quantity = item.getValue();
                    ProductDAO dao = new ProductDAO();
                    ProductDTO product = dao.getById(id);
                    String name = product.getName();
                    String unit = product.getUnit();
                    double price = product.getPrice();
                    htmlList+= "<tr>";
                    htmlList+= "<td>"+ ++count +"</td>";
                    htmlList+= "<td>"+id+"</td>";
                    htmlList+= "<td>"+name+"</td>";
                    htmlList+= "<td>"+unit+"</td>";
                    htmlList+= "<td>"+price+"</td>";
                    htmlList+= "<td>"+quantity+"</td>";
                    htmlList+= "</tr>";
                    totalPrice += price*quantity;
                }
                
                htmlContent += "<table border=\"1\">\n" +
                    "            <thead>\n" +
                    "                <tr>\n" +
                    "                    <th>No</th>\n" +
                    "                    <th>Product ID</th>\n" +
                    "                    <th>Product Name</th>\n" +
                    "                    <th>Quantity Per Unit</th>\n" +
                    "                    <th>Price (USD)</th>\n" +
                    "                    <th>Quantity</th>\n" +
                    "                </tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>\n" +
                        htmlList +
                    "            </tbody>\n" +
                    "        </table>" +
                            "<div>Total: "+count+" products selected</div>" +
                            "<div>Payment in USD: "+totalPrice+"</div>" +
                            "<a href='"+Global.SL_CHECKOUT+"'>Check out</a>";
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Online Shop</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Online Shop</h1>");
            
            out.println(htmlContent);
            
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
