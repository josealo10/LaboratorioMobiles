/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelets;

import Controller.ControllerCursos;
import Exceptions.ExceptionManager;
import java.io.BufferedReader;
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
    
    
    public void sendResponse(HttpServletResponse response,JSONObject jsonResponse) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
      resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
      resp.setHeader("Access-Control-Allow-Methods", "GET");
      resp.setHeader("Access-Control-Allow-Methods", "POST");
      resp.setHeader("Access-Control-Allow-Methods", "PUT");
      resp.setHeader("Access-Control-Allow-Methods", "DELETE");
    }
    
    private String passRequestBodyToString(HttpServletRequest request){
        StringBuffer jb = new StringBuffer();
        String line = null;

        try {
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            jb.append(line);
        } catch (Exception e) { /*report an error*/ }
        return jb.toString();
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
            jsonResponse = controller.getCursos();
            sendResponse(response, jsonResponse);
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
            
            if(request.getParameter("action") != null){
            if(request.getParameter("action").equals("DELETE")){
                jsonResponse = controller.eliminarCurso(request.getParameter("codigo"));
                sendResponse(response, jsonResponse);
                return;
            }
            
            if(request.getParameter("action").equals("PUT")){
                JSONObject  jsonRequest= new JSONObject(passRequestBodyToString(request));
                jsonResponse = controller.actualizarCurso(
                        jsonRequest.getInt("codigo"),
                        jsonRequest.getString("nombre"),
                        jsonRequest.getInt("creditos"),
                        jsonRequest.getInt("horasSemanales"),
                        jsonRequest.getInt("carrera")
                );
                sendResponse(response, jsonResponse);
                return;
            }}

                JSONObject  jsonRequest= new JSONObject(passRequestBodyToString(request));
                jsonResponse = controller.insertarCurso(
                        jsonRequest.getString("nombre"),
                        jsonRequest.getInt("creditos"),
                        jsonRequest.getInt("horasSemanales"),
                        jsonRequest.getInt("carrera")
                );
                sendResponse(response, jsonResponse);
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
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        setAccessControlHeaders(response);
        JSONObject jsonResponse = new JSONObject();
        
        try {
            JSONObject  jsonRequest= new JSONObject(passRequestBodyToString(request));
            jsonResponse = controller.actualizarCurso(
                        jsonRequest.getInt("codigo"),
                        jsonRequest.getString("nombre"),
                        jsonRequest.getInt("creditos"),
                        jsonRequest.getInt("horasSemanales"),
                        jsonRequest.getInt("carrera")
                );
                sendResponse(response, jsonResponse);
        } catch (JSONException ex) {
            Logger.getLogger(ServeletCursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        setAccessControlHeaders(response);
        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse = controller.eliminarCurso(request.getParameter("codigo"));
            sendResponse(response, jsonResponse);
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
