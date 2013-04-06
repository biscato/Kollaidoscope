package com.biscato.kollaidoscope.model;

import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Key;

@XmlRootElement
@PersistenceCapable
public class Survey {
	
	private Key key;
	private long startTimestamp;
	private long endTimestamp;
	private int[] participant;
	private int[] teamLead;
	private boolean active;
	
	public Survey(){
		super();
	}
	
	@XmlElement // why an annotation only for Id? why not for other attribute?
	public long getId() {
		if (key != null)
			return this.key.getId();
		else 
			return -1;
	}
	
	public long getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public long getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	public int[] getParticipant() {
		return participant;
	}
	public void setParticipant(int[] participant) {
		this.participant = participant;
	}
	public int[] getTeamLead() {
		return teamLead;
	}
	public void setTeamLead(int[] teamLead) {
		this.teamLead = teamLead;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
