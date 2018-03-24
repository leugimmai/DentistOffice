package Business;
import java.util.ArrayList;

/**
 *
 *Appointment List Class - Creates an array list from selecting appointments from
 * the appointment class.
 * @author Miguel Quintana
 * @see Appointment
 * @version 1.0
 */

public class AppointmentList {
    private ArrayList<Appointment> appointmentList = new ArrayList<>();
    
    /**
     * This method adds an Appointment to the array list object belonging to this
     * class.
     * @param appointment An instance of the appointment class
     */
    
    public void addAppointment(Appointment appointment){
        appointmentList.add(appointment);
    }
    
    /**
     * Method that returns back the ArrayList filled with appointments.
     * @return AppointmentList array list of appointments
     */
    
    public ArrayList<Appointment> getAppointmentList(){
        return appointmentList;
    }
    
    
}
