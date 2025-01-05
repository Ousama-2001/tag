package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@Entity
@Table(name = "localities")
public class Locality {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String postalCode;
    private String locality;
}
