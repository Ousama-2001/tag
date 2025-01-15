package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Review;
import be.iccbxl.pid.reservationsspringboot.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        repository.findAll().forEach(reviews::add);
        return reviews;
    }

    public Review getReview(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addReview(Review review) {
        repository.save(review);
    }

    public void updateReview(Long id, Review review) {
        repository.save(review);
    }

    public void deleteReview(Long id) {
        repository.deleteById(id);
    }
}
