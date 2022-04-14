package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.model.Doctor.Doctor;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.Notification.JdbcNotificationDao;
import com.techelevator.model.Notification.Notification;
import com.techelevator.model.Office.JdbcOfficeDao;
import com.techelevator.model.Office.Office;
import com.techelevator.model.Patient.JdbcPatientDao;
import com.techelevator.model.Patient.Patient;
import com.techelevator.model.Review.JdbcReviewDao;
import com.techelevator.model.TimeSlot.JdbcTimeSlotDao;
import com.techelevator.model.TimeSlot.TimeSlot;
import com.techelevator.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * SiteController
 */
@Controller
public class DoctorController {
    @Autowired
    private AuthProvider auth;
    @Autowired
    private JdbcDoctorDao doctorDao;
    @Autowired
    private JdbcPatientDao patientDao;
    @Autowired
    private JdbcOfficeDao officeDao;
    @Autowired
    private JdbcReviewDao reviewDao;
    @Autowired
    private JdbcNotificationDao notificationDao;
    @Autowired
    private JdbcTimeSlotDao timeSlotDao;


    @RequestMapping(path = "/myPatients", method = RequestMethod.GET)
    public String loadMyPatients(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        Long doctorId = doctor.getDoctorId();
        List<Patient> mypatients = patientDao.getPatientsByDoctor(doctorId);
        modelMap.addAttribute("patients", mypatients);
        return "LoggedIn/User/usedByDoctors/myPatients";
    }

    @RequestMapping(path = "/myPatients", method = RequestMethod.POST)
    public String selectPatient(ModelMap modelMap, @RequestParam Long patientId, HttpSession session){
        Patient patient = patientDao.getPatientByPid(patientId);
        modelMap.put("patient", patient);
        session.setAttribute("patient", patient);
        return "redirect:/patientDetails";
    }

    @RequestMapping(path = "/patientDetails", method = RequestMethod.GET)
    public String seePatientInfo(ModelMap modelMap, HttpSession session){
        Patient patient = (Patient) session.getAttribute("patient");
        modelMap.put("patient", patient);
        return "LoggedIn/User/usedByDoctors/patientDetails";
    }

    @RequestMapping(path = "/doctorAlerts", method = RequestMethod.GET)
    public String loadDrAlerts(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        List<Notification> myNewAlerts = notificationDao.getDoctorNewAlerts(doctor.getDoctorId());
        modelMap.addAttribute("newAlerts", myNewAlerts);
        List<Notification> myOldAlerts = notificationDao.getDoctorOldAlerts(doctor.getDoctorId());
        modelMap.addAttribute("oldAlerts", myOldAlerts);
        return "LoggedIn/User/usedByDoctors/doctorAlerts";
    }

    @RequestMapping(path = "/doctorAlerts", method = RequestMethod.POST)
    public String acknowledgeDrAlert(@RequestParam Long notifId) {
        Notification alert = notificationDao.getNotifById(notifId);
        notificationDao.acknowledgeAlert(alert);
        return "redirect:/doctorAlerts";
    }

    @RequestMapping(path = "/editDoctor", method = RequestMethod.GET)
    public String queryDoctor(HttpSession session, ModelMap modelMap){
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        modelMap.addAttribute("doctor", doctor);
        List<Office> officeList = officeDao.getAllOffices();
        modelMap.addAttribute("offices", officeList);
        return "LoggedIn/User/usedByDoctors/editDoctor";
    }

    @RequestMapping(path = "/editDoctor", method = RequestMethod.POST)
    public String updateDoctor(HttpSession session, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String specialty, @RequestParam Long officeId){
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        Office oldOffice = officeDao.getOfficeById(doctor.getOfficeId());
        Office newOffice = officeDao.getOfficeById(officeId);
        if (oldOffice.getOfficeId() != newOffice.getOfficeId()) {
            List<TimeSlot> myAppointments = timeSlotDao.seeMyAgenda(doctor.getDoctorId());
            for (TimeSlot appointment: myAppointments) {
                String content = notificationDao.generateForPatientMoved(appointment, doctor, oldOffice, newOffice);
                notificationDao.createNotif(appointment.getPatientId(), doctor.getDoctorId(), content, false);
            }
        }
        doctorDao.updateDoctor(doctor.getDoctorId(), firstName, lastName, specialty, officeId);
        Doctor newDoctor = doctorDao.getDoctorByDoctorId(doctor.getDoctorId());
        if (!(doctor.getFirstName().equals(newDoctor.getFirstName())) || !(doctor.getLastName().equals(newDoctor.getLastName())))
        {
            List<TimeSlot> myAppointments = timeSlotDao.seeMyAgenda(doctor.getDoctorId());
            for (TimeSlot appointment: myAppointments) {
                String content = notificationDao.generateForPatientNameChange(appointment, doctor, newDoctor);
                notificationDao.createNotif(appointment.getPatientId(), doctor.getDoctorId(), content, false);
            }
        }
        return "redirect:/private";
    }

}


