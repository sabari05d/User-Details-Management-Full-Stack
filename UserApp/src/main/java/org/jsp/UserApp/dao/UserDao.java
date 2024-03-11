package org.jsp.UserApp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.UserApp.Repository.UserRepository;
import org.jsp.UserApp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public Optional<User> verify(long phone, String password) {
		return userRepository.verify(phone, password);
	}

	public Optional<User> verify(String email, String password) {
		return userRepository.verify(email, password);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public List<User> findUserByName(String name) {
		return userRepository.findUserByName(name);
	}

	public Optional<User> findByPhone(long phone) {
		return userRepository.findUserByPhone(phone);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	public boolean deleteUser(int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.delete(user.get());
			return true;
		}
		return false;
	}

}
