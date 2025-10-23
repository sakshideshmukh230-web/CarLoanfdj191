package in.nexa.carenquiry.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping("/saveDataWithDocs")
	public ResponseEntity<String> saveDataWithDocs(@RequestPart("data") String data , @RequestPart("addressProof") MultipartFile addressProof, @RequestPart("panCard") MultipartFile panCard, @RequestPart("incomeTax") MultipartFile incomeTax,
			 @RequestPart("addharCard") MultipartFile addharCard, @RequestPart("photo") MultipartFile photo,  @RequestPart("signature")MultipartFile signature,  @RequestPart("banckCheque")MultipartFile bankCheque, @RequestPart("salarySlips")MultipartFile salarySlips) {
		
		csi.saveCustomerWithDocs(data, addressProof, panCard, incomeTax, addharCard, photo, signature, bankCheque, salarySlips);
	
		return new ResponseEntity<String> ("Data inserted......", HttpStatus.OK);
	}
}
