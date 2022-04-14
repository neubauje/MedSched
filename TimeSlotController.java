package com.techelevator.controller;


import com.techelevator.model.Doctor.Doctor;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.Notification.JdbcNotificationDao;
import com.techelevator.model.Patient.JdbcPatientDao;
import com.techelevator.model.Patient.Patient;
import com.techelevator.model.TimeSlot.JdbcTimeSlotDao;
import com.techelevator.model.TimeSlot.TimeSlot;
import com.techelevator.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TimeSlotController {

    @Autowired
    private JdbcDoctorDao doctorDao;
    @Autowired
    private JdbcPatientDao patientDao;
    @Autowired
    private JdbcTimeSlotDao timeSlotDao;
    @Autowired
    private JdbcNotificationDao notificationDao;

    @RequestMapping(path = "/listAvailabilities", method = RequestMethod.GET)
    public String seeFreeSlots(ModelMap model, HttpSession session) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        Long doctorId = doctor.getDoctorId();
        LocalDate date = (LocalDate) session.getAttribute("searchDate");
        List<TimeSlot> timeSlots;
        if (date == null){
            timeSlots = timeSlotDao.findFreeSlots(doctorId);
        }
        else {
            timeSlots = timeSlotDao.findFreeSlots(date, doctorId);
        }
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("doctor", doctor);
        return "LoggedIn/Schedules/usedByPatients/listAvailabilities";
    }

    @RequestMapping(path = "/listAvailabilities", method = RequestMethod.POST)
    public String pickSlot(HttpSession session, @RequestParam Long timeslotId, ModelMap modelMap) {
        modelMap.addAttribute("timeSlotId", timeslotId);
        session.setAttribute("selectedTimeSlotId", timeslotId);
        return "redirect:/addDetails";
    }

    @RequestMapping(path = "/selectDate", method = RequestMethod.GET)
    public String showCalendar(HttpSession session, ModelMap modelMap) {
        Long doctorId = (Long) session.getAttribute("doctorId");
        Doctor doctor = doctorDao.getDoctorByDoctorId(doctorId);
        modelMap.addAttribute("doctor", doctor);
        session.setAttribute("doctor", doctor);
        return "LoggedIn/Schedules/usedByPatients/selectDate";
    }

    @RequestMapping(path = "/selectDate", method = RequestMethod.POST)
    public String dateSelected(HttpSession session, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, ModelMap modelMap) {
        modelMap.addAttribute("date", date);
        session.setAttribute("searchDate", date);
        return "redirect:/listAvailabilities";
    }

    @RequestMapping(path = "/viewMyAgenda", method = RequestMethod.GET)
    public String seeBookings(HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        List<TimeSlot> myIncomingAppointments = timeSlotDao.seeMyAgenda(doctor.getDoctorId());
        model.addAttribute("timeSlots", myIncomingAppointments);
        List<Patient> myPatients = patientDao.getPatientsByDoctor(doctor.getDoctorId());
        model.addAttribute("patients", myPatients);
        return "LoggedIn/Schedules/usedByDoctors/viewMyAgenda";
    }

    @RequestMapping(path = "/viewMyAgenda", method = RequestMethod.POST)
    public String cancelBooking(HttpSession session, @RequestParam Long timeslotId){
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        TimeSlot slot = timeSlotDao.getTimeSlot(timeslotId);
        Patient patient = patientDao.getPatientByPid(slot.getPatientId());
        String content = notificationDao.generateContentForPatientCancel(slot, doctor);
        notificationDao.createNotif(patient.getPatientId(), doctor.getDoctorId(), content, false);
        timeSlotDao.cancelAppointment(slot);
        return "redirect:/viewMyAgenda";
    }

    @RequestMapping(path = "/updateMySchedule", method = RequestMethod.POST)
    public String updateDoctorAvailability(HttpSession session, ModelMap model, @RequestParam Long timeslotId) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        TimeSlot slot = timeSlotDao.getTimeSlot(timeslotId);
        if (slot.isAssigned()) {
            Patient patient = patientDao.getPatientByPid(slot.getPatientId());
            String content = notificationDao.generateContentForPatientCancel(slot, doctor);
            notificationDao.createNotif(patient.getPatientId(), doctor.getDoctorId(), content, false);
        }
        timeSlotDao.deleteTimeSlot(timeslotId);
        return "redirect:/updateMySchedule";
    }

    @RequestMapping(path = "/updateMySchedule", method = RequestMethod.GET)
    public String showFormToUpdateDoctorSchedule(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        List<TimeSlot> timeSlots = timeSlotDao.seeMySchedule(doctor.getDoctorId());
        List<Patient> myPatients = patientDao.getPatientsByDoctor(doctor.getDoctorId());
        modelMap.addAttribute("patients", myPatients);
        modelMap.addAttribute("timeslots", timeSlots);
        return "LoggedIn/Schedules/usedByDoctors/updateMySchedule";
    }

    @RequestMapping(path = "/createSlots", method = RequestMethod.GET)
    public String makeNewSlot(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        modelMap.addAttribute("doctor", doctor);
        return "LoggedIn/Schedules/usedByDoctors/createSlots";
    }

    @RequestMapping(path = "/createSlots", method = RequestMethod.POST)
    public String saveNewSlot(HttpSession session, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                              @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime startTime,
                              @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime endTime) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        timeSlotDao.createFreeSlot(doctor.getDoctorId(), date, startTime, endTime);
        return "redirect:/updateMySchedule";
    }

    @RequestMapping(path = "/addDetails", method = RequestMethod.POST)
    public String processAppointmentForPatient(@RequestParam(required = false) String description,
                                               HttpSession session) {
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        Long patientId = patient.getPatientId();
        TimeSlot timeSlot = (TimeSlot) session.getAttribute("timeslot");
        timeSlot.setDescription(description);
        timeSlot.setPatientId(patientId);
        Long doctorId = timeSlot.getDoctorId();
        timeSlotDao.updateAppointByPatient(timeSlot);
        String content = notificationDao.generateContentForDoctorCreate(timeSlot, patient);
        notificationDao.createNotif(patientId, doctorId, content, true);
        return "redirect:/confirmBooked";
    }


    @RequestMapping(path = "/addDetails", method = RequestMethod.GET)
    public String loadDetailsBox(HttpSession session, ModelMap modelMap) {
        Long timeslotId = (Long) session.getAttribute("selectedTimeSlotId");
        TimeSlot timeSlot = timeSlotDao.getTimeSlot(timeslotId);
        session.setAttribute("timeslot", timeSlot);
        modelMap.addAttribute("timeslot", timeSlot);
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        modelMap.addAttribute("doctor", doctor);
        return "LoggedIn/Schedules/usedByPatients/addDetails";
    }

    @RequestMapping(path = "/myAppointments", method = RequestMethod.GET)
    public String seeAppointments(HttpSession session, ModelMap model) {
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        List<TimeSlot> myBookings = timeSlotDao.seeMyAppointments(patient.getPatientId());
        model.addAttribute("bookedTimeSlots", myBookings);
        List<Doctor> myDoctors = doctorDao.getDoctorsByPatient(patient.getPatientId());
        model.addAttribute("doctors", myDoctors);
        return "LoggedIn/Schedules/usedByPatients/myAppointments";

    }

    @RequestMapping(path = "/myAppointments", method = RequestMethod.POST)
    public String updatePatientAppointment(HttpSession session, ModelMap model, @RequestParam Long timeslotId) {
        TimeSlot timeSlot = timeSlotDao.getTimeSlot(timeslotId);
        Doctor doctor = doctorDao.getDoctorByDoctorId(timeSlot.getDoctorId());
        Patient patient = patientDao.getPatientByPid(timeSlot.getPatientId());
        String content = notificationDao.generateContentForDoctorCancel(timeSlot, patient);
        notificationDao.createNotif(patient.getPatientId(), doctor.getDoctorId(), content, true);
        timeSlotDao.cancelAppointment(timeSlot);
        return "redirect:/myAppointments";
    }

    @RequestMapping(path = "/confirmBooked", method = RequestMethod.GET)
    public String showConfirmation() {
        return "LoggedIn/Schedules/usedByPatients/confirmBooked";
    }

}
