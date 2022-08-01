package carRental.domain;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarReservationRepository extends CrudRepository<CarReservation, Long> {
    CarReservation findByReservationID(long resID);

    List<CarReservation> findAll();

    @Query(value= "Select * from reservation u WHERE u.email =?1", nativeQuery = true)
    List<CarReservation> findByEmail(String email);

    @Transactional
    int deleteByReservationID(long reservationID);
}
