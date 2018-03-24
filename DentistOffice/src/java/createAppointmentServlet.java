/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 * Java servlet that creates an appointment for the patient in the database.
 *
 * @author Miguel Quintana
 * @version 1.0
 *
 */
@WebServlet(urlPatterns = {"/createAppointmentServlet"})
public class createAppointmentServlet extends HttpServlet {

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

            //Parameters grabbed from the form field that is needed to make
            //an appointment for the patient
            String dateTime = request.getParameter("dateTimePicker");
            String dentistChoice = request.getParameter("dentistChoice");
            String procedureChoice = request.getParameter("procedureChoice");
            String patientId = request.getParameter("patientIdTb");

            Patient patient = new Patient();

            try {

                //Add patients information to the patient class using their id to then
                //create an appointment. To create a new session with the patients updated
                //information and forward to a confirmation page.
                patient.selectFromDbWithId(patientId);

                patient.createAppointment(dateTime, dentistChoice, procedureChoice);

                patient.selectFromDbWithId(patientId);

                HttpSession patientSession;
                patientSession = request.getSession();
                patientSession.setAttribute("Patient", patient);

            } catch (Exception e) {
                System.out.println("Could Not Add Appointment");
            }

            RequestDispatcher rd = request.getRequestDispatcher("/appointmentConfirmation.jsp");
            rd.forward(request, response);

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
