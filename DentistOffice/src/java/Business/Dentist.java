/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * Dentist Business Object Class
 */
public class Dentist {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String office;
    private AppointmentList appointmentList = new AppointmentList();
    
    public Dentist(){
        this.id = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.office = "";
        
    }
    
    /**
     * Constructor method for Dentist
     * @param id
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param office 
     */
    public Dentist(String id, String password, String firstName, String lastName, String email, String office) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.office = office;
    }
    
    /**
     * 
     * Handles connecting to database and finding a dentist matching with an email.
     * 
     * @param email
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void selectFromDatabase(String email) throws SQLException, ClassNotFoundException{
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel","password");
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery("select * from Dentists where email='" + email + "';");
            
            resultSet.next();
            
            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), 
                    resultSet.getString(6));
            
            //Adding appointments from Appointments Table to Dentist's Array List
            
            ResultSet appointmenResultSet = statement.executeQuery("select * from Appointments where dentId='" + this.id + "';");
            
            while(appointmenResultSet.next()){
                Appointment appt = new Appointment(appointmenResultSet.getString(1), appointmenResultSet.getString(2), 
                        appointmenResultSet.getString(3), appointmenResultSet.getString(4));
                this.appointmentList.addAppointment(appt);   
            }
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    public String displayDentist(){
        return "Dentist id: " + this.id + ".\nPassword: " + this.password + "\nFirst: " + this.firstName + "\nLast: " + this.lastName +
                "\nEmail: " + this.email + "\nOffice: " + this.office;
    }
    
    /**
    *
    *Getters and Setters for Dentist Class
    *
    */
    
    public void setAll(String id, String password, String firstName, String lastName, String email, String office){
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.office = office;
    }
    
    public AppointmentList getAppointmentList(){
        return appointmentList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
    
    public static void main(String[] args) {
        Dentist d1 = new Dentist();
        
        try{
            d1.selectFromDatabase("fm@gmail.com");
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        System.out.println(d1.displayDentist());
        
    }
    
}
