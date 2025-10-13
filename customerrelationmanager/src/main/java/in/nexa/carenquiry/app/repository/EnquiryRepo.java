package in.nexa.carenquiry.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nexa.carenquiry.app.model.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

}
