/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.customer.CustomerDAO;
import sample.customer.CustomerDTO;
import sample.order.OrderDAO;
import sample.order.OrderDTO;
import sample.room.RoomDAO;
import sample.room.RoomDTO;
import sample.room.RoomSearchError;
import sample.utils.Global;

/**
 *
 * @author Chuot Bach
 */
public class SearchServlet extends HttpServlet {
    
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
            String roomID = request.getParameter("txtRoom");
            // validate
            RoomSearchError roomSearchError = new RoomSearchError();
            if (roomID == null || roomID.trim().length()<1 || roomID.trim().length()>3) {
                roomSearchError.setValid(false);
                roomSearchError.setRoomIDLengthErr("Room's length must be from 1 to 3.");
            }
            request.setAttribute("roomSearchError", roomSearchError);
            if (roomSearchError.isValid()) {
                // lay room dto
                RoomDAO roomDAO = new RoomDAO();
                RoomDTO roomDTO = roomDAO.getByRoomID(roomID);
                if (roomDTO != null) {
                    request.setAttribute("roomDTO", roomDTO);
                    // lay order dto
                    OrderDAO orderDAO = new OrderDAO();
                    OrderDTO orderDTO = orderDAO.getLastOrderByRoomID(roomID);
                    if (orderDTO != null) {
                        // lay cust dto
                        CustomerDAO customerDAO = new CustomerDAO();
                        CustomerDTO customerDTO = customerDAO.loadByID(orderDTO.getCustID());

                        request.setAttribute("orderDTO", orderDTO);
                        request.setAttribute("customerDTO", customerDTO);                    
                    }
                }
            }
            // dieu huong
            String url = Global.checkoutPage;
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
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
