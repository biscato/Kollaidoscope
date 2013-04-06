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
public class Answer {
	
	@PrimaryKey //Annotation for jersey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	private long creationTimestamp; //TODO: implement a real timestamp
	private String user;
	private int rating;
	private long questionId;
	private String questionText;
	private String questionCategory;
	private int questionCategoryId;
	
	public Answer(){
		super();
	}
	
	@XmlElement // why an annotation only for Id? why not for other attribute?
	public long getId() {
		if (key != null)
			return this.key.getId();
		else 
			return -1;
	}
	
	public long getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getQuestionCategory() {
		return questionCategory;
	}
	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}
	public int getQuestionCategoryId() {
		return questionCategoryId;
	}
	public void setQuestionCategoryId(int questionCategoryId) {
		this.questionCategoryId = questionCategoryId;
	}
}
