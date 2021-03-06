package mk.trkalo.dnp.dnpshop.repository;

import mk.trkalo.dnp.dnpshop.model.user.LoggedUser;
import mk.trkalo.dnp.dnpshop.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<LoggedUser> findByEmail(String email);
    Optional<User> findById(Long id);
    /*@Query(
            value = "SELECT * FROM User _user " +
                    //"JOIN user.phoneNumbers _number " +
                    "WHERE " +
                    "UPPER(_user.name) like %:query% OR " +
                    "_user.phone_numbers like %:query%"
            , nativeQuery = true
    )*/
    @Query("SELECT DISTINCT _user FROM User _user " +
            "LEFT JOIN _user.addresses _adr " +
            "WHERE _user.name LIKE %:query% OR " +
            "_user.phoneNumbers LIKE %:query% OR " +
            "CONCAT(_adr.streetName,' ', _adr.streetNumber) LIKE %:query% OR " +
            "_adr.objectTitle LIKE %:query%"
    )
    List<User> findWhereNameOrPhoneNumberLike(@Param("query") String query, Pageable pageable);

}
