package in.nexa.operationalexecutive.app.serviceimpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.nexa.operationalexecutive.app.model.Cibil;
import in.nexa.operationalexecutive.app.model.CustomerLoanApplication;
import in.nexa.operationalexecutive.app.model.CustomerVerification;
import in.nexa.operationalexecutive.app.model.Enquiry;
import in.nexa.operationalexecutive.app.repository.CustomerLoanApplicationRepo;
import in.nexa.operationalexecutive.app.repository.CustomerVerificationRepo;
import in.nexa.operationalexecutive.app.repository.OperationalExecutiveRepository;
import in.nexa.operationalexecutive.app.servicei.OperationalExecutiveServiceI;

@Service
public class OperationalExecutiveServiceImpl implements OperationalExecutiveServiceI
{
	@Autowired
	OperationalExecutiveRepository oer;
	
	@Autowired
	CustomerVerificationRepo cvr;
	
	@Autowired
	CustomerLoanApplicationRepo cla;

	@Autowired
	RestTemplate rt; 
	
	@Autowired 
	JavaMailSender sender;

	
	@Value("${spring.mail.username}")
	private static String sendFrom;
	
	@Override
	public List getPendingEnquiry() {
		String getUrl= "http://localhost:9091/api/enquiry/getpendingenquiry";
	    List l=	rt.getForObject(getUrl, List.class);
		return l;
	}
   
	
	
	@Override  
	public Cibil calculateCibil(int customerId) {
		
		String getCibilUrl = "http://localhost:9095/cibil";
		int cibilscore = rt.getForObject(getCibilUrl, Integer.class);
		System.out.println(cibilscore);
		 
		String getUrl ="http://localhost:9091/api/enquiry/getsinglecustomer/"+customerId;
		Enquiry e = rt.getForObject(getUrl, Enquiry.class);
		System.out.println(e.getEmail());
		
		if(e.getCibil().getCibilScore() == 0) { 
			
			e.getCibil().setCibilScore(cibilscore);
						
			if(cibilscore >= 800 && cibilscore<= 900) {
				e.getCibil().setStatus("Excellent");
				e.getCibil().setCibilRemark("Approved");
				e.setEnquiryStatus("Approved");
				
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(e.getEmail());
				message.setSubject("related car Enquiry");
				message.setFrom(sendFrom);
				message.setText("Your are eligible for the car loan "+ "Customer Id is "+ e.getCustomerId() +" \n "+ e.getFirstname());
				
			   sender.send(message);
			}
			else if(cibilscore >= 760 && cibilscore <= 800) {
				e.getCibil().setStatus("Very Good ");
				e.getCibil().setCibilRemark("Approved");
				e.setEnquiryStatus("Approved");
				
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(e.getEmail());
				message.setSubject("related car Enquiry");
				message.setFrom(sendFrom);
				message.setText("Your are eligible for the car loan "+ "Customer Id is "+ e.getCustomerId() +" \n "+ e.getFirstname());
				
			   sender.send(message);
			}
			else if(cibilscore >= 700 && cibilscore <= 760) {
				e.getCibil().setStatus(" Good ");
				e.getCibil().setCibilRemark("Approved");
				e.setEnquiryStatus("Approved");
				
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(e.getEmail());
				message.setSubject("related car Enquiry");
				message.setFrom(sendFrom);
				message.setText("Your are eligible for the car loan "+ "Customer Id is "+ e.getCustomerId() +" \n "+ e.getFirstname());
				
			   sender.send(message);
			}
			else if(cibilscore >= 600 && cibilscore <=700) {
				e.getCibil().setStatus("Average");
				e.getCibil().setCibilRemark("Rejected");
				e.setEnquiryStatus("Rejected");
				
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(e.getEmail());
				message.setSubject("related car Enquiry");
				message.setFrom(sendFrom);
				message.setText("Your are not eligible for the car loan "+ "Customer Id is "+ e.getCustomerId() +" \n "+ e.getFirstname());
				
			   sender.send(message);
			}
			else if(cibilscore >= 300 && cibilscore <=600) {
				e.getCibil().setStatus("Poor");
				e.getCibil().setCibilRemark("Rejected");
				e.setEnquiryStatus("Rejected");
				
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(e.getEmail());
				message.setSubject("related car Enquiry");
				message.setFrom(sendFrom);
				message.setText("Your are not eligible for the car loan "+ "Customer Id is "+ e.getCustomerId() +" \n "+ e.getFirstname());
				
			   sender.send(message);
			}
			
			oer.save(e);
		}
		System.out.println("Cibil Already Genrated..");
		
		return e.getCibil(); 
		
	}

//	@Override
//	public List<Enquiry> getforwardedToOe() {
//		 return oer.findByEnquiryStatus("forwardedtooe");
//	}
//	
	

	
	@Override
	public List<CustomerLoanApplication> getSubmittedApplications() {
		return cla.findByLoanStatus("submitted");
	}

	@Override
	public List<Enquiry> getforwardtoOE() {
		String getUrl= "http://localhost:9091/api/enquiry/getforwardtoOE";
	    List l=	rt.getForObject(getUrl, List.class);
		return l;
	}
	
	
	
		@Override
		public CustomerVerification updateVerificationStatus(int customerId, int verificationID,String status) {
			System.out.println(verificationID + " " + status);
			
			Optional<CustomerVerification> op =	cvr.findById(verificationID);	
			CustomerVerification customerVerification = op.get();
			customerVerification.setStatus(status);
			cvr.save(customerVerification);
			
			Optional<CustomerLoanApplication> loanAppOpt = cla.findById(customerId); 
		    if (loanAppOpt.isEmpty()) {
		        throw new RuntimeException("CustomerLoanApplication not found for ID: " + customerId);
		    }
	
		    CustomerLoanApplication customerLoanApplication = loanAppOpt.get();
		    customerLoanApplication.setLoanStatus(status);
		    cla.save(customerLoanApplication);
		    
		    System.out.println("verificationStatus & loanStatus Updated..");
			
			return customerVerification;
				
		}

}


