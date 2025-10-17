package in.nexa.carenquiry.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DependentInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int dependentInfoId;
	 private int noOfFamilyMember;
	 private int noOfChild;
	 private String maritalStatus;
	 private String dependentMember;
	 private double familyIncome;
 

}
