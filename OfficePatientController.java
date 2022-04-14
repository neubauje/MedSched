package com.techelevator.controller;


import com.techelevator.model.Doctor.Doctor;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.Office.JdbcOfficeDao;
import com.techelevator.model.Office.Office;
import com.techelevator.model.Patient.JdbcPatientDao;
import com.techelevator.model.Patient.Patient;
import com.techelevator.model.Review.JdbcReviewDao;
import com.techelevator.model.Review.Review;
import com.techelevator.model.User.JdbcUserDao;
import com.techelevator.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OfficePatientController {
    public OfficePatientController() {
    }

    @Autowired
    private JdbcOfficeDao officeDao;
    @Autowired
    private JdbcDoctorDao doctorDao;
    @Autowired
    private JdbcReviewDao reviewDao;
    @Autowired
    private JdbcUserDao userDao;
    @Autowired
    private JdbcPatientDao patientDao;

    @RequestMapping(path = "/oneOfficeSearch", method = RequestMethod.GET)
    public String showOneOfficeSearchPage() {

        return "oneOfficeSearch";
    }

    @RequestMapping(path = "/showOneOfficeByName", method = RequestMethod.GET)
    public String showOneOfficeSearchPost(@RequestParam String officeName, ModelMap modelMap) {
//        if (officeName == null) {
//            List<Office> offices = officeDao.getAllOffices();
//            modelMap.put("office", offices);
//        } else {
            modelMap.put("office", officeDao.getOffice(officeName));

        return "showOneOffice";
    }

    @RequestMapping(path = "/showOneOffice", method = RequestMethod.GET)
    public String showOneOfficeSearchPost(ModelMap modelMap, HttpSession session, @RequestParam Long officeId) {
        Office office = officeDao.getOfficeById(officeId);
        modelMap.addAttribute("office", office);
        List<Doctor> doctors = doctorDao.getAllDoctors();
        modelMap.addAttribute("doctors", doctors);
        List<Doctor> officeDoctors = doctorDao.getDoctorsByOffice(officeId);
        modelMap.addAttribute("officeDoctors", officeDoctors);
        List<Review> officeReviews = reviewDao.getReviewsByOfficeId(officeId);
        modelMap.addAttribute("reviews", officeReviews);
        List<User> users = userDao.getAllUsers();
        modelMap.addAttribute("users", users);
        return "LoggedIn/Offices/usedByPatients/showOneOffice";
    }

    @RequestMapping(value = "/showAllOffices", method = RequestMethod.GET)
    public String showAllOfficesPage(ModelMap map) {

        List<Office> offices = officeDao.getAllRealOffices();
        List<Office> ratedOffices = new ArrayList<>();
        for (Office office: offices) {
            office = reviewDao.getAverageRating(office);
            ratedOffices.add(office);
        }
        map.put("offices", ratedOffices);

        List<Review> allReviews = reviewDao.getAllReviews();
        map.put("reviews", allReviews);
        return "LoggedIn/Offices/usedByPatients/showAllOffices";
    }

    @RequestMapping(path = "/showAllOffices", method = RequestMethod.POST)
    public String selectAnOffice(HttpSession session, @RequestParam Long officeId, ModelMap modelMap){
        modelMap.addAttribute("officeId", officeId);
        session.setAttribute("officeId", officeId);
        return "redirect:/showOneOffice";
    }

    @RequestMapping(path = "/myOffices", method = RequestMethod.POST)
    public String reviewAnOffice(HttpSession session, @RequestParam Long officeId, ModelMap modelMap){
        modelMap.addAttribute("officeId", officeId);
        return "redirect:/createReview";
    }

    @RequestMapping(path = "/myOffices", method = RequestMethod.GET)
    public String showMyOffices(HttpSession session, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Patient patient = patientDao.getOnePatient(user.getId());
        Long patientId = patient.getPatientId();
        List<Doctor> mydoctors = doctorDao.getDoctorsByPatient(patientId);
        modelMap.addAttribute("doctors", mydoctors);
        List<Office> officeList = officeDao.getOfficesByPatientId(patientId);
        modelMap.addAttribute("offices", officeList);
        return "LoggedIn/Offices/usedByPatients/myOffices";
    }

}
