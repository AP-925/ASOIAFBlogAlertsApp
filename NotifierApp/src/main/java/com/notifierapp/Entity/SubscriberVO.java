package com.notifierapp.Entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@jakarta.persistence.Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubscriberVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubscriberVO() {
		
	}
	public SubscriberVO(String emailID) {
		super();
		this.emailID = emailID;
	}

	@Override
	public String toString() {
		return "SubscriberVO [emailID=" + emailID + ", latestNotifyDate=" + latestNotifyDate + "]";
	}

	@jakarta.persistence.Id
	@jakarta.persistence.Column(name = "subscriberID")
	private String emailID;
	private String latestNotifyDate;

	public String getLatestNotifyDate() {
		return latestNotifyDate;
	}
	public void setLatestNotifyDate(String latestNotifyDate) {
		this.latestNotifyDate = latestNotifyDate;
	}
	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
}
