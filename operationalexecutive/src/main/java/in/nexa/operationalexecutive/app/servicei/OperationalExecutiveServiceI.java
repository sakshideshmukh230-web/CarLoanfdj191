package in.nexa.operationalexecutive.app.servicei;

import java.util.List;

import in.nexa.operationalexecutive.app.model.Cibil;


import in.nexa.operationalexecutive.app.model.CustomerLoanApplication;
import in.nexa.operationalexecutive.app.model.CustomerVerification;

import in.nexa.operationalexecutive.app.model.Enquiry;

public interface OperationalExecutiveServiceI {

	public List getPendingEnquiry();

	public Cibil calculateCibil(int customerId);


	public List<Enquiry> getforwardtoOE();


	public List<Enquiry> getforwardedToOe();

	public CustomerVerification updateVerificationStatus(int verificationID, String status);

	public List<CustomerLoanApplication> getSubmittedApplications();

	



}
