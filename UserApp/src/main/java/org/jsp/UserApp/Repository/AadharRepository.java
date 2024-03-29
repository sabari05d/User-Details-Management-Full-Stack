package org.jsp.UserApp.Repository;

import java.util.Optional;

import org.jsp.UserApp.dto.AadharCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AadharRepository extends JpaRepository<AadharCard, Integer> {

	@Query("select a from AadharCard a where a.number=?1")
	public Optional<AadharCard> findByNumber(long number);

	@Query("select a from AadharCard a where a.user.name=?1")
	public Optional<AadharCard> findAadharByUserEmail(String email);

	@Query("select a from AadharCard a where a.user.email=?1 and a.user.password=?2")
	public Optional<AadharCard> verifyAadharByUserEmail(String email, String password);

	@Query("select a from AadharCard a where a.user.phone=?1")
	public Optional<AadharCard> findAadharByUserPhone(long phone);
}
