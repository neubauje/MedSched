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
import com.techelevator.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * SiteController
 */
@Controller
public class PatientController {
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

    @RequestMapping(path = "/findDoctors", method = RequestMethod.GET)
    public String showDoctors(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        Long patientId = patient.getPatientId();
        List<Doctor> doctorList = doctorDao.getAllDoctors();
        modelMap.addAttribute("doctors", doctorList);
        List<Office> officeList = officeDao.getAllOffices();
        modelMap.addAttribute("offices", officeList);
        List<Doctor> myDoctors = doctorDao.getDoctorsByPatient(patientId);
        modelMap.addAttribute("myDoctors", myDoctors);
        return "LoggedIn/User/usedByPatients/findDoctors";
    }

    @RequestMapping(path = "/findDoctors", method = RequestMethod.POST)
    public String pickDoctor(HttpSession session, @RequestParam Long doctorId) {
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        Long patientId = patient.getPatientId();
        if (doctorDao.isLinked(doctorId, patientId)){doctorDao.unlinkDoctorPatient(doctorId, patientId);
        String content = notificationDao.generateForDoctorDrop(patient);
        notificationDao.createNotif(patientId, doctorId, content, true);}
        else {doctorDao.linkDoctorPatient(doctorId, patientId);}
        return "redirect:/myDoctors";
    }

    @RequestMapping(path = "/myDoctors", method = RequestMethod.GET)
    public String loadMyDoctors(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        Long patientId = patient.getPatientId();
        List<Doctor> mydoctors = doctorDao.getDoctorsByPatient(patientId);
        modelMap.addAttribute("doctors", mydoctors);
        List<Office> officeList = officeDao.getAllOffices();
        modelMap.addAttribute("offices", officeList);
        return "LoggedIn/User/usedByPatients/myDoctors";
    }

    @RequestMapping(path = "/myDoctors", method = RequestMethod.POST)
    public String bookMyDoctor(HttpSession session, @RequestParam Long doctorId, ModelMap modelMap) {
        session.setAttribute("doctorId", doctorId);
        modelMap.addAttribute("doctorId", doctorId);
        Doctor doctor = doctorDao.getDoctorByDoctorId(doctorId);
        session.setAttribute("doctor", doctor);
        modelMap.addAttribute("doctor", doctor);
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        Long patientId = patient.getPatientId();
        modelMap.addAttribute("patientId", patientId);
        return "redirect:/selectDate";
    }

    @RequestMapping(path = "/patientAlerts", method = RequestMethod.GET)
    public String loadDrAlerts(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        List<Notification> myNewAlerts = notificationDao.getPatientNewAlerts(patient.getPatientId());
        modelMap.addAttribute("newAlerts", myNewAlerts);
        List<Notification> myOldAlerts = notificationDao.getPatientOldAlerts(patient.getPatientId());
        modelMap.addAttribute("oldAlerts", myOldAlerts);
        return "LoggedIn/User/usedByPatients/patientAlerts";
    }

    @RequestMapping(path = "/patientAlerts", method = RequestMethod.POST)
    public String acknowledgeDrAlert(HttpSession session, @RequestParam Long notifId) {
        Notification alert = notificationDao.getNotifById(notifId);
        notificationDao.acknowledgeAlert(alert);
        return "redirect:/patientAlerts";
    }

    @RequestMapping(path = "/editPatient", method = RequestMethod.GET)
    public String queryPatient(HttpSession session, ModelMap modelMap){
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        modelMap.addAttribute("patient", patient);
        return "LoggedIn/User/usedByPatients/editPatient";
    }

    @RequestMapping(path = "/editPatient", method = RequestMethod.POST)
    public String updatePatient(@Valid @ModelAttribute Patient patient, HttpSession session, BindingResult result, RedirectAttributes flash){
        if (result.hasErrors()) {
            flash.addFlashAttribute("patient", patient);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "patient", result);
            flash.addFlashAttribute("message", "Please fix the following errors:");
            return "LoggedIn/User/usedByPatients/editPatient";
        }
        User user = (User) session.getAttribute("user");
        patient.setUserId(user.getId());
        patientDao.updatePatient(patient);
        session.setAttribute("patient", patient);
        return "redirect:/private";
    }
}


