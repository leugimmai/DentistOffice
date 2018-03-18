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
 * Patient Business Object Class
 */
public class Patient {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String insurance;
    private Appointment appointment = new Appointment();
    
    
    public Patient(){
        this.id = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.email = "";
        this.insurance = "";
    }
    
    /**
     * Constructor for Patient Class
     * @param id
     * @param password
     * @param firstName
     * @param lastName
     * @param address
     * @param email
     * @param insurance 
     */
    
    public Patient(String id, String password, String firstName, String lastName, String address, String email, String insurance) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.insurance = insurance;
    }
    
    /**
     * Handles connecting to database and finding a patient matching with an email.
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
            
            ResultSet resultSet = statement.executeQuery("select * from Patients where email='" + email + "';");
            
            resultSet.next();
            
            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), 
                    resultSet.getString(6), resultSet.getString(7));
            
            ResultSet resultSetTwo = statement.executeQuery("select * from Appointments where patId='" + this.id + "';");
            
            resultSetTwo.next();
            
            this.appointment.setAll(resultSetTwo.getString(1), resultSetTwo.getString(2), resultSetTwo.getString(3), resultSetTwo.getString(4));
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void selectFromDbWithId(String id) throws SQLException, ClassNotFoundException{
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel","password");
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery("select * from Patients where patId='" + id + "';");
            
            resultSet.next();
            
            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), 
                    resultSet.getString(6), resultSet.getString(7));
            
            ResultSet resultSetTwo = statement.executeQuery("select * from Appointments where patId='" + this.id + "';");
            
            resultSetTwo.next();
            
            this.appointment.setAll(resultSetTwo.getString(1), resultSetTwo.getString(2), resultSetTwo.getString(3), resultSetTwo.getString(4));
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public String displayPatient(){
        return "Patient id: " + this.id + ".\nPassword: " + this.password + "\nFirst: " + this.firstName + "\nLast: " + this.lastName +
                "\nEmail: " + this.email + "\nInsurance: " + this.insurance;
    }
    
    
    /**
     * Getters and Setters for Patient class
     * @return 
     */
    
    public void setAll(String id, String password, String firstName, String lastName, String address, String email, String insurance){
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.insurance = insurance;
    }
    
    public Appointment getAppointment(){
        return appointment;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
    
    
//    public static void main(String[] args) {
//        Patient p1 = new Patient();
//        
//        try{
//            p1.selectFromDatabase("mstew@gmail.com");
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        
//        System.out.println(p1.displayPatient());
//        
//        System.out.println(p1.appointment.displayAppointment());
//    }
    
}
