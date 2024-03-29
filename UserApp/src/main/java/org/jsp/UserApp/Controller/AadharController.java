package org.jsp.UserApp.Controller;

import org.jsp.UserApp.Service.AadharService;
import org.jsp.UserApp.dto.AadharCard;
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
@RequestMapping("/aadhar")
public class AadharController {
	@Autowired
	private AadharService aadharService;

	@PostMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<AadharCard>> saveAadhar(@RequestBody AadharCard card,@PathVariable int id) {
		return aadharService.saveAadhar(card, id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<AadharCard>> updateUser(@RequestBody AadharCard card) {
		return aadharService.updateAadharCard(card);
	}
	
	@DeleteMapping("/{aid}")
	public ResponseEntity<ResponseStructure<String>> deleteAadhar(@PathVariable int aid) {
		return aadharService.deleteAadhar(aid);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<AadharCard>> findById(@PathVariable int id) {
		return aadharService.findById(id);
	}

	@GetMapping(value = "/find-by-number")
	public ResponseEntity<ResponseStructure<AadharCard>> findByNumber(@RequestParam long number) {
		return aadharService.findByNumber(number);
	}

	@PostMapping(value = "/find-aadhar-by-email")
	public ResponseEntity<ResponseStructure<AadharCard>> findAadharByUserEmail(@RequestParam String email) {
		return aadharService.findAadharByUserEmail(email);
	}

	@PostMapping(value = "/find-aadhar-by-phone")
	public ResponseEntity<ResponseStructure<AadharCard>> findAadharByUserPhone(@RequestParam long phone) {
		return aadharService.findAadharByUserPhone(phone);
	}
	
	@PostMapping(value = "/verify-aadhar-by-email")
	public ResponseEntity<ResponseStructure<AadharCard>> verifyAadharByUserEmail(@RequestParam String email,@RequestParam String password) {
		return aadharService.verifyAadharByUserEmail(email, password);
	}


}
