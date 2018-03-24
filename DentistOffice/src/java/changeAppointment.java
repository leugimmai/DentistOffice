/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Business.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Java servlet that will delete the patients existing appointment so they can create
 * a new appointment back at their account page.
 *
 * @author Miguel Quintana
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/changeAppointment"})
public class changeAppointment extends HttpServlet {

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
            
            //Get patients id from hidden textbox input parameter and form.
            //Got added in from the session previously created.
            String patientId = request.getParameter("patientIdTb");
            
            Patient patient = new Patient();

            try {
                //Find the patient information to add to the class and then delete 
                //their existing appointment. Reinstantiate the information and 
                //create a new session to forward back to their account page
                //to set up a new appointment.
                patient.selectFromDbWithId(patientId);

                patient.deleteAppointment(patientId);

                patient.selectFromDbWithId(patientId);

                HttpSession patientSession;
                patientSession = request.getSession();
                patientSession.setAttribute("Patient", patient);

            } catch (Exception e) {
                e.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("/Patient.jsp");
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
