package in.nexa.carenquiry.app.servicei;

import in.nexa.carenquiry.app.model.Enquiry;

public interface EnquiryServiceI {

	Enquiry saveAll(Enquiry enquiry);

	public Enquiry getSingleCustomer(int customerId);

}
