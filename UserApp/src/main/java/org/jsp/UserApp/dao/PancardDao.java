package org.jsp.UserApp.dao;

import java.util.Optional;

import org.jsp.UserApp.Repository.PancardRepository;
import org.jsp.UserApp.dto.Pancard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PancardDao {
	@Autowired
	private PancardRepository pancardRepository;

	public Pancard savePancard(Pancard p, int uid) {
		return pancardRepository.save(p);
	}

	public Optional<Pancard> findById(int id) {
		return pancardRepository.findById(id);
	}

	public Optional<Pancard> findByPancardNumber(String number) {
		return pancardRepository.findByPancardNumber(number);
	}

	public Optional<Pancard> findByUserEmail(String email) {
		return pancardRepository.findPancardByUserEmail(email);
	}

	public Optional<Pancard> verify(long phone, String password) {
		return pancardRepository.findPancardByUserPhone(phone, password);
	}
}
