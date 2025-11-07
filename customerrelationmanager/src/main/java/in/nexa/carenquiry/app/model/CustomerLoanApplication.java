package in.nexa.carenquiry.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class CustomerLoanApplication {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int	customerId;
		private String	customerName;
		private String	customerDateOfBirth;
		private int	customerAge;
		private int	requiredTenure;
		private String	customerGender;
		private String	customerEmail;
		private Double	customerMobileNumber;
		private Double	customerAdditionalMobileNumber;
		private Double  customerAmountPaidForCar;
		private Double	customerTotalLoanRequired;
		private String	loanStatus="submited";
		
		@OneToOne(cascade = CascadeType.ALL)
		private AllPersonalDocuments allPersonalDocuments;
		
		@OneToOne(cascade = CascadeType.ALL)
		private DependentInformation familydependentInfo;

		
		@OneToOne(cascade = CascadeType.ALL)
		private CustomerAddress	customerAddress;
		
		@OneToOne(cascade = CascadeType.ALL)
		private AccountDetails accountDetails; 
		
		@OneToOne(cascade = CascadeType.ALL)
		private GuarantorDetails guarantorDetails;
		
		@OneToOne(cascade = CascadeType.ALL)
		private CustomerVerification customerVerification;
		
		@OneToOne(cascade = CascadeType.ALL)
		private SanctionLetter sanctionLetter;
		
		
		
}
