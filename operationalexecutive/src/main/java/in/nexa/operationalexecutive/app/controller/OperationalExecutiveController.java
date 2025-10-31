package in.nexa.operationalexecutive.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.nexa.operationalexecutive.app.model.Cibil;
import in.nexa.operationalexecutive.app.model.Enquiry;
import in.nexa.operationalexecutive.app.servicei.OperationalExecutiveServiceI;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController 
@CrossOrigin("*")
public class OperationalExecutiveController {
	
	@Autowired
	OperationalExecutiveServiceI oei;
	

	@GetMapping("/getbystatus")
	public ResponseEntity<List> getPendingEnquiry(){
		
		List list=  oei.getPendingEnquiry();
		
		return new ResponseEntity<List>(list, HttpStatus.OK);
		
	}
	
	@GetMapping("/getCibilScore/{customerId}")
	public ResponseEntity<Cibil> calculateCibil(@PathVariable("customerId")int customerId) {
		Cibil cibil = oei.calculateCibil(customerId);
		return new ResponseEntity<Cibil>(cibil,HttpStatus.OK);
				
	}

	  @GetMapping("/getforwardtoOE")
	  public List<Enquiry> getforwardtoOE()
	  {
		  List<Enquiry> enquiry=oei.getforwardtoOE();
		  
		  return enquiry;
		  
	  }
	 

}
