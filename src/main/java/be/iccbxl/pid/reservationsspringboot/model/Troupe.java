package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "troupes")
public class Troupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 60, nullable = false)
    private String name;

    @Column(name="logo_url", length = 255, nullable = false)
    private String logoUrl;

    @OneToMany(mappedBy = "troupe")
    private List<Artist> artists = new ArrayList<>();
}

