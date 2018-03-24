package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * Appointment Class - Class for managing appointments
 *
 * @author Miguel Quintana
 * @version 1.0
 */
public class Appointment {

    private String apptDateTime;
    private String patientId;
    private String dentistId;
    private String procedureCode;
    private boolean isEmpty = false;

    /**
     * Initial Constructor method that takes no parameters.
     */
    public Appointment() {
        this.apptDateTime = "";
        this.patientId = "";
        this.dentistId = "";
        this.procedureCode = "";
    }

    /**
     * Constructor Method for the Appointment class
     *
     * @param apptDateTime The date and time of appointment
     * @param patientId The patient belonging to the appointment
     * @param dentistId The dentist doing the procedure
     * @param procedureCode The procedure being done
     */
    public Appointment(String apptDateTime, String patientId, String dentistId, String procedureCode) {
        this.apptDateTime = apptDateTime;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.procedureCode = procedureCode;
    }

    /**
     * Method that handles selecting a patient and finding an appointment in the
     * database that pertains to that patient.
     *
     * @param patientId The patient belonging to the appointment
     */
    public void selectDbPatientAppt(String patientId) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Appointments where patId='" + patientId + "';");

            resultSet.next();

            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));

            this.isEmpty = false;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that returns back a string of the appointment object that was made.
     *
     * @return string showing parameters of appointment
     */
    public String displayAppointment() {
        return "Appointment: " + this.apptDateTime + ".\nPatient: " + this.patientId + "\nDoctor: " + this.dentistId
                + "\nProcedure: " + this.procedureCode;
    }

    /**
     *
     *Method that sets all parameters for the appointment.
     *
     * @param apptDateTime The date and time of appointment
     * @param patientId The patient belonging to the appointment
     * @param procedureCode The procedure being done
     * @param dentistId The dentist doing the procedure
     */
    public void setAll(String apptDateTime, String patientId, String dentistId, String procedureCode) {
        this.apptDateTime = apptDateTime;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.procedureCode = procedureCode;
    }

    /**
     *Method that checks if an appointment exists.
     * @return boolean value of appointment. If an appointment existed in the database
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }

    /**
     *Method that returns back the appointment date and time.
     * @return Appointment date and time - the day and time of the appointment
     */
    public String getApptDateTime() {
        return apptDateTime;
    }

    /**
     *Method that sets the appointment date and time.
     * @param apptDateTime The date and time of the appointment
     */
    public void setApptDateTime(String apptDateTime) {
        this.apptDateTime = apptDateTime;
    }

    /**
     *Method that returns back the patients id belonging to the appointment.
     * @return Patient id - The patient belonging to the appointment
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     *Method that sets the patients id belonging to the appointment.
     * @param patientId The patient belonging to the appointment
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     *Method that returns back the patients id belonging to the appointment.
     * @return dentist id - The dentist performing the procedure
     */
    public String getDentistId() {
        return dentistId;
    }

    /**
     *Method that sets the dentists id belonging to the appointment.
     * @param dentistId The dentist doing the procedure
     */
    public void setDentistId(String dentistId) {
        this.dentistId = dentistId;
    }

    /**
     *Method that returns back the procedure code belonging to the appointment.
     * @return procedure code - The procedure being done
     */
    public String getProcedureCode() {
        return procedureCode;
    }

    /**
     *Method that sets the procedure code belonging to the appointment.
     * @param procedureCode The procedure being done
     */
    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

}
