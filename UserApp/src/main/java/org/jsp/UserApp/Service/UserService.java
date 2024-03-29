package org.jsp.UserApp.Service;

import java.util.List;
import java.util.Optional;

import org.jsp.UserApp.Exception.IdNotFoundException;
import org.jsp.UserApp.Exception.InvalidCredentialException;
import org.jsp.UserApp.Exception.NoUserFoundException;
import org.jsp.UserApp.dao.AadharDao;
import org.jsp.UserApp.dao.PancardDao;
import org.jsp.UserApp.dao.UserDao;
import org.jsp.UserApp.dto.ResponseStructure;
import org.jsp.UserApp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PancardDao pancardDao;
	
	@Autowired
	private AadharDao aadharDao;

	public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setMessage("User saved SuccessFully...!!!");
		structure.setData(userDao.saveUser(user));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User u) {
		Optional<User> resUser = userDao.findById(u.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (resUser.isPresent()) {
			User dbUser = userDao.saveUser(u);
			dbUser.setName(u.getName());
			dbUser.setPhone(u.getPhone());
			dbUser.setAge(u.getAge());
			dbUser.setImage_url(u.getImage_url());
			dbUser.setEmail(u.getEmail());
			dbUser.setPassword(u.getPassword());
			structure.setMessage("User Updated Successfully..!!!");
			structure.setData(userDao.saveUser(dbUser));
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> resUser = userDao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (resUser.isPresent()) {
			structure.setMessage("User Found Successfully..!!!");
			structure.setData(resUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		List<User> resUser = userDao.findAll();
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		if (resUser.size() > 0) {
			structure.setMessage("Users Found Successfully..!!!");
			structure.setData(resUser);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.ACCEPTED);
		}
		throw new NoUserFoundException("User Not Found");

	}

	public ResponseEntity<ResponseStructure<User>> findByPhone(long phone) {
		Optional<User> resUser = userDao.findByPhone(phone);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (resUser.isPresent()) {
			structure.setMessage("User Found Successfully..!!!");
			structure.setData(resUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new NoUserFoundException("User Not Found For The Given Phone Number..!!!");
	}

	public ResponseEntity<ResponseStructure<User>> findByEmail(String email) {
		Optional<User> resUser = userDao.findByEmail(email);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (resUser.isPresent()) {
			structure.setMessage("User Found Successfully..!!!");
			structure.setData(resUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new NoUserFoundException("User Not Found For The Given Email ID..!!!");
	}

	public ResponseEntity<ResponseStructure<User>> Verify(long phone, String password) {
		Optional<User> resUser = userDao.verify(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (resUser.isPresent()) {
			structure.setMessage("User Found Successfully..!!!");
			structure.setData(resUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new InvalidCredentialException("Invalid Phone Number Or Password");
	}

	public ResponseEntity<ResponseStructure<User>> Verify(String email, String password) {
		Optional<User> resUser = userDao.verify(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (resUser.isPresent()) {
			structure.setMessage("User Found Successfully..!!!");
			structure.setData(resUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new InvalidCredentialException("Invalid Email Or Password");
	}

	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		Optional<User> resUser = userDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (resUser.isPresent()) {
			userDao.deleteUser(id);
			pancardDao.deletePancard(resUser.get().getPancard().getId());
			aadharDao.deleteAadhar(resUser.get().getAadharcard().getId());
			structure.setMessage("User Found Successfully..!!!");
			structure.setData("User Details Deleted Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
}
