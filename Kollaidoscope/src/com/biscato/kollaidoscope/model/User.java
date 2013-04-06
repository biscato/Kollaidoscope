package com.biscato.kollaidoscope.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.appengine.api.datastore.Key;

@XmlRootElement
@PersistenceCapable
public class User {

	@PrimaryKey //Annotation for jersey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	private long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String company;
	private long[] memberInTeam;
	private String role;
	private Date validFrom;
	private Date validTo;
	
	public User(){
		super();
	}
	
	@XmlElement // why an annotation only for Id? why not for other attribute?
	public long getId() {
		if (key != null)
			return this.key.getId();
		else 
			return -1;
	}
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public long[] getMemberInTeam() {
		return memberInTeam;
	}

	public void setMemberInTeam(long[] memberInTeam) {
		this.memberInTeam = memberInTeam;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
}
