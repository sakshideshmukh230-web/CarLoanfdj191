package in.nexa.operationalexecutive.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PermanentAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int permanentAddressId;
	private String areaname;
	private String cityname;
	private String district;
	private String state;
	private long pincode;
	private String houseNumber;
	private String streetName;


}
