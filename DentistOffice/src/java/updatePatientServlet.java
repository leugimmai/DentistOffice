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
 *Java servlet that updates the patients account information
 * @author Miguel Quintana
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/updatePatientServlet"})
public class updatePatientServlet extends HttpServlet {

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
            
            //The parameters grabbed from the form to then update the patients account
            String firstName = request.getParameter("firstNameTB");
            String lastName = request.getParameter("lastNameTB");
            String email = request.getParameter("emailTB");
            String address = request.getParameter("addressTB");
            String password = request.getParameter("passwordTB");
            String id = request.getParameter("patientIdTB");
            String insurance = request.getParameter("insuranceTB");
            
            
            Patient patient = new Patient();

            try {
                
                //Update of the patients account in the database. To then create a 
                //new session and forward back to their main page.
                patient.updatePatient(id, password, firstName, lastName, address, email, insurance);

                System.out.println(patient.displayPatient());
                
                patient.selectFromDbWithId(id);
                
                HttpSession dentistSession;
                dentistSession = request.getSession();
                dentistSession.setAttribute("Patient", patient);
            } catch (Exception ex) {
                ex.printStackTrace();
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
