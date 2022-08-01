package carRental.domain;

import org.springframework.data.repository.CrudRepository;

public interface CarCustomerRepository extends CrudRepository<CarCustomer, Long> {

  CarCustomer findByEmail(String email);
}