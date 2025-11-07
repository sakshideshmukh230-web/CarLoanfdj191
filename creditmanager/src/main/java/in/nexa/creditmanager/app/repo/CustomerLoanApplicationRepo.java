package in.nexa.creditmanager.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nexa.creditmanager.app.model.CustomerLoanApplication;

@Repository
public interface CustomerLoanApplicationRepo extends JpaRepository<CustomerLoanApplication, Integer>{
	
	List<CustomerLoanApplication> findByLoanStatus(String loanStatus);

}
