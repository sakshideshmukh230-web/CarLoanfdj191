package in.nexa.creditmanager.app.servicei;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import in.nexa.creditmanager.app.model.CustomerLoanApplication;
import in.nexa.creditmanager.app.model.SanctionLetter;

public interface CreditManagerServiceI {

	public List<CustomerLoanApplication> getVerifiedcustomers();

	public Optional<CustomerLoanApplication> findById(Integer enquid);
	
	String sendSanctionMail(CustomerLoanApplication customerDetails);

}
