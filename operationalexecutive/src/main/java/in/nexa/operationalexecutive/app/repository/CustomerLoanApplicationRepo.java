package in.nexa.operationalexecutive.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nexa.operationalexecutive.app.model.CustomerLoanApplication;

@Repository
public interface CustomerLoanApplicationRepo extends JpaRepository<CustomerLoanApplication, Integer> {
	
	public List<CustomerLoanApplication> findByLoanStatus(String loanStatus);

}
