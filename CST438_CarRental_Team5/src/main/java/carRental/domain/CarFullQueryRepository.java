package carRental.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFullQueryRepository extends CrudRepository<CarFullQuery, Long> {

  @Query(value = "SELECT reservation.reservationID, reservation.email, customer.f_Name, customer.l_Name,"
      + " reservation.pickup_Location, reservation.pickup_Date,"
      + " reservation.return_Location, reservation.return_Date,"
      + " car.type,"
      + " cast(reservation.total AS decimal(10,2)) AS total"
      + " FROM ((reservation LEFT JOIN customer ON reservation.email = customer.email)"
      + " LEFT JOIN car ON reservation.carID = car.carID) ORDER BY reservation.reservationID", nativeQuery = true)
  List<CarFullQuery> findAllJoin();
}
