package in.nexa.carenquiry.app.servicei;

import java.util.List;

import in.nexa.carenquiry.app.model.Enquiry;

public interface EnquiryServiceI {

	Enquiry saveAll(Enquiry enquiry);


	public List getPendingStatus();

    public	void deletecustomerId(int customerId);
    
	public Enquiry getSingleCustomer(int customerId);


	List<Enquiry> getAllEnquiry();


	Enquiry forwardedToOe(int customerId);


	List<String> rejectedStatus();


	List<String> approvedStatus();


	


}
