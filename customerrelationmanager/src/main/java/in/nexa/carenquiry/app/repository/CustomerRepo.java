package in.nexa.carenquiry.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nexa.carenquiry.app.model.CustomerLoanApplication;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerLoanApplication, Integer>{

}
