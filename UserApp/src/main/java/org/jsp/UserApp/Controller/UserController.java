package org.jsp.UserApp.Controller;

import java.util.List;

import org.jsp.UserApp.Service.UserService;
import org.jsp.UserApp.dto.ResponseStructure;
import org.jsp.UserApp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseStructure<User> saveUser(@RequestBody User u) {
		return userService.saveUser(u);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User u) {
		return userService.updateUser(u);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable int id) {
		return userService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> delete(@PathVariable int id) {
		return userService.deleteUser(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<User>>> FindAll() {
		return userService.findAll();
	}

	@PostMapping(value = "/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam long phone, @RequestParam String password) {
		return userService.Verify(phone, password);
	}

	@PostMapping(value = "/verify-by-email")
	public ResponseEntity<ResponseStructure<User>> verify(@RequestParam String email, @RequestParam String password) {
		return userService.Verify(email, password);
	}


	@GetMapping(value = "/find-by-email")
	public ResponseEntity<ResponseStructure<User>> findByEmail(@RequestParam String email) {
		return userService.findByEmail(email);
	}

	@GetMapping(value = "/find-by-phone")
	public ResponseEntity<ResponseStructure<User>> findByPhone(@RequestParam long phone) {
		return userService.findByPhone(phone);
	}
}
