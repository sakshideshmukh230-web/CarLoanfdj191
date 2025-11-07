package in.nexa.creditmanager.app.serviceimpl;


import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.lowagie.text.Image;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nexa.creditmanager.app.model.CustomerLoanApplication;
import in.nexa.creditmanager.app.model.SanctionLetter;
import in.nexa.creditmanager.app.repo.CustomerLoanApplicationRepo;
import in.nexa.creditmanager.app.repo.SanctionLetterRepo;
import in.nexa.creditmanager.app.servicei.CreditManagerServiceI;

import jakarta.mail.internet.MimeMessage;

@Service
public class CreditManagerImpl implements CreditManagerServiceI {
	
	    @Autowired
	    private RestTemplate rt;

	    @Autowired
	    private SanctionLetterRepo sr;

	    @Autowired
	    private CustomerLoanApplicationRepo clar;

	    @Autowired
	    private JavaMailSender mailSender;

	    @Value("${spring.mail.username}")
	    private String fromEmail;

	    
	    
	    @Override
		public List<CustomerLoanApplication> getVerifiedcustomers() {
			return clar.findByLoanStatus("verified");			 
		}

		@Override
		public Optional<CustomerLoanApplication> findById(Integer enquid) {
			Optional<CustomerLoanApplication> findById= clar.findById(enquid);
			return findById;
		}
		
		
		@Override
		public String sendSanctionMail(CustomerLoanApplication customerDetails) {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			byte[] sanctionLetter = customerDetails.getSanctionLetter().getSanctionLetterPdf();

			try {
				MimeMessageHelper mimemessageHelper = new MimeMessageHelper(mimeMessage, true);
				mimemessageHelper.setFrom(fromEmail);
				mimemessageHelper.setTo(customerDetails.getCustomerEmail());
				mimemessageHelper.setSubject("ABC Finance Ltd. Sanction Letter");
				String text = "Dear " + customerDetails.getCustomerName()
						+ ",\n" + "\n"
						+ "This letter is to inform you that we have reviewed your request for a credit loan. \n\n We are pleased to offer you a credit loan of "
						+ customerDetails.getSanctionLetter().getLoanAmtSanctioned() + " ₹ for "
						+ customerDetails.getSanctionLetter().getLoanTenureInMonth() + " months.\n" + "\n"
						+ "We are confident that you will manage your credit loan responsibly, and we look forward to your continued business.\n"
						+ "\n"
						+ "Should you have any questions about your credit loan, please do not hesitate to contact us.\n"
						+ "\n" + "Thank you for your interest in our services.";

				mimemessageHelper.setText(text);

				mimemessageHelper.addAttachment("loanSanctionLetter.pdf", new ByteArrayResource(sanctionLetter));
				mailSender.send(mimeMessage);
				return "Mail sent successfully to " + customerDetails.getCustomerEmail();

			} catch (Exception e) {
				System.out.println("Email Failed to Send!!!!!!");
				e.printStackTrace();
				return "Failed to send mail to " + customerDetails.getCustomerEmail() + ": " + e.getMessage();
			}
		}
}
	



