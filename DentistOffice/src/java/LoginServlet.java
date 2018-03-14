/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author miguel
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email;
        String password;
        String accountType;

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            accountType = request.getParameter("accountRadioBtn");
            email = request.getParameter("emailInput");
            password = request.getParameter("passwordInput");
            
            /**
             * Checking Radio Boxes from Login Form to then validate for either Patient or Dentist
             */

            if ("Patient".equals(accountType)) {
                try {

                    Patient patient = new Patient();
                    patient.selectFromDatabase(email);
                    
                    Appointment appt = new Appointment();
                    appt = patient.getAppointment();
                    

                    if (password.equals(patient.getPassword())) {
                        HttpSession patientSession;
                        patientSession = request.getSession();
                        patientSession.setAttribute("Patient", patient);
                        
                        
                        RequestDispatcher rd = request.getRequestDispatcher("/Patient.jsp");
                        rd.forward(request, response);
                        
                        
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("/LoginError.jsp");
                        rd.forward(request, response);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if ("Dentist".equals(accountType)) {

                try {
                    Dentist dentist = new Dentist();
                    dentist.selectFromDatabase(email);

                    if (password.equals(dentist.getPassword())) {
                        
                        HttpSession dentistSession;
                        dentistSession = request.getSession();
                        dentistSession.setAttribute("Dentist", dentist);
                        
                        RequestDispatcher rd = request.getRequestDispatcher("/Dentist.jsp");
                        rd.forward(request, response);

                        
                    }else{
                        RequestDispatcher rd = request.getRequestDispatcher("/LoginError.jsp");
                        rd.forward(request, response);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

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
