/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelets;

import Controller.ControllerLogin;
import Exceptions.ExceptionManager;
import Logic.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.net.Proxy.Type.HTTP;
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
@WebServlet(name = "ServeletLogin", urlPatterns = {"/ServeletLogin"})
public class ServeletLogin extends HttpServlet {

    
    private ControllerLogin controller = new ControllerLogin();
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
            throws ServletException, IOException, SQLException, JSONException {
        
        StringBuffer jb = new StringBuffer();
        String line = null;
        
        try {
          BufferedReader reader = request.getReader();
          while ((line = reader.readLine()) != null)
            jb.append(line);
        } catch (Exception e) { /*report an error*/ }
        
        JSONObject  jsonRequest= new JSONObject(jb.toString());
        JSONObject jsonResponse = controller.getLoginResponse(jsonRequest.getString("username"), jsonRequest.getString("clave"));
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
        
    }
    
    private void setAccessControlHeaders(HttpServletResponse resp) {
      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
      resp.setHeader("Access-Control-Allow-Methods", "POST");
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
                    ExceptionManager.manageBDError(jsonResponse);
                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.print(jsonResponse);
                    out.flush();
                } catch (JSONException ex1) {
                    Logger.getLogger(ServeletLogin.class.getName()).log(Level.SEVERE, null, ex1);
                }
        } catch (JSONException ex) {
            try {
                    ExceptionManager.manageJsonError(jsonResponse);
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
        setAccessControlHeaders(response);
        JSONObject jsonResponse = new JSONObject();
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
                try {
                    jsonResponse.put("success", false);
                    jsonResponse.put("error", "No existe ese usuario");
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
