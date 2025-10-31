package in.nexa.operationalexecutive.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nexa.operationalexecutive.app.model.CustomerVerification;

@Repository
public interface CustomerVerificationRepo extends JpaRepository<CustomerVerification, Integer>{

}
