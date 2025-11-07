package in.nexa.carenquiry.app.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SanctionLetter {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer sanctionId;
	    private Date sanctionDate;
	    private String applicantName;
	    private String contactDetails;
	    private Double loanRequired;
	    private Double loanAmtSanctioned;
	    private String interestType;
	    private Double rateOfInterest;
	    private Integer loanTenureInMonth;
	    private Double monthlyEmiAmount;
	    private String modeOfPayment;
	    private String remarks = "Customer verified and found eligible after document verification.";
	    private String termsCondition="Loan subject to standard bank policies";
	    private String status = "Created";
	    
	   
}
