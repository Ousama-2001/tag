package be.iccbxl.pid.reservationsspringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The firstname must not be empty.")
    @Size(min = 2, max = 60, message = "The firstname must be between 2 and 60 characters long.")
    private String firstname;

    @NotBlank(message = "The lastname must not be empty.")
    @Size(min = 2, max = 60, message = "The lastname must be between 2 and 60 characters long.")
    private String lastname;

    @ManyToMany(mappedBy = "artists")
    private List<Type> types = new ArrayList<>();

    /**
     * Relation Many-to-One vers Troupe.
     * Un artiste appartient à une seule troupe, ou aucune.
     */
    @ManyToOne
    @JoinColumn(
            name = "troupe_id",
            foreignKey = @ForeignKey(
                    name = "fk_artist_troupe",
                    foreignKeyDefinition =
                            "FOREIGN KEY (troupe_id) REFERENCES troupes(id) " +
                                    "ON UPDATE CASCADE ON DELETE RESTRICT"
            )
    )
    private Troupe troupe;

    // Méthodes d’association pour Type (inchangées) :
    public Artist addType(Type type) {
        if (!types.contains(type)) {
            types.add(type);
            type.addArtist(this);
        }
        return this;
    }

    public Artist removeType(Type type) {
        if (types.contains(type)) {
            types.remove(type);
            type.getArtists().remove(this);
        }
        return this;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Troupe getTroupe() {
        return troupe;
    }

    public void setTroupe(Troupe troupe) {
        this.troupe = troupe;
    }
}
