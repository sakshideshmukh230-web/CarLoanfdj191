package in.nexa.operationalexecutive.app.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class CustomerVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int verificationID;
	private Date verificationDate;
	private String status;
	private String remarks;

}
