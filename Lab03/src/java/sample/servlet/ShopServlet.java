/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.utils.Global;

/**
 *
 * @author Chuot Bach
 */
public class ShopServlet extends HttpServlet {

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
        System.out.println("shop");
        try {
            // 1. Get list
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> list = dao.loadAll();
            
            // 2. Make content
            String htmlContent = "";
            if (list==null || list.isEmpty()) {
                htmlContent = "Khong ton tai san pham nao";
            } else {
                String htmlList = "";
                int count = 0;
                for (ProductDTO item: list) {
                    htmlList+= "<tr>";
                    htmlList+= "<td>"+ ++count +"</td>";
                    htmlList+= "<td>"+item.getId()+"</td>";
                    htmlList+= "<td><a href='ProcessServlet?btAction=Buy&productId="+item.getId()+"'>"+item.getName()+"</a></td>";
                    htmlList+= "<td>"+item.getUnit()+"</td>";
                    htmlList+= "<td>"+item.getPrice()+"</td>";
                    htmlList+= "</tr>";
                }
                htmlContent += "<table border=\"1\">\n" +
                    "            <thead>\n" +
                    "                <tr>\n" +
                    "                    <th>No</th>\n" +
                    "                    <th>Product ID</th>\n" +
                    "                    <th>Product Name</th>\n" +
                    "                    <th>Quantity Per Unit</th>\n" +
                    "                    <th>Price (USD)</th>\n" +
                    "                </tr>\n" +
                    "            </thead>\n" +
                    "            <tbody>\n" +
                        htmlList +
                    "            </tbody>\n" +
                    "        </table>" +
                            "<div>Total: "+count+" products in the store</div>" +
                            "<a href='"+Global.SL_CART+"'>View my shopping cart</a>";
            }
            
            // 3. Print
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
