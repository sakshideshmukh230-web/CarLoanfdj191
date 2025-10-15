package in.nexa.operationalexecutive.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.nexa.operationalexecutive.app.model.Cibil;
import in.nexa.operationalexecutive.app.model.Enquiry;
import in.nexa.operationalexecutive.app.repository.OperationalExecutiveRepository;
import in.nexa.operationalexecutive.app.servicei.OperationalExecutiveServiceI;

@Service
public class OperationalExecutiveServiceImpl implements OperationalExecutiveServiceI
{
	@Autowired
	OperationalExecutiveRepository oer;

	@Autowired
	RestTemplate rt; 
	
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
		
		e.getCibil().setCibilScore(cibilscore);
		if(cibilscore >= 800 && cibilscore<= 900) {
			e.getCibil().setStatus("Excellent");
			e.getCibil().setCibilRemark("Approved");
		}
		else if(cibilscore >= 760 && cibilscore <= 800) {
			e.getCibil().setStatus("Very Good ");
			e.getCibil().setCibilRemark("Approved");;
		}
		else if(cibilscore >= 700 && cibilscore <= 760) {
			e.getCibil().setStatus(" Good ");
			e.getCibil().setCibilRemark("Approved");
		}
		else if(cibilscore >= 600 && cibilscore <=700) {
			e.getCibil().setStatus("Average");
			e.getCibil().setCibilRemark("Rejected");
		}
		else if(cibilscore >= 300 && cibilscore <=600) {
			e.getCibil().setStatus("Poor");
			e.getCibil().setCibilRemark("Rejected");
		}
		
		oer.save(e);
		
		return e.getCibil(); 
		
	}
	
	

}
