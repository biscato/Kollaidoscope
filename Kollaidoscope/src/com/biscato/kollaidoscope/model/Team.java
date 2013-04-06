package com.biscato.kollaidoscope.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Key;

@XmlRootElement
@PersistenceCapable
public class Team {

	@PrimaryKey //Annotation for jersey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	private int teamId;
	private String name;
	private String company;
	private String email;
	private String website;
	private boolean active;
	
	public Team(){
		super();
	}
	
	@XmlElement // why an annotation only for Id? why not for other attribute?
	public long getId() {
		if (key != null)
			return this.key.getId();
		else 
			return -1;
	}
	

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
