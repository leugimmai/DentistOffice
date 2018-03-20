/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * Appointment Business Object Class
 * @author Miguel Quintana
 */


public class Appointment {
    private String apptDateTime;
    private String patientId;
    private String dentistId;
    private String procedureCode;
    private boolean isEmpty = false;
    
    public Appointment(){
        this.apptDateTime = "";
        this.patientId = "";
        this.dentistId = "";
        this.procedureCode = "";
    }
    
    /**
     * Constructor Method
     * @param apptDateTime
     * @param patientId
     * @param dentistId
     * @param procedureCode 
     */

    public Appointment(String apptDateTime, String patientId, String dentistId, String procedureCode) {
        this.apptDateTime = apptDateTime;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.procedureCode = procedureCode;
    }
    
    public void selectDbPatientAppt(String patientId){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel","password");
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery("select * from Appointments where patId='" + patientId + "';");
            
            resultSet.next();
            
            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            
            this.isEmpty = false;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public String displayAppointment(){
        return "Appointment: " + this.apptDateTime + ".\nPatient: " + this.patientId + "\nDoctor: " + this.dentistId + 
                "\nProcedure: " + this.procedureCode;
    }
    
    
    /**
    *
    * Getters and Setters
    *
    */
    
    public void setAll(String apptDateTime, String patientId, String dentistId, String procedureCode){
        this.apptDateTime = apptDateTime;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.procedureCode = procedureCode;
    }
    
    public boolean isEmpty(){
        return this.isEmpty;
    }
    
    
    public String getApptDateTime() {
        return apptDateTime;
    }

    public void setApptDateTime(String apptDateTime) {
        this.apptDateTime = apptDateTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDentistId() {
        return dentistId;
    }

    public void setDentistId(String dentistId) {
        this.dentistId = dentistId;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }
    
    
}
