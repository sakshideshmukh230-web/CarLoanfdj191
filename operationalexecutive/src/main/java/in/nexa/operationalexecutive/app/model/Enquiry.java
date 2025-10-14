package in.nexa.operationalexecutive.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Enquiry {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String firstname;
	private String lastName;
	private int age;
	private String email;
	private String mobileNo;
	private String pancardNo;
	private String enquiryStatus="Pending";
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cibil;

}
