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
import sample.order.OrderDAO;
import sample.order.OrderDTO;
import sample.orderdetails.OrderDetailsDAO;
import sample.orderdetails.OrderDetailsDTO;
import sample.utils.Global;

/**
 *
 * @author Chuot Bach
 */
public class CheckoutServlet extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            if (session!=null) {
                Cart cart = (Cart)session.getAttribute(Global.PR_CART);
                Map<String, Integer> items = cart.getItems();
                
                for (Map.Entry<String, Integer> item: items.entrySet()) {
                    String productId = item.getKey();
                    int quantity = item.getValue();
                    String id = "duc";
                    /*
                    OrderDTO orderDTO = new OrderDTO(id);
                    OrderDAO orderDAO = new OrderDAO();
                    orderDAO.insert(id);
                    /**/
                    OrderDetailsDTO orderDetailDTO = new OrderDetailsDTO(id, productId, quantity);
                    OrderDetailsDAO orderDetailDAO = new OrderDetailsDAO();
                    orderDetailDAO.insert(orderDetailDTO);
                }
            }
            
            response.sendRedirect(Global.SL_PROCESS);
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
