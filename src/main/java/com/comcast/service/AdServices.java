package com.comcast.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.comcast.model.Campaign;
import com.comcast.model.Campaigns;

@Path("/ad")
public class AdServices 
{
	public static ConcurrentHashMap<String,Campaigns> campaignDB = new ConcurrentHashMap<>();
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Campaign> campaingList()
	{
		List<Campaign> campaignList = new ArrayList<>();
		Campaign camp1 = new Campaign("acme",30,"new campaign");
		campaignList.add(camp1);
		return campaignList;
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Campaign campaingById(@PathParam("id") String id) throws Exception
	{
		if(!campaignDB.containsKey(id))
			throw new Exception("no active campaings");
		if(campaignDB.get(id).getLast().getExpired())  
			throw new Exception("no active campaings");
		else 
			return campaignDB.get(id).getLast();
	}
	
	@GET
	@Path("/all/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Campaigns getPartnerAds(@PathParam("id") String id) throws Exception 
	{
		if(campaignDB.containsKey(id))  
				return campaignDB.get(id);
		else
			throw new Exception ("No campaigns found");
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Boolean addCampaing(Campaign campaign) throws Exception
	{
		if(campaignDB.containsKey(campaign.getPartnerId()))
		{
			Campaigns campaigns = campaignDB.get(campaign.getPartnerId());
			if(!campaigns.add(campaign) )
			{
				throw new Exception("Invalid Campaing");
			}
		}
		else //new partner;
		{	
			Campaigns campaigns = new Campaigns();
			if(campaigns.add(campaign))
				campaignDB.put(campaign.getPartnerId(), campaigns);
			else
			{
				throw new Exception("Invalid Campaing");
			}
		}
		return true;
	
	}
	
}



