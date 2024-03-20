package org.jsp.UserApp.Service;

import java.util.Optional;

import org.jsp.UserApp.Exception.IdNotFoundException;
import org.jsp.UserApp.Exception.InvalidCredentialException;
import org.jsp.UserApp.dao.AadharDao;
import org.jsp.UserApp.dao.UserDao;
import org.jsp.UserApp.dto.AadharCard;
import org.jsp.UserApp.dto.ResponseStructure;
import org.jsp.UserApp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AadharService {
	@Autowired
	private AadharDao aadharDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<AadharCard>> saveAadhar(AadharCard card, int id) {
		Optional<User> resUser = userDao.findById(id);
		ResponseStructure<AadharCard> structure = new ResponseStructure<>();
		if (resUser.isPresent()) {
			User user = resUser.get();
			card.setUser(user);
			user.setAadharcard(card);
			structure.setMessage("Aadhar Card Saved Successfully");
			structure.setData(aadharDao.saveAadhar(card, id));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<AadharCard>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<AadharCard>> updateAadharCard(AadharCard card) {
		Optional<AadharCard> resCard = aadharDao.findById(card.getId());
		ResponseStructure<AadharCard> structure = new ResponseStructure<>();
		if (resCard.isPresent()) {
			AadharCard aadhar = resCard.get();
			aadhar.setName(card.getName());
			aadhar.setNumber(card.getNumber());
			aadhar.setAddress(card.getAddress());
			aadhar.setImage_url(card.getImage_url());
			aadhar.setGender(card.getGender());
			aadhar.setPincode(card.getPincode());
			aadhar.setCity(card.getCity());
			aadhar.setState(card.getState());
			structure.setMessage("AadharCard Updated Succesfully!!");
			structure.setData(aadharDao.updateAadharCard(aadhar));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<AadharCard>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<AadharCard>> findById(int id) {
		Optional<AadharCard> resCard = aadharDao.findById(id);
		ResponseStructure<AadharCard> structure = new ResponseStructure<>();
		if (resCard.isPresent()) {
			structure.setMessage("AadharCard Found!!");
			structure.setData(resCard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<AadharCard>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<AadharCard>> findByNumber(long number) {
		Optional<AadharCard> resCard = aadharDao.findByNumber(number);
		ResponseStructure<AadharCard> structure = new ResponseStructure<>();
		if (resCard.isPresent()) {
			structure.setMessage("Valid Panacrd Number");
			structure.setData(resCard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<AadharCard>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid Aadhar Number");
	}

	public ResponseEntity<ResponseStructure<AadharCard>> findAadharByUserEmail(String email) {
		Optional<AadharCard> resCard = aadharDao.findAadharByUserEmail(email);
		ResponseStructure<AadharCard> structure = new ResponseStructure<>();
		if (resCard.isPresent()) {
			structure.setMessage("AadharCard Found!!");
			structure.setData(resCard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<AadharCard>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Incorrect User Email");
	}

	public ResponseEntity<ResponseStructure<AadharCard>> verifyAadharByUserEmail(String email, String password) {
		Optional<AadharCard> resCard = aadharDao.verifyAadharByUserEmail(email, password);
		ResponseStructure<AadharCard> structure = new ResponseStructure<>();
		if (resCard.isPresent()) {
			structure.setMessage("AadharCard Found!!");
			structure.setData(resCard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<AadharCard>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid User Email or Password");
	}

	public ResponseEntity<ResponseStructure<AadharCard>> findAadharByUserPhone(long phone) {
		Optional<AadharCard> resCard = aadharDao.findAadharByUserPhone(phone);
		ResponseStructure<AadharCard> structure = new ResponseStructure<>();
		if (resCard.isPresent()) {
			structure.setMessage("User Found");
			structure.setData(resCard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<AadharCard>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Incorrect Aadhar Number");
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteAadhar(int aid) {
		Optional<AadharCard> card = aadharDao.findById(aid);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if(card.isPresent()) {
			aadharDao.deleteAadhar(aid);
			structure.setMessage("Aadhar card Found Successfully..!!!");
			structure.setData("Aadhar card Deleted Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
}
