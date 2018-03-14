
package Business;

import java.util.ArrayList;

/**
 *
 *
 */
public class AppointmentList {
    private ArrayList<Appointment> appointmentList = new ArrayList<>();
    
    public void addAppointment(Appointment appointment){
        appointmentList.add(appointment);
    }
    
    public void display(){
        
    }
    
    
    public ArrayList<Appointment> getAppointmentList(){
        return appointmentList;
    }
    
    
}
