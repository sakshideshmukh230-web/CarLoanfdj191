package in.nexa.creditmanager.app.servicei;

import in.nexa.creditmanager.app.model.CustomerLoanApplication;
import in.nexa.creditmanager.app.model.SanctionLetter;

public interface SanctionServiceI {

	CustomerLoanApplication updateSactionLetter(Integer customerId, SanctionLetter sanctionLetter);

}
