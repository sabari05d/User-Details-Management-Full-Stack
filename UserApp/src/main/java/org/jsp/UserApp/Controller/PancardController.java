package org.jsp.UserApp.Controller;

import org.jsp.UserApp.Service.PancardService;
import org.jsp.UserApp.dto.Pancard;
import org.jsp.UserApp.dto.ResponseStructure;
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
@RequestMapping("/pancard")
public class PancardController {

	@Autowired
	private PancardService pancardService;

	@PostMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Pancard>> savePancard(@RequestBody Pancard p, @PathVariable int id) {
		return pancardService.savePancard(p, id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Pancard>> updatePancard(@RequestBody Pancard p) {
		return pancardService.updatePancard(p);
	}
	
	@DeleteMapping("/{pid}")
	public ResponseEntity<ResponseStructure<String>> deletePancard(@PathVariable int pid) {
		return pancardService.deletePancard(pid);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Pancard>> finfById(@PathVariable int id) {
		return pancardService.findById(id);
	}

	@GetMapping(value = "/find-by-number")
	public ResponseEntity<ResponseStructure<Pancard>> findByPanNumber(@RequestParam String number) {
		return pancardService.findByPancardNumber(number);
	}

	@GetMapping(value = "/find-by-email")
	public ResponseEntity<ResponseStructure<Pancard>> verify(@RequestParam String email) {
		return pancardService.findByUserEmail(email);
	}

	@PostMapping(value = "/find-by-phone")
	public ResponseEntity<ResponseStructure<Pancard>> verify(@RequestParam long phone, @RequestParam String password) {
		return pancardService.verifyByPhoneAndPassword(phone, password);
	}
}
