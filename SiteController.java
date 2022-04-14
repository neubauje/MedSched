package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.Doctor.Doctor;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.Notification.JdbcNotificationDao;
import com.techelevator.model.Office.JdbcOfficeDao;
import com.techelevator.model.Office.Office;
import com.techelevator.model.Patient.JdbcPatientDao;
import com.techelevator.model.Patient.Patient;
import com.techelevator.model.Review.JdbcReviewDao;
import com.techelevator.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
     * SiteController
     */
    @Controller
    public class SiteController {
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

        @RequestMapping(path = "/private", method = RequestMethod.GET)
        public String privatePage(ModelMap model, HttpSession session) throws UnauthorizedException {
            if (auth.userHasRole(new String[] { "Doctor", "Patient" })) {
                User user = (User) session.getAttribute("user");
                model.addAttribute("user", user);
                if (user.getRole().equals("Doctor")){
                    Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
                    model.addAttribute("doctor", doctor);
                    Office office = officeDao.getOfficeById(doctor.getOfficeId());
                    model.addAttribute("office", office);
                }
                else {
                    Patient patient = patientDao.getOnePatient(user.getId());
                    model.addAttribute("patient", patient);
                }
                Boolean alert = notificationDao.hasUnreadNotifs(user.getId());
                model.addAttribute("alert", alert);
                return "LoggedIn/User/private";
            } else {
                throw new UnauthorizedException();
            }
        }

        @RequestMapping(path = "/about", method = RequestMethod.GET)
        public String aboutPage() throws UnauthorizedException {
            return "about";
        }

    }


