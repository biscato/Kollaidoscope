package com.biscato.kollaidoscope.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.Answer;
import com.biscato.kollaidoscope.persistence.AnswerDAO;

@Path("/answers")
public class AnswerResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> createVoting(List<Answer> answers){
		//TODO: Error handling
		AnswerDAO answer = new AnswerDAO();
		ArrayList<Answer> list = new ArrayList<Answer>();
		Calendar cal = Calendar.getInstance();
		for(Answer ans : answers){
			ans.setCreationTimestamp(cal.getTimeInMillis());
			list.add(answer.createEntity(ans));
		}
		return list;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	//TODO: return boolean for success or failure
	public void deleteAllVotes(){
		AnswerDAO answer = new AnswerDAO();
		answer.deleteAllEntities();
	}
}
