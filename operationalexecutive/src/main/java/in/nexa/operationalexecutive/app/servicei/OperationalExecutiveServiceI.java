package in.nexa.operationalexecutive.app.servicei;

import java.util.List;

import in.nexa.operationalexecutive.app.model.Cibil;
import in.nexa.operationalexecutive.app.model.Enquiry;

public interface OperationalExecutiveServiceI {

	public List getPendingEnquiry();

	public Cibil calculateCibil(int customerId);

	public List<Enquiry> getforwardtoOE();


}
