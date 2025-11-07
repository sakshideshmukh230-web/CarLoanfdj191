package in.nexa.carenquiry.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public CustomerLoanApplication saveCustomerWithDocs(
			String data, 
			MultipartFile addressProof, 
			MultipartFile panCard,
			MultipartFile incomeTax,
			MultipartFile addharCard, 
			MultipartFile photo, 
			MultipartFile signature,
			MultipartFile bankCheque, 
			MultipartFile salarySlips) {
		
		ObjectMapper ob = new ObjectMapper();
		CustomerLoanApplication customerLoanApplication = null;
		
		try {
			customerLoanApplication = ob.readValue(data, CustomerLoanApplication.class);
			
			if (customerLoanApplication.getAllPersonalDocuments() == null) {
	            customerLoanApplication.setAllPersonalDocuments(new AllPersonalDocuments());
	        }
			
			if (addressProof != null && !addressProof.isEmpty()) 
				customerLoanApplication.getAllPersonalDocuments().setAddressProof(addressProof.getBytes());
			
			
			if (panCard != null && !panCard.isEmpty())
				customerLoanApplication.getAllPersonalDocuments().setPanCard(panCard.getBytes());
			
			
			if (incomeTax != null && !incomeTax.isEmpty())
				customerLoanApplication.getAllPersonalDocuments().setIncomeTax(incomeTax.getBytes());
			
			
			if (addharCard != null && !addharCard.isEmpty())
				customerLoanApplication.getAllPersonalDocuments().setAddharCard(addharCard.getBytes());
			
			
			if (photo != null && !photo.isEmpty())
				customerLoanApplication.getAllPersonalDocuments().setPhoto(photo.getBytes());
			
			
			if (signature != null && !signature.isEmpty())
				customerLoanApplication.getAllPersonalDocuments().setSignature(signature.getBytes());
			
			
			if (bankCheque != null && !bankCheque.isEmpty())
				customerLoanApplication.getAllPersonalDocuments().setBankCheque(bankCheque.getBytes());
			
			
			if (salarySlips != null && !salarySlips.isEmpty())
				customerLoanApplication.getAllPersonalDocuments().setSalarySlips(salarySlips.getBytes());
			
			
			CustomerVerification ver = new CustomerVerification();
			ver.setVerificationDate(new java.util.Date());
			ver.setStatus("submitted");
			ver.setRemarks("all documents are OK");

	        customerLoanApplication.setCustomerVerification(ver);
			
			
			return cr.save(customerLoanApplication);
		} 
		catch (Exception e) {
			e.printStackTrace();  
	        throw new RuntimeException("Error saving customer loan application.. ", e);
		} 
		
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