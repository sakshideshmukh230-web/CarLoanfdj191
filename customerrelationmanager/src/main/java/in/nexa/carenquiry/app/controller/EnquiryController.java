package in.nexa.carenquiry.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	
	@GetMapping("/getpendingenquiry")
	public ResponseEntity<List>  getPendingStatus(){
		List<String> list =esi.getPendingStatus();
		return new ResponseEntity<List>(list, HttpStatus.OK);
		
	}
	
}
