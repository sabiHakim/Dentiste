/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur.TraitementDent;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dent.Dent;
import model.connexion.Connexion;
import model.scan.ResultatScan;

/**
 *
 * @author Jimmy
 */
public class ChoixEtat extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChoixEtat</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChoixEtat at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        try {
            Connection co = Connexion.connect();
            
            
            Dent d = new Dent();
            Dent[] allDent = d.getAllDent(co);
            
            ResultatScan[] allEtat = ResultatScan.getAllEtat(co);
            
            request.setAttribute("allDent", allDent);
            
            request.setAttribute("allEtat", allEtat);
            
            co.close();
            
            String type = (String) request.getAttribute("type");
            
            RequestDispatcher send =  null; 
            
            if(type.hashCode() == "normal".hashCode()){
               send = request.getRequestDispatcher("insertionEtatDent.jsp");   
            }else{
                //Special Alea
                send = request.getRequestDispatcher("InsertSpecial.jsp");
            }
            
            

            
            send.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ChoixEtat.class.getName()).log(Level.SEVERE, null, ex);
            out.print(ex.getMessage());
        }
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
