package com.comcast.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Campaigns extends LinkedList<Campaign> implements Serializable 
{

	/**
	 * David L. Adkins
	 * 20160801
	 */
	private static final long serialVersionUID = -4086430014675178890L;
	//private static ConcurrentHashMap<String,Campaigns> ADCapaign = new ConcurrentHashMap<>(); 
	
	
	
	@Override
	public boolean add(Campaign campaign) 
	{
		if(this.isEmpty())
			return super.add(campaign);
		
		if(this.contains(campaign))
			return false;
		else
		{
			if(this.getLast().getExpired())
				return super.add(campaign);
			else
				return false;
		}
	}
	
}
