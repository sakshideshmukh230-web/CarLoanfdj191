package in.nexa.carenquiry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nexa.carenquiry.app.model.Enquiry;
import in.nexa.carenquiry.app.servicei.EnquiryServiceI;

@RestController
public class EnquiryController {
	
	@Autowired
	EnquiryServiceI esi;
	
	@PostMapping("/save")
	public ResponseEntity<Enquiry> saveEnquiry(@RequestBody Enquiry enquiry) {
		Enquiry save=	esi.saveAll(enquiry);
			return new ResponseEntity<Enquiry>(save, HttpStatus.CREATED);
		}
	
	@DeleteMapping("/deletecustomerId/{customerId}")
	public void deletecustomerId(@PathVariable("customerId") int customerId) {
	    System.out.println(customerId);
	    esi.deletecustomerId(customerId);
	}

}
