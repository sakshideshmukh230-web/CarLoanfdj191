package in.nexa.carenquiry.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.nexa.carenquiry.app.model.CustomerLoanApplication;
import in.nexa.carenquiry.app.model.CustomerVerification;
import in.nexa.carenquiry.app.servicei.CustomerServiceI;


@RestController
@RequestMapping("/api/customerApplicationForm")
public class CustomerController {
	
	@Autowired
	CustomerServiceI csi;
	

	@PostMapping(value = "/saveDataWithDocs", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> saveDataWithDocs(@RequestPart("data") String data , @RequestPart("addressProof") MultipartFile addressProof, @RequestPart("panCard") MultipartFile panCard, @RequestPart("incomeTax") MultipartFile incomeTax,
			 @RequestPart("addharCard") MultipartFile addharCard, @RequestPart("photo") MultipartFile photo,  @RequestPart("signature")MultipartFile signature,  @RequestPart("bankCheque")MultipartFile bankCheque, @RequestPart("salarySlips")MultipartFile salarySlips) {
		
		CustomerLoanApplication saved = csi.saveCustomerWithDocs(data, addressProof, panCard, incomeTax, addharCard, photo, signature, bankCheque, salarySlips);
		System.out.println(data);
		
		if (saved == null) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failed to save data");
	    }
		
		return ResponseEntity.ok("Data inserted successfully..");
	}
	

	}

