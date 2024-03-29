package org.jsp.UserApp.Repository;

import java.util.Optional;

import org.jsp.UserApp.dto.Pancard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PancardRepository extends JpaRepository<Pancard, Integer> {

	@Query("select p from Pancard p where p.number=?1")
	public Optional<Pancard> findByPancardNumber(String number);

	@Query("select p from Pancard p where p.user.name=?1 ")
	public Optional<Pancard> findPancardByUserName(String name);

	@Query("select p from Pancard p where p.user.email=?1 ")
	public Optional<Pancard> findPancardByUserEmail(String email);

	@Query("select p from Pancard p where p.user.phone=?1 and p.user.password=?2")
	public Optional<Pancard> findPancardByUserPhone(long phone, String password);

}
