package in.nexa.carenquiry.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nexa.carenquiry.app.model.Enquiry;
import in.nexa.carenquiry.app.servicei.EnquiryServiceI;

@RestController
@RequestMapping("/api/enquiry")
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


	
	@GetMapping("/getpendingenquiry")
	public ResponseEntity<List>  getPendingStatus(){
		List<String> list =esi.getPendingStatus();
		return new ResponseEntity<List>(list, HttpStatus.OK);
		
	}

	  @GetMapping("/getsinglecustomer/{customerId}")
	  public ResponseEntity<Enquiry> getSingleCustomer(@PathVariable("customerId")int customerId)
	  {
		  Enquiry enquiry = esi.getSingleCustomer(customerId);
		  
		  return new ResponseEntity<Enquiry>(enquiry,HttpStatus.OK);
	  }
	  

	  @GetMapping("/getAll")
	  public ResponseEntity<List<Enquiry>> getAllEnquiry()
		{
			List<Enquiry> enquiry= esi.getAllEnquiry();
			return new ResponseEntity<List<Enquiry>>(enquiry,HttpStatus.OK);
		}  

}
