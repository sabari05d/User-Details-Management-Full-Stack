package org.jsp.UserApp.Service;

import java.util.Optional;

import org.jsp.UserApp.Exception.IdNotFoundException;
import org.jsp.UserApp.Exception.InvalidCredentialException;
import org.jsp.UserApp.Exception.NoUserFoundException;
import org.jsp.UserApp.dao.PancardDao;
import org.jsp.UserApp.dao.UserDao;
import org.jsp.UserApp.dto.Pancard;
import org.jsp.UserApp.dto.ResponseStructure;
import org.jsp.UserApp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PancardService {
	@Autowired
	private PancardDao pancardDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Pancard>> savePancard(Pancard p, int uid) {
		Optional<User> User = userDao.findById(uid);
		ResponseStructure<Pancard> structure = new ResponseStructure<>();
		if (User.isPresent()) {
			User u = User.get();
			u.setPancard(p);
			p.setUser(u);
			structure.setMessage("Pancard Saved Successfully");
			structure.setData(pancardDao.savePancard(p, uid));
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Pancard>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Pancard>> updatePancard(Pancard p) {
		Optional<Pancard> resPancard = pancardDao.findById(p.getId());
		ResponseStructure<Pancard> structure = new ResponseStructure<>();
		if (resPancard.isPresent()) {
			Pancard card = resPancard.get();
			card.setName(p.getName());
			card.setDob(p.getDob());
			card.setImage_url(p.getImage_url());
			card.setNumber(p.getNumber());
			structure.setMessage("Pancard Updated Successfullly");
			structure.setData(pancardDao.updatePancard(card));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Pancard>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Pancard>> findById(int id) {
		Optional<Pancard> resPancard = pancardDao.findById(id);
		ResponseStructure<Pancard> structure = new ResponseStructure<>();
		if (resPancard.isPresent()) {
			structure.setMessage("Pancard Found!!!");
			structure.setData(resPancard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Pancard>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Pancard>> findByPancardNumber(String number) {
		Optional<Pancard> resPancard = pancardDao.findByPancardNumber(number);
		ResponseStructure<Pancard> structure = new ResponseStructure<>();
		if (resPancard.isPresent()) {
			structure.setMessage("Pancard Found!!!");
			structure.setData(resPancard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Pancard>>(structure, HttpStatus.OK);
		}
		throw new NoUserFoundException("Invalid Pancard Number....!!!");
	}

	public ResponseEntity<ResponseStructure<Pancard>> findByUserEmail(String email) {
		Optional<Pancard> resPancard = pancardDao.findByUserEmail(email);
		ResponseStructure<Pancard> structure = new ResponseStructure<>();
		if (resPancard.isPresent()) {
			structure.setMessage("Pancard Found!!!");
			structure.setData(resPancard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Pancard>>(structure, HttpStatus.OK);
		}
		throw new NoUserFoundException("Invalid User Email....!!!");
	}

	public ResponseEntity<ResponseStructure<Pancard>> verifyByPhoneAndPassword(long phone, String password) {
		Optional<Pancard> resPancard = pancardDao.verify(phone, password);
		ResponseStructure<Pancard> structure = new ResponseStructure<>();
		if (resPancard.isPresent()) {
			structure.setMessage("Valid Pancard Number");
			structure.setData(resPancard.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Pancard>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid Phone Number or Password");
	}
	
	public ResponseEntity<ResponseStructure<String>> deletePancard(int pid) {
		Optional<Pancard> pancard = pancardDao.findById(pid);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if(pancard.isPresent()) {
			pancardDao.deletePancard(pid);
			structure.setMessage("Pancard Found Successfully..!!!");
			structure.setData("Pancard Deleted Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

}
