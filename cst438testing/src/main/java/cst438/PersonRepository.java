package cst438;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

  @Query("select p from Person p order by lastName, favoriteFood desc")
  List<Person> findAllOrderByLastNameFavoriteFoodDesc();
}
