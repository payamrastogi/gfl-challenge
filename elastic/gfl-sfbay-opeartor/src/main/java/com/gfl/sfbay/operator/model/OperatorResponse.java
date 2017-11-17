package com.gfl.sfbay.operator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperatorResponse
{
	@JsonProperty("Id")
	public String id;
	@JsonProperty("Name")
	public String name;
	@JsonProperty("ShortName")
	public String shortName;
	@JsonProperty("SiriOperatorRef")
	public String siriOperatorRef;
	@JsonProperty("TimeZone")
	public String timeZone;
	@JsonProperty("DefaultLanguage")
	public String defaultLanguage;
	@JsonProperty("ContactTelephoneNumber")
	public String contactTelephoneNumber;
	@JsonProperty("WebSite")
	public String website;
	@JsonProperty("PrimaryMode")
	public String primaryMode;
	@JsonProperty("PrivateCode")
	public String privateCode;
	@JsonProperty("Montiored")
	public boolean monitored;
	@JsonProperty("OtherModes")
	public String otherModes;
}
