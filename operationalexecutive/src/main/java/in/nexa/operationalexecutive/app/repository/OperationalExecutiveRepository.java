package in.nexa.operationalexecutive.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.nexa.operationalexecutive.app.model.Enquiry;
import jakarta.transaction.Transactional;

@Repository
public interface OperationalExecutiveRepository extends JpaRepository<Enquiry, Integer>
{

	List<Enquiry> findByEnquiryStatus(String string);

}
