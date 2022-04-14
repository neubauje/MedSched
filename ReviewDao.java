package com.techelevator.model.Review;

import java.time.LocalDate;
import java.util.List;

public interface ReviewDao {

        public List<Review> getAllReviews();

      public void save(Long userId, Long officeId, int rating, String message);

        public List<Review> respond(Long officeId);

        public void respondToReview(Review reviewUpdate);

        public Review getReview(long reviewId);

//  public void save(long reviewId, String username, String message, long rating, LocalDate dateSubmitted);
}

