package in.nexa.operationalexecutive.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
		String getUrl= "http://localhost:9091/getpendingenquiry";
	    List l=	rt.getForObject(getUrl, List.class);
		return l;
	}

}
