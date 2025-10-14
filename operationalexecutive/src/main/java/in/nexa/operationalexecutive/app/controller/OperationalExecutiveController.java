package in.nexa.operationalexecutive.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nexa.operationalexecutive.app.model.Enquiry;
import in.nexa.operationalexecutive.app.servicei.OperationalExecutiveServiceI;

@RestController
public class OperationalExecutiveController {
	
	@Autowired
	OperationalExecutiveServiceI oei;
	

	@GetMapping("/getbystatus")
	public ResponseEntity<List> getPendingEnquiry(){
		
		List list= oei.getPendingEnquiry();
		
		return new ResponseEntity<List>(list, HttpStatus.ACCEPTED );
		
	}
	

}
