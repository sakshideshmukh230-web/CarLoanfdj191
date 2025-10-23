package in.nexa.carenquiry.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.nexa.carenquiry.app.model.CustomerLoanApplication;
import in.nexa.carenquiry.app.repository.CustomerRepo;
import in.nexa.carenquiry.app.servicei.CustomerServiceI;

@Service
public class CustomerServiceImpl implements  CustomerServiceI{
	
	@Autowired
	CustomerRepo cr;

	@Override
	public CustomerLoanApplication saveCustomers(CustomerLoanApplication customerapp) {
		return cr.save(customerapp);
	}

	@Override
	public CustomerLoanApplication saveCustomerWithDocs(String data, MultipartFile addressProof, MultipartFile panCard,
			MultipartFile incomeTax, MultipartFile addharCard, MultipartFile photo, MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips) {
		
		ObjectMapper ob = new ObjectMapper();
		CustomerLoanApplication customerLoanApplication = null;
		
		try {
			customerLoanApplication = ob.readValue(data, CustomerLoanApplication.class);
			customerLoanApplication.getAllPersonalDocuments().setAddressProof(addressProof.getBytes());
			customerLoanApplication.getAllPersonalDocuments().setPanCard(panCard.getBytes());
			customerLoanApplication.getAllPersonalDocuments().setIncomeTax(incomeTax.getBytes());
			customerLoanApplication.getAllPersonalDocuments().setAddharCard(addharCard.getBytes());
			customerLoanApplication.getAllPersonalDocuments().setPhoto(photo.getBytes());
			customerLoanApplication.getAllPersonalDocuments().setSignature(signature.getBytes());
			customerLoanApplication.getAllPersonalDocuments().setBankCheque(bankCheque.getBytes());
			customerLoanApplication.getAllPersonalDocuments().setSalarySlips(salarySlips.getBytes());
			return cr.save(customerLoanApplication);
		} 
		catch (Exception e) {
			
			System.out.println(e.getMessage());
		} 
		
		
		return null;
	}
}