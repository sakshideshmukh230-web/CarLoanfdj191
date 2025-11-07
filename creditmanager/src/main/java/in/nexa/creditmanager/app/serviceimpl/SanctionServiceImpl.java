package in.nexa.creditmanager.app.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nexa.creditmanager.app.model.CustomerLoanApplication;
import in.nexa.creditmanager.app.model.SanctionLetter;
import in.nexa.creditmanager.app.repo.CustomerLoanApplicationRepo;
import in.nexa.creditmanager.app.repo.SanctionLetterRepo;
import in.nexa.creditmanager.app.servicei.SanctionServiceI;
import jakarta.mail.internet.MimeMessage;

@Service
public class SanctionServiceImpl implements SanctionServiceI {
	

	@Autowired
	private CustomerLoanApplicationRepo clar;
	
	@Autowired
	private SanctionLetterRepo slr;
	
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public CustomerLoanApplication updateSactionLetter(Integer customerId, SanctionLetter sanctionLetter) {
		
		Optional<CustomerLoanApplication> customer = clar.findById(customerId);
		
		if (customer.isEmpty()) {
	        throw new RuntimeException("CustomerLoanApplication not found with ID: " + customerId);
	    }
		
		CustomerLoanApplication customerDetails = customer.get();
		
		
		SanctionLetter sl = new SanctionLetter();
		sl.setSanctionDate(sanctionLetter.getSanctionDate());
        sl.setApplicantName(sanctionLetter.getApplicantName());
        sl.setContactDetails(sanctionLetter.getContactDetails());
        sl.setLoanRequired(sanctionLetter.getLoanRequired());
        sl.setLoanAmtSanctioned(sanctionLetter.getLoanAmtSanctioned());
        sl.setInterestType(sanctionLetter.getInterestType());
        sl.setRateOfInterest(sanctionLetter.getRateOfInterest());
        sl.setLoanTenureInMonth(sanctionLetter.getLoanTenureInMonth());
        sl.setMonthlyEmiAmount(sanctionLetter.getMonthlyEmiAmount());
        sl.setModeOfPayment(sanctionLetter.getModeOfPayment());
        sl.setRemarks(sanctionLetter.getRemarks());
        sl.setTermsCondition(sanctionLetter.getTermsCondition());
        sl.setStatus(sanctionLetter.getStatus());
		
        SanctionLetter saved = slr.save(sl);
		
        if(saved != null) {
        	System.out.println(saved);
        }
		
        customerDetails.setSanctionLetter(saved);

			String title = "ABC Finance Ltd.";

			Document document = new Document(PageSize.A4);

			String content1 = "\n\n Dear " + customerDetails.getCustomerName()
					+ ","
					+ "\nABC Finance Ltd. is Happy to informed you that your loan application has been approved. ";

			String content2 = "\n\nWe hope that you find the terms and conditions of this loan satisfactory "
					+ "and that it will help you meet your financial needs.\n\nIf you have any questions or need any assistance regarding your loan, "
					+ "please do not hesitate to contact us.\n\nWe wish you all the best and thank you for choosing us."
					+ "\n\nSincerely,\n\n" + "Vijay Chaudhari (Credit Manager)";
			
			
			ByteArrayOutputStream opt = new ByteArrayOutputStream();
			
			PdfWriter.getInstance(document, opt);
			document.open();

			Image img = null;
			try {
				img = Image.getInstance("C:\\Users\\91726\\Desktop\\DummyImages\\logo.png");
				img.scalePercent(50, 50);
				img.setAlignment(Element.ALIGN_RIGHT);
				document.add(img);

			} catch (BadElementException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
			Paragraph titlepara = new Paragraph(title, titlefont);
			titlepara.setAlignment(Element.ALIGN_CENTER);
			document.add(titlepara);

			Font titlefont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
			Paragraph paracontent1 = new Paragraph(content1, titlefont2);
			document.add(paracontent1);

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100f);
			table.setWidths(new int[] { 2, 2 });
			table.setSpacingBefore(10);

			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(CMYKColor.WHITE);
			cell.setPadding(5);

			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			font.setColor(5, 5, 161);

			Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(5, 5, 161);

			cell.setPhrase(new Phrase("Applicant Name", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getApplicantName()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Loan Required", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getLoanRequired()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Loan Sanctined", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getLoanAmtSanctioned()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Interest Type", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getInterestType()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Interest Rate", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getRateOfInterest()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Loan Tenure(in months)", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getLoanTenureInMonth()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Monthly EMI amount", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getMonthlyEmiAmount()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Mode Of payment", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getModeOfPayment()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Status", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getStatus()), font1));
			table.addCell(cell);
			
			
			cell.setPhrase(new Phrase("Sanction Letter Generated Date", font));
			table.addCell(cell);
			Date date = new Date();
			customerDetails.getSanctionLetter().setSanctionDate(date.toString());
			cell.setPhrase(new Phrase(String.valueOf(customerDetails.getSanctionLetter().getSanctionDate()), font1));
			table.addCell(cell);
			
			document.add(table);

			Font titlefont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
			Paragraph paracontent2 = new Paragraph(content2, titlefont3);
			document.add(paracontent2);
			document.close();
			
			
			ByteArrayInputStream byt = new ByteArrayInputStream(opt.toByteArray());
			byte[] bytes = byt.readAllBytes();
			customerDetails.getSanctionLetter().setSanctionLetterPdf(bytes);
			
					
			return clar.save(customerDetails);	
	
	}
}
