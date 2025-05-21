package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Artist;
import be.iccbxl.pid.reservationsspringboot.model.Troupe;
import be.iccbxl.pid.reservationsspringboot.repository.ArtistRepository;
import be.iccbxl.pid.reservationsspringboot.repository.TroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private TroupeRepository troupeRepository;

    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artists::add);
        return artists;
    }

    public Artist getArtist(long id) {
        return artistRepository.findById(id);
    }

    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public void updateArtist(long id, Artist artist) {
        artistRepository.save(artist);
    }

    public void deleteArtist(long id) {
        artistRepository.deleteById(id);
    }
    /**
     * Assigne (ou désaffilie) une troupe à un artiste.
     *
     * @param artistId l'id de l'artiste
     * @param troupeId l'id de la troupe à affecter, ou null pour désaffilier
     */
    public void assignToTroupe(long artistId, Long troupeId) {
        Artist artist = getArtist(artistId);

        if (troupeId != null) {
            Troupe troupe = troupeRepository.findById(troupeId)
                    .orElseThrow(() -> new NoSuchElementException("Troupe introuvable : " + troupeId));
            artist.setTroupe(troupe);
        } else {
            artist.setTroupe(null);
        }

        artistRepository.save(artist);
    }
}

