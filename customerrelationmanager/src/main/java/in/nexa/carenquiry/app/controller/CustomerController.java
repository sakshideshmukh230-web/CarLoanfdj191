package in.nexa.carenquiry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nexa.carenquiry.app.model.CustomerLoanApplication;
import in.nexa.carenquiry.app.servicei.CustomerServiceI;


@RestController
@RequestMapping("/api/customerApplicationForm")
public class CustomerController {
	
	@Autowired
	CustomerServiceI csi;
	
	@PostMapping("/saveCustomer")
	public ResponseEntity<CustomerLoanApplication> saveApplicationForm(@RequestBody CustomerLoanApplication customerapp){
		csi.saveCustomers(customerapp);
		System.out.println(customerapp.getCustomerEmail());
		return new ResponseEntity<CustomerLoanApplication> (customerapp, HttpStatus.CREATED);
	}

}
