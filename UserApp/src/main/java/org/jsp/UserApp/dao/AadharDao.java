package org.jsp.UserApp.dao;

import java.util.Optional;

import org.jsp.UserApp.Repository.AadharRepository;
import org.jsp.UserApp.dto.AadharCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AadharDao {
	@Autowired
	private AadharRepository aadharRepository;

	public AadharCard saveAadhar(AadharCard card, int uid) {
		return aadharRepository.save(card);
	}
	
	public AadharCard updateAadharCard(AadharCard card) {
		return aadharRepository.save(card);
	}

	public Optional<AadharCard> findById(int id) {
		return aadharRepository.findById(id);
	}

	public Optional<AadharCard> findByNumber(long number) {
		return aadharRepository.findByNumber(number);
	}

	public Optional<AadharCard> findAadharByUserEmail(String email) {
		return aadharRepository.findAadharByUserEmail(email);
	}

	public Optional<AadharCard> verifyAadharByUserEmail(String email, String password) {
		return aadharRepository.verifyAadharByUserEmail(email, password);
	}

	public Optional<AadharCard> findAadharByUserPhone(long number) {
		return aadharRepository.findAadharByUserPhone(number);
	}
	
	public boolean deleteAadhar(int aid) {
		Optional<AadharCard> card = aadharRepository.findById(aid);
		if(card.isPresent()) {
			aadharRepository.delete(card.get());
			return true;
		}
		return false;
	}
}
