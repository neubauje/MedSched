package com.techelevator.controller;

import com.techelevator.model.Doctor.Doctor;
import com.techelevator.model.Doctor.JdbcDoctorDao;
import com.techelevator.model.Office.JdbcOfficeDao;
import com.techelevator.model.Office.Office;
import com.techelevator.model.Review.JdbcReviewDao;
import com.techelevator.model.Review.Review;
import com.techelevator.model.User.JdbcUserDao;
import com.techelevator.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private JdbcReviewDao reviewDao;
    @Autowired
    private JdbcOfficeDao officeDao;
    @Autowired
    private JdbcDoctorDao doctorDao;
    @Autowired
    private JdbcUserDao userDao;

    @RequestMapping(path = "index", method = RequestMethod.GET)
    public String displayIndexPage(ModelMap map) {

        List<Review> reviews = reviewDao.getAllReviews();
        map.addAttribute("reviews", reviews);

        return "createReview";
    }


    @RequestMapping(value = "/createReview", method = RequestMethod.GET)
    public String medicalReviewsForm(HttpSession session, ModelMap modelMap, @RequestParam Long officeId) {
        Office reviewOffice = officeDao.getOfficeById(officeId);
        modelMap.addAttribute("office", reviewOffice);
        User user = (User) session.getAttribute("user");
        modelMap.addAttribute("username", user.getUsername());
        return "LoggedIn/Reviews/createReview";
    }

    @RequestMapping(path = "/createReview", method = RequestMethod.POST)
    public String saveUserReview(HttpSession session, @RequestParam String message, @RequestParam int rating, @RequestParam Long officeId, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        reviewDao.save(user.getId(), officeId, rating, message);
        Office office = officeDao.getOfficeById(officeId);
        modelMap.addAttribute("office", office);
        modelMap.addAttribute("officeId", officeId);
        return "redirect:/showOneOffice";
    }

    @RequestMapping(path = "/officeFeedback", method = RequestMethod.GET)
    public String displayReviews(HttpSession session, ModelMap modelHolder) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        Office office = officeDao.getOfficeById(doctor.getOfficeId());
        List<Review> sr = reviewDao.getReviewsByOfficeId(office.getOfficeId());
        modelHolder.put("reviews", sr);
        modelHolder.addAttribute("user", user);
        List<User> userList = userDao.getAllUsers();
        modelHolder.addAttribute("userList", userList);
        List<Doctor> doctorList = doctorDao.getDoctorsByOffice(office.getOfficeId());
        modelHolder.addAttribute("doctorList", doctorList);
        return "LoggedIn/Reviews/officeFeedback";
    }

    @RequestMapping(path = "/officeFeedback", method = RequestMethod.POST)
    public String respondToThisReview(HttpSession session, @RequestParam Long reviewId, ModelMap modelMap) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        Office office = officeDao.getOfficeById(doctor.getOfficeId());
        modelMap.addAttribute("reviewId", reviewId);
        return "redirect:/respondReview";
    }

    @RequestMapping(value = "/respondReview", method = RequestMethod.GET)
    public String respondToPatientReviewsForm(@RequestParam Long reviewId, HttpSession session, ModelMap modelMap) {
        Review review = reviewDao.getReview(reviewId);
        session.setAttribute("review", review);
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        modelMap.addAttribute("doctor", doctor);
        modelMap.addAttribute("review", review);
        return "LoggedIn/Reviews/respondReview";
    }

    @RequestMapping(path = "/respondReview", method = RequestMethod.POST)
    public String respondToReviews(@RequestParam String doctorResponse, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Doctor doctor = doctorDao.getOneDoctorByUserId(user.getId());
        Review review = (Review) session.getAttribute("review");
        review.setDoctorResponse(doctorResponse);
        review.setDoctorId(doctor.getDoctorId());
        reviewDao.respondToReview(review);
        return "redirect:/officeFeedback";
    }
}
