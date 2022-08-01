package carRental.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CarInfoRepository extends CrudRepository<CarInfo, Long> {

  CarInfo findByCarID(long carID);

  @Query(value= "Select cast(price as decimal(10,2) from car c WHERE c.carID =?1", nativeQuery = true)
  float findCarCost(long carID);

}
