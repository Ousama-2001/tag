package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @Column(columnDefinition = "TEXT")
    private String review;

    @Column(columnDefinition = "TINYINT UNSIGNED")
    private Integer stars;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
