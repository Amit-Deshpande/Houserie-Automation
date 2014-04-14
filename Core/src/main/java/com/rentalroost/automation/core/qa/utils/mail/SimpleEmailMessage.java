package com.rentalroost.automation.core.qa.utils.mail;

import java.util.Date;import java.util.List;

/**
 * @author kaushik_vira
 *
 */
public class SimpleEmailMessage {

	private String subject;
	private String bodyContent;
	private String from;
	private String replyTo;
	private String to;
	private Date sentDate;		private List<String> fileName;		
	//Add list of string to store attached file names 		public List<String> getFilename() {		return fileName;	}	public void setFileName(List<String> fileNameList) {		this.fileName = fileNameList;	}
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyConect) {
		this.bodyContent = bodyConect;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sent) {
		this.sentDate = sent;
	}
	
	

}
