package in.nexa.carenquiry.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nexa.carenquiry.app.model.CustomerLoanApplication;
import in.nexa.carenquiry.app.repository.CustomerRepo;
import in.nexa.carenquiry.app.servicei.CustomerServiceI;

@Service
public class CustomerServiceImpl implements  CustomerServiceI{
	
	@Autowired
	CustomerRepo cr;

	@Override
	public CustomerLoanApplication saveCustomers(CustomerLoanApplication customerapp) {
		return cr.save(customerapp);
	}
}