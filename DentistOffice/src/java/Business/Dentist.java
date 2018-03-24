/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * Dentist Business Object Class
 *
 * @author Miguel Quintana
 * @version 1.0
 */
public class Dentist {

    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String office;
    private AppointmentList appointmentList = new AppointmentList();

    /**
     * Initialization Constructor for dentist class
     */
    public Dentist() {
        this.id = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.office = "";

    }

    /**
     * Constructor method for Dentist
     *
     * @param id The dentists id
     * @param password The dentsits password
     * @param firstName The dentists first name
     * @param lastName The dentists last name
     * @param email The dentists email
     * @param office The dentists office number
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
     * Method that handles connecting to database and finding a dentist matching
     * with their email to set the parameters belonging to the dentist. Along
     * with that finding appointments that are associated with the dentist and
     * adds to the appointment list class.
     *
     * @param email The dentists email
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void selectFromDatabase(String email) throws SQLException, ClassNotFoundException {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Dentists where email='" + email + "';");

            resultSet.next();

            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6));

            //Adding appointments from Appointments Table to Dentist's Array List
            ResultSet appointmenResultSet = statement.executeQuery("select * from Appointments where dentId='" + this.id + "';");

            while (appointmenResultSet.next()) {
                Appointment appt = new Appointment(appointmenResultSet.getString(1), appointmenResultSet.getString(2),
                        appointmenResultSet.getString(3), appointmenResultSet.getString(4));
                this.appointmentList.addAppointment(appt);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that handles connecting to database and finding a dentist matching
     * with their id to set the parameters belonging to the dentist.
     *
     * @param id The dentists Id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void selectFromDatabaseWithId(String id) throws SQLException, ClassNotFoundException {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Dentists where id='" + id + "';");

            resultSet.next();

            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * Method that updates the dentists account information in the database.
     * 
     * @param password The dentists password
     * @param firstName The dentists first name
     * @param lastName The dentists last name
     * @param email The dentists email account
     * @param officeNo The dentists office number
     * @param dentistId The dentists id number
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateDentist(String password, String firstName, String lastName, String email, String officeNo, String dentistId) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");

            String query = "UPDATE Dentists SET firstName='" + firstName + "', lastName='" + lastName + "', email='" + email + "', passwd='" + password + "', office='"
                    + officeNo + "' WHERE id='" + dentistId + "';";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();

            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.office = officeNo;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that returns back a string containing all the dentists information
     *
     * @return String containing dentists information
     */
    public String displayDentist() {
        return "Dentist id: " + this.id + ".\nPassword: " + this.password + "\nFirst: " + this.firstName + "\nLast: " + this.lastName
                + "\nEmail: " + this.email + "\nOffice: " + this.office;
    }

    /**
     *
     * Method that sets parameters for the dentist class
     *
     * @param id The dentists id number
     * @param password The dentists password
     * @param firstName The dentists first name
     * @param lastName The dentists last name
     * @param email The dentists email account
     * @param office The dentists office number
     */
    public void setAll(String id, String password, String firstName, String lastName, String email, String office) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.office = office;
    }

    /**
     * Method that returns the appointment list object that was created
     *
     * @return The dentists appointment
     */
    public AppointmentList getAppointmentList() {
        return appointmentList;
    }

    /**
     * Method that returns back the dentists id
     *
     * @return The dentists Id
     */
    public String getId() {
        return id;
    }

    /**
     * Method that sets the dentists id
     *
     * @param id The dentists id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method that returns back the dentists account password
     *
     * @return The dentists account password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method that sets the dentists password
     *
     * @param password The dentists account password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method that returns back the dentists first name
     *
     * @return The dentists first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method that sets the dentists first name
     *
     * @param firstName The dentists first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method that returns back the dentists last name
     *
     * @return The dentists last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method that sets the dentists last name
     *
     * @param lastName The dentists last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method that returns back the dentists email
     *
     * @return The dentists email account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method that sets the dentists email account
     *
     * @param email The dentists email account
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method that returns back the dentists office number
     *
     * @return The dentists office number
     */
    public String getOffice() {
        return office;
    }

    /**
     * Method that sets the dentists office number
     *
     * @param office The dentists office number
     */
    public void setOffice(String office) {
        this.office = office;
    }

}
