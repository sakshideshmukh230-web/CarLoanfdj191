package in.nexa.carenquiry.app.servicei;

import org.springframework.web.multipart.MultipartFile;

import in.nexa.carenquiry.app.model.CustomerLoanApplication;

public interface CustomerServiceI {

public CustomerLoanApplication saveCustomerWithDocs(String data , MultipartFile addressProof,MultipartFile panCard,MultipartFile incomeTax,
													MultipartFile addharCard, MultipartFile photo,MultipartFile signature, MultipartFile bankCheque,MultipartFile salarySlips );
}

