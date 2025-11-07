package in.nexa.creditmanager.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nexa.creditmanager.app.model.CustomerLoanApplication;
import in.nexa.creditmanager.app.model.SanctionLetter;
import in.nexa.creditmanager.app.servicei.CreditManagerServiceI;
import in.nexa.creditmanager.app.servicei.SanctionServiceI;

@RestController
@RequestMapping("/credit/manager")
public class CreditManagerController {
	
	@Autowired
	private CreditManagerServiceI cms;
	
	@Autowired
	private SanctionServiceI ss;
	
	@GetMapping("/getAllVerifiedCustomer")
	public ResponseEntity<List<CustomerLoanApplication>> getAllVerifiedCustomer(){		
		List<CustomerLoanApplication> verifedCustomers = cms.getVerifiedcustomers();
		if (verifedCustomers.isEmpty())
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		
		return new ResponseEntity<List<CustomerLoanApplication>>(verifedCustomers, HttpStatus.OK);
	}
	
	
	@PutMapping("/generatePdf/{customerId}")
	public CustomerLoanApplication updateSactionLetter(@PathVariable("customerId") Integer customerId, @RequestBody SanctionLetter sanctionLetter) {
			return ss.updateSactionLetter(customerId, sanctionLetter);
	}
	
	
	@GetMapping("/sendMail/{customerId}")
    public ResponseEntity<String> sendMail(@PathVariable("customerId") int customerId) {
		System.out.println("Mail sending started");
		Optional<CustomerLoanApplication> customer = cms.findById(customerId);

	    if (customer.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + customerId + " not found.");
	    }
	    
		CustomerLoanApplication customerDetails = customer.get();
		
		try {
			cms.sendSanctionMail(customerDetails);
			return ResponseEntity.ok("Sanction letter email sent successfully to: " + customerDetails.getCustomerEmail());
		}
		catch (Exception e) {
			e.printStackTrace();

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
		}

    }

}
