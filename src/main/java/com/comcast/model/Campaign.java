package com.comcast.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import com.fasterxml.jackson.annotation.*;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Campaign implements Serializable, Comparator<Campaign> 
{

	/**
	 * David L. Adkins
	 * 20160801
	 */
	@JsonIgnore 
	private static final long serialVersionUID = -4086430014675178890L;
	 @JsonCreator
	public Campaign()
	{
		 this.adCreation = new Date();
	}
	//partner_id
	@JsonProperty("partner_id")
	private String partnerId;
	//duration in seconds
	@JsonProperty
	private Integer duration;
	
	@JsonIgnore 
	private Date adCreation;
	
	@JsonProperty("ad_content")
	private String adContent;
	
	@JsonIgnore
	private Boolean expired = false;
	
	
	public Campaign(String partnerId, Integer duration, String adContent )
	{
		this.adCreation = new Date();
		this.adContent = adContent;
		this.duration = duration;
		this.partnerId = partnerId;
	}	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Campaign [partnerId=" + partnerId + ", duration=" + duration + ", adCreation=" + adCreation
				+ ", adContent=" + adContent + ", isExpired()=" + expired + "]";
	}
	@JsonProperty("partner_id")
	public String getPartnerId()
	{
		return partnerId;
	}
	@JsonProperty("partner_id")
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	@JsonProperty
	public Integer getDuration() {
		return duration;
	}
	@JsonProperty
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the adContent
	 */
	@JsonProperty("ad_content")
	public String getAdContent() {
		return adContent;
	}

	/**
	 * @param adContent the adContent to set
	 */
	@JsonProperty("ad_content")
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	/**
	 * @return the expired
	 */
	@JsonIgnore
	public Boolean getExpired() 
	{
		Date currentDate = new Date();
		long adExpiration = this.adCreation.getTime() + this.getDuration()*1000; 
		//System.out.println(adExpiration + ":" + currentDate.getTime());
		expired = (currentDate.getTime() >= adExpiration);
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	@JsonIgnore
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	@Override
	public int compare(Campaign o1, Campaign o2) 
	{
		return o1.adCreation.compareTo(o2.adCreation);
	}
	
	
}
