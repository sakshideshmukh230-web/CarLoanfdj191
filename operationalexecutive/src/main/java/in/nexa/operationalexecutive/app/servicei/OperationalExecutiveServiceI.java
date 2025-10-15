package in.nexa.operationalexecutive.app.servicei;

import java.util.List;

import in.nexa.operationalexecutive.app.model.Cibil;

public interface OperationalExecutiveServiceI {

	public List getPendingEnquiry();

	public Cibil calculateCibil(int customerId);

}
