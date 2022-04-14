package com.techelevator.controller;

import com.techelevator.model.Doctor.Doctor;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.Notification.JdbcNotificationDao;
import com.techelevator.model.Office.JdbcOfficeDao;
import com.techelevator.model.Office.Office;
import com.techelevator.model.TimeSlot.JdbcTimeSlotDao;
import com.techelevator.model.TimeSlot.TimeSlot;
import com.techelevator.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class OfficeDoctorController {
    public OfficeDoctorController() {
    }

    @Autowired
    private JdbcOfficeDao officeDao;
    @Autowired
    private JdbcDoctorDao doctorDao;
    @Autowired
    private JdbcNotificationDao notificationDao;
    @Autowired
    private JdbcTimeSlotDao timeSlotDao;


    @RequestMapping(path = "/myOffice", method = RequestMethod.GET)
    public String showOneOfficeSearchPostForDoctor(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        Long officeId = doctor.getOfficeId();
        Office office = officeDao.getOfficeById(officeId);
        modelMap.put("office", office);
        session.setAttribute("office", office);
        return "LoggedIn/Offices/usedByDoctors/myOffice";
    }

    @RequestMapping(name = "/editOffice", method = RequestMethod.POST)
    public String updateOfficeInformation(@Valid @ModelAttribute("office") Office office, BindingResult result, RedirectAttributes flash, HttpSession session) {
        if (result.hasErrors()) {
            flash.addFlashAttribute("office", office);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "office", result);
            flash.addFlashAttribute("message", "Please fix the following errors:");
            return "LoggedIn/Offices/usedByDoctors/editOffice";
        }
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        Office oldOffice = officeDao.getOfficeById(office.getOfficeId());
        officeDao.updateOffice(office);
        Office newOffice = officeDao.getOfficeById(office.getOfficeId());
        if (!(oldOffice.getAddress().equals(newOffice.getAddress()))) {
            List<TimeSlot> myAppointments = timeSlotDao.seeMyAgenda(doctor.getDoctorId());
            for (TimeSlot appointment : myAppointments) {
                String content = notificationDao.generateForPatientMoved(appointment, doctor, oldOffice, newOffice);
                notificationDao.createNotif(appointment.getPatientId(), doctor.getDoctorId(), content, false);
            }
        }
        return "redirect:/myOffice";
    }

    @RequestMapping(path = "/editOffice", method = RequestMethod.GET)
    public String showOneOfficeFormForDoctorToUpdate(HttpSession session, ModelMap modelMap) {
        Office office = (Office) session.getAttribute("office");
        modelMap.addAttribute("office", office);
        return "LoggedIn/Offices/usedByDoctors/editOffice";
    }

    @RequestMapping(path = "/addNewOffice", method = RequestMethod.GET)
    public String queryForOfficeDetails(HttpSession session, ModelMap modelMap){
        if (!modelMap.containsAttribute("office")) {
            modelMap.put("office", new Office());
        }
        return "LoggedIn/Offices/usedByDoctors/addNewOffice";
    }

    @RequestMapping(path = "/addNewOffice", method = RequestMethod.POST)
    public String newOfficeSubmit(@Valid @ModelAttribute("office") Office office, HttpSession session, BindingResult result, RedirectAttributes flash) {
        if (result.hasErrors()) {
            flash.addFlashAttribute("office", office);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "office", office);
            flash.addFlashAttribute("message", "Please fix the following errors:");
            return "redirect:/addNewOffice";
        }
        Office newOffice = officeDao.createNewOffice(office);
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        doctorDao.assignToOffice(doctor.getDoctorId(), office.getOfficeId());
        return "redirect:/private";
    }
}
