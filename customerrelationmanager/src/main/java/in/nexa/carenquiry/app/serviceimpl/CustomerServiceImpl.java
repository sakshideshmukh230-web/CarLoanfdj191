package in.nexa.carenquiry.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.nexa.carenquiry.app.CustomerrelationmanagerApplication;
import in.nexa.carenquiry.app.model.AllPersonalDocuments;


import in.nexa.carenquiry.app.model.CustomerLoanApplication;
import in.nexa.carenquiry.app.model.CustomerVerification;
import in.nexa.carenquiry.app.repository.CustomerRepo;
import in.nexa.carenquiry.app.servicei.CustomerServiceI;

@Service
public class CustomerServiceImpl implements  CustomerServiceI{

    
	@Autowired
	CustomerRepo cr;




	

	@Override
	public CustomerLoanApplication saveCustomerWithDocs(String data, MultipartFile addressProof, MultipartFile panCard,
			MultipartFile incomeTax, MultipartFile addharCard, MultipartFile photo, MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips) {
		
		ObjectMapper ob = new ObjectMapper();
		CustomerLoanApplication customerLoanApplication = null;
		AllPersonalDocuments docs = new AllPersonalDocuments();
		
		try {
			customerLoanApplication = ob.readValue(data, CustomerLoanApplication.class);
			docs.setAddressProof(addressProof.getBytes());
			docs.setPanCard(panCard.getBytes());
			docs.setIncomeTax(incomeTax.getBytes());
			docs.setAddharCard(addharCard.getBytes());
			docs.setPhoto(photo.getBytes());
			docs.setSignature(signature.getBytes());
			docs.setBankCheque(bankCheque.getBytes());
			docs.setSalarySlips(salarySlips.getBytes());
			customerLoanApplication.setAllPersonalDocuments(docs);
			return cr.save(customerLoanApplication);
		} 
		catch (Exception e) {
			
			System.out.println(e.getMessage());
		} 
		return null;
		
	}


	@Override
	public List<CustomerLoanApplication> getSubmittedApplications() {
		return cr.findByLoanStatus("submited");
	}


	@Override
	public CustomerVerification updateVerificationStatus(int verificationID, String status) {
		
		return null;
	}
	
}