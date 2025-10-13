package in.nexa.carenquiry.app.servicei;

import in.nexa.carenquiry.app.model.Enquiry;

public interface EnquiryServiceI {

	Enquiry saveAll(Enquiry enquiry);

    public	void deletecustomerId(int customerId);
	public Enquiry getSingleCustomer(int customerId);

}
