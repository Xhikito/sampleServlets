/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sampleservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import vo.Persona;
import vo.util.JDBCUtil;

/**
 *
 * @author s206e18
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Map<String, Persona> usersMap;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        usersMap = new HashMap<>();
        usersMap.put ("jsmith",new Persona("Smith", "Jhon", "jsmith", "123"));
        usersMap.put ("mperez",new Persona("Perez", "Mary", "mperez", "456"));
        
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
        
        
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        
        Persona persona = null;
        
        if((login!=null && login.length()>0) && (pwd!=null && pwd.length()>0)){
            persona = JDBCUtil.obtenerPersona(login, pwd);
        }
        
        //if(usersMap.get(login)!=null && usersMap.get(login).getPassword().equals(pwd)){
        if(persona !=null){
            //request.setAttribute("message", "Bienvenido "+  usersMap.get(login).getNombre() + " " + usersMap.get(login).getApellido());
            request.setAttribute("message", "Bienvenido "+  persona.getNombre() + " " + persona.getApellido() + " - Usuario: " + persona.getUsuario());
            RequestDispatcher dispacher = request.getRequestDispatcher("/welcome.jsp");
            dispacher.forward(request, response);
        }else{
            RequestDispatcher dispacher = request.getRequestDispatcher("/login.jsp");
            dispacher.forward(request, response); 
        }
    }

    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

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
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
