package com.techelevator.model.Review;


import com.techelevator.model.Office.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReviewDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> allReviews = new ArrayList<>();
        String sqlSelectAllReviews = "Select * from review";
        SqlRowSet medReview = jdbcTemplate.queryForRowSet(sqlSelectAllReviews);
        while (medReview.next()) {
            Review review = new Review();
            review.setReviewId(medReview.getLong("review_id"));
            review.setUserId(medReview.getLong("user_id"));
            review.setMessage(medReview.getString("message"));
            review.setRating(medReview.getInt("rating"));
            review.setDateSubmitted(medReview.getDate("date_submitted").toLocalDate());
            review.setDoctorResponse(medReview.getString("doctor_response"));
            review.setDoctorId(medReview.getLong("doctor_id"));
            allReviews.add(review);

        }
        return allReviews;
    }

    @Override
    public void save(Long userId, Long officeId, int rating, String message) {
        String sqlInsertReview = "Insert into review (user_id, office_id, rating, message, date_submitted) " +
                "values (?, ?, ?, ?, current_date)";
        jdbcTemplate.update(sqlInsertReview, userId, officeId, rating, message);
    }


    private Long getNextId() {
        String sqlSelectNextId = "SELECT NEXTVAL('seq_review_id')";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
        Long id = null;
        if (results.next()) {
            id = results.getLong(1);
        } else {
            throw new RuntimeException("Review must be saved");
        }
        return id;
    }


    @Override
    public List<Review> respond(Long officeId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "select review_id from office join review on office.office_id = review.office_id " +
                "where office.office_id = ?;";
        SqlRowSet medResponds = jdbcTemplate.queryForRowSet(sql, officeId);
        while (medResponds.next()){
            reviews.add(mapRowToReviews(medResponds));
        }
        return reviews;

    }
    public Review getReview(long reviewId){
        String sql = "select * from review where review_id = ?;";
        SqlRowSet getReviews = jdbcTemplate.queryForRowSet(sql, reviewId);
        Review review = null;
        if(getReviews.next()){
            review = mapRowToReviews(getReviews);
        }
        return review;
    }

    public void respondToReview(Review reviewUpdate){
      String sql = "Update review SET doctor_response = ?, doctor_id = ? where review_id = ?";
      jdbcTemplate.update(sql, reviewUpdate.getDoctorResponse(), reviewUpdate.getDoctorId(), reviewUpdate.getReviewId());

    }


    private Review mapRowToReviews(SqlRowSet medResponds) {
        Review review = new Review();
        review.setReviewId(medResponds.getLong("review_id"));
        review.setUserId(medResponds.getLong("user_id"));
        review.setMessage(medResponds.getString("message"));
        review.setRating(medResponds.getLong("rating"));
        review.setDateSubmitted(medResponds.getDate("date_submitted").toLocalDate());
        review.setOfficeId(medResponds.getLong("office_id"));
        review.setDoctorId(medResponds.getLong("doctor_id"));
        review.setDoctorResponse(medResponds.getString("doctor_response"));

        return review;
    }

    public List<Review> getReviewsByOfficeId(Long officeId){
        List<Review> ourFeedback = new ArrayList<>();
        String sql = "SELECT * from review where office_id = ? order by date_submitted desc;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, officeId);
        while (results.next()){
            ourFeedback.add(mapRowToReviews(results));
        }
        return ourFeedback;
    }

    public Office getAverageRating(Office office){
        List<Review> reviews = getReviewsByOfficeId(office.getOfficeId());
        int average = 0;
        if (reviews.size() > 0) {
            for (int i = 0; i < reviews.size(); i++) {
                average = (int) (average + reviews.get(i).getRating());
            }
            average = average / reviews.size();
        }
        office.setAverageRating(average);
        return office;
    }

}
