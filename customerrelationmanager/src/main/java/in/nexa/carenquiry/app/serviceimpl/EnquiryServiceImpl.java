package in.nexa.carenquiry.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nexa.carenquiry.app.model.Enquiry;
import in.nexa.carenquiry.app.repository.EnquiryRepo;
import in.nexa.carenquiry.app.servicei.EnquiryServiceI;

@Service
public class EnquiryServiceImpl implements EnquiryServiceI {
	
	@Autowired
	EnquiryRepo er;

	@Override
	public Enquiry saveAll(Enquiry enquiry) {
		return er.save(enquiry);
	}
    
	@Override

	public List getPendingStatus() {
	
		return er.findByEnquiryStatus("Pending");
	}

	public void deletecustomerId(int customerId) {
		er.deleteById(customerId);
	}

	@Override
	public Enquiry getSingleCustomer(int customerId) {
		
		return er.findById(customerId).get();

	}
	

}
