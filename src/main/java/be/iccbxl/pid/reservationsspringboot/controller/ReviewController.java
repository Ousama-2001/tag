package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.model.Review;
import be.iccbxl.pid.reservationsspringboot.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @GetMapping
    public List<Review> getAllReviews() {
        return service.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable Long id) {
        return service.getReview(id);
    }

    @PostMapping
    public void addReview(@RequestBody Review review) {
        service.addReview(review);
    }

    @PutMapping("/{id}")
    public void updateReview(@PathVariable Long id, @RequestBody Review review) {
        service.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        service.deleteReview(id);
    }
}
