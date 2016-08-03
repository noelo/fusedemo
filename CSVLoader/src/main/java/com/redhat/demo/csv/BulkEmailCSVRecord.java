package com.redhat.demo.csv;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord( separator = "," )
public class BulkEmailCSVRecord {
	
	public String getSenderEmail() {
		return SenderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		SenderEmail = senderEmail;
	}

	public String getRecipientEmail() {
		return RecipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		RecipientEmail = recipientEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public int getEmailPriority() {
		return emailPriority;
	}

	public void setEmailPriority(int emailPriority) {
		this.emailPriority = emailPriority;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	@DataField(pos = 1,required = true)
	private String SenderEmail;
	
	@DataField(pos = 2,required = true)
	private String RecipientEmail;
	
	@DataField(pos = 3,required = false)
	private String emailSubject;
	
	@DataField(pos = 4,defaultValue="1")
	private int emailPriority;
	
	@DataField(pos = 5,required = false)
	private String emailBody;
	
	
	

}
