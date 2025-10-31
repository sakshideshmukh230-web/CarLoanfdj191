package in.nexa.carenquiry.app.servicei;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.nexa.carenquiry.app.model.CustomerLoanApplication;
import in.nexa.carenquiry.app.model.CustomerVerification;

public interface CustomerServiceI {

public CustomerLoanApplication saveCustomerWithDocs(String data , MultipartFile addressProof,MultipartFile panCard,MultipartFile incomeTax,
													MultipartFile addharCard, MultipartFile photo,MultipartFile signature, MultipartFile bankCheque,MultipartFile salarySlips );


public List<CustomerLoanApplication> getSubmittedApplications();


public CustomerVerification updateVerificationStatus(int verificationID, String status);
}

