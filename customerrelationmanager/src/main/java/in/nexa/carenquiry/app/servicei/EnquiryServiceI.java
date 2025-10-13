package in.nexa.carenquiry.app.servicei;

import java.util.List;

import in.nexa.carenquiry.app.model.Enquiry;

public interface EnquiryServiceI {

	Enquiry saveAll(Enquiry enquiry);

	public List getPendingStatus();

}
