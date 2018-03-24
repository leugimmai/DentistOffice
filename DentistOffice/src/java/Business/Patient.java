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
 * Patient Business Object Class
 *
 * @author Miguel Quintana
 * @version 1.0
 *
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
    private boolean hasAppointment = false;

    /**
     * Initialization of Patient
     */
    public Patient() {
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
     *
     * @param id Patients id number
     * @param password Patients account password
     * @param firstName Patients first name
     * @param lastName Patients last name
     * @param address Patients address
     * @param email Patients email account
     * @param insurance Patients insurance company
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
     * Method that selects the patient from the database to get there
     * information using an email account. Then adding an appointment to the
     * class if one exists that is tied to the patient.
     *
     * @param email The patients account email
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void selectFromDatabase(String email) throws SQLException, ClassNotFoundException {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Patients where email='" + email + "';");

            resultSet.next();

            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6), resultSet.getString(7));

            ResultSet resultSetTwo = statement.executeQuery("select * from Appointments where patId='" + this.id + "';");

            resultSetTwo.next();

            this.appointment.setAll(resultSetTwo.getString(1), resultSetTwo.getString(2), resultSetTwo.getString(3), resultSetTwo.getString(4));

            hasAppointment = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that selects the patient from the database to get there
     * information using the patients id number. Then adding an appointment to the
     * class if one exists that is tied to the patient.
     *
     * @param id The patients account id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void selectFromDbWithId(String id) throws SQLException, ClassNotFoundException {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Patients where patId='" + id + "';");

            resultSet.next();

            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6), resultSet.getString(7));

            ResultSet resultSetTwo = statement.executeQuery("select * from Appointments where patId='" + this.id + "';");

            resultSetTwo.next();

            this.appointment.setAll(resultSetTwo.getString(1), resultSetTwo.getString(2), resultSetTwo.getString(3), resultSetTwo.getString(4));

            hasAppointment = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *Method that creates an appointment in the database for the patient. Then sets
     * the hasAppointment variable to true.
     * 
     * @param dateTime The appointment date and time the patient chose
     * @param dentistId The dentist the patient chose to see
     * @param procedureCode The procedure the patient is looking to get done
     */
    public void createAppointment(String dateTime, String dentistId, String procedureCode) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");
            Statement statement = connection.createStatement();

            String query = "INSERT INTO Appointments (apptDateTime, patId, dentId, procCode) VALUES('" + dateTime + "','" + this.id + "','" + dentistId + "','"
                    + procedureCode + "');";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();

            hasAppointment = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Method that updates any of the patients information passed to it in the database.
     * By using the id parameter passed to it.
     * 
     * @param id The patients account id
     * @param password The patients account password
     * @param firstName The patients first name
     * @param lastName The patients last name
     * @param address The patients address
     * @param email The patients account password
     * @param insurance The patients insurance provider
     */
    public void updatePatient(String id, String password, String firstName, String lastName, String address, String email, String insurance) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");

            String query = "UPDATE Patients SET firstName='" + firstName + "', lastName='" + lastName + "', email='" + email + "', passwd='" + password
                    + "', addr='" + address + "', insCo='" + insurance + "' WHERE patId='" + id + "';";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *Method that deletes an appointment belonging to the patient from the database by using
     * the id parameter passed to it.
     * @param id The patients account id
     */
    public void deleteAppointment(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");

            String query = "DELETE FROM Appointments WHERE patId='" + id + "';";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.execute();

            hasAppointment = false;

            this.appointment = new Appointment();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *Method that returns back the patients account information
     * @return A string containing the patients account information
     */
    public String displayPatient() {
        return "Patient id: " + this.id + ".\nPassword: " + this.password + "\nFirst: " + this.firstName + "\nLast: " + this.lastName
                + "\nEmail: " + this.email + "\nInsurance: " + this.insurance;
    }

    /**
     * Method that sets all the patients parameters to the class.
     *
     * @param id The patients account id
     * @param lastName The patients last name
     * @param password The patients account password
     * @param firstName The patients first name
     * @param address The patients address
     * @param email The patients email account
     * @param insurance The patients insurance  provider
     */
    public void setAll(String id, String password, String firstName, String lastName, String address, String email, String insurance) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.insurance = insurance;
    }

    /**
     *Method that returns back the patients appointment class if one exists.
     * @return The patients appointment
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     *Method that returns back the boolean value if they had an appointment or not
     * @return boolean value
     */
    public boolean hasAppointment() {
        return hasAppointment;
    }

    /**
     *Method that returns back the patients id
     * @return string of patients id
     */
    public String getId() {
        return id;
    }

    /**
     *Method that sets the patients account id
     * @param id The patients account id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *Method that returns back the patients account password
     * @return string of patients password
     */
    public String getPassword() {
        return password;
    }

    /**
     *Method that sets the patients account password
     * @param password The patients account password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *Method that returns back the patients first name
     * @return string of patients first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *Method that sets the patients first name
     * @param firstName The patients first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *Method that returns back the patient
     * @return string of patients last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *Method that sets the patients last name
     * @param lastName The patients last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *Method that returns the patients address
     * @return string of patients address 
     */ 
    public String getAddress() {
        return address;
    }

    /**
     *Method that sets the patients address
     * @param address The patients address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *Method that returns back the patients email account
     * @return string of patients email account
     */
    public String getEmail() {
        return email;
    }

    /**
     *Method that sets the patients email account
     * @param email The patients email account
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *Method that returns back the patients insurance provider 
     * @return string of patients insurance
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     *Method that sets the patients insurance provider
     * @param insurance The patients insurance provider 
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

}
