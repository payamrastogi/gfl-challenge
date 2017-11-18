package com.gfl.sfbay.model.operator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperatorResponseModel
{
	@JsonProperty("Id")
	private String id;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("ShortName")
	private String shortName;
	@JsonProperty("SiriOperatorRef")
	private String siriOperatorRef;
	@JsonProperty("TimeZone")
	private String timeZone;
	@JsonProperty("DefaultLanguage")
	private String defaultLanguage;
	@JsonProperty("ContactTelephoneNumber")
	private String contactTelephoneNumber;
	@JsonProperty("WebSite")
	private String website;
	@JsonProperty("PrimaryMode")
	private String primaryMode;
	@JsonProperty("PrivateCode")
	private String privateCode;
	@JsonProperty("Montiored")
	private boolean monitored;
	@JsonProperty("OtherModes")
	private String otherModes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getSiriOperatorRef() {
		return siriOperatorRef;
	}
	public void setSiriOperatorRef(String siriOperatorRef) {
		this.siriOperatorRef = siriOperatorRef;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getDefaultLanguage() {
		return defaultLanguage;
	}
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}
	public String getContactTelephoneNumber() {
		return contactTelephoneNumber;
	}
	public void setContactTelephoneNumber(String contactTelephoneNumber) {
		this.contactTelephoneNumber = contactTelephoneNumber;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPrimaryMode() {
		return primaryMode;
	}
	public void setPrimaryMode(String primaryMode) {
		this.primaryMode = primaryMode;
	}
	public String getPrivateCode() {
		return privateCode;
	}
	public void setPrivateCode(String privateCode) {
		this.privateCode = privateCode;
	}
	public boolean isMonitored() {
		return monitored;
	}
	public void setMonitored(boolean monitored) {
		this.monitored = monitored;
	}
	public String getOtherModes() {
		return otherModes;
	}
	public void setOtherModes(String otherModes) {
		this.otherModes = otherModes;
	}
}
