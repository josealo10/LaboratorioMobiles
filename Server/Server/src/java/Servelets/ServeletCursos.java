/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelets;

import Controller.ControllerCursos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jaalf
 */
@WebServlet(name = "ServeletCursos", urlPatterns = {"/ServeletCursos"})
public class ServeletCursos extends HttpServlet {

    private ControllerCursos controller = new ControllerCursos();
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
            throws ServletException, IOException, JSONException, SQLException {
        
        JSONObject jsonResponse = controller.getCursos();
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
        
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
      resp.setHeader("Access-Control-Allow-Methods", "GET");
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
        setAccessControlHeaders(response);
        JSONObject jsonResponse = new JSONObject();
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
                try {
                    jsonResponse.put("success", false);
                    jsonResponse.put("Error", "error en la base de datos");
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.print(jsonResponse);
                    out.flush();
                } catch (JSONException ex1) {
                    Logger.getLogger(ServeletLogin.class.getName()).log(Level.SEVERE, null, ex1);
                }
        } catch (JSONException ex) {
            try {
                    jsonResponse.put("success", false);
                    jsonResponse.put("Error", "error al pacear json");
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.print(jsonResponse);
                    out.flush();
                } catch (JSONException ex1) {
                    Logger.getLogger(ServeletLogin.class.getName()).log(Level.SEVERE, null, ex1);
                }
        }
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
        JSONObject jsonResponse = new JSONObject();
        setAccessControlHeaders(response);
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
                try {
                    jsonResponse.put("success", false);
                    jsonResponse.put("error", "error en la base de datos");
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.print(jsonResponse);
                    out.flush();
                } catch (JSONException ex1) {
                    Logger.getLogger(ServeletLogin.class.getName()).log(Level.SEVERE, null, ex1);
                }
        } catch (JSONException ex) {
            try {
                    jsonResponse.put("success", false);
                    jsonResponse.put("error", "error al pacear json");
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.print(jsonResponse);
                    out.flush();
                } catch (JSONException ex1) {
                    Logger.getLogger(ServeletLogin.class.getName()).log(Level.SEVERE, null, ex1);
                }
        }
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
