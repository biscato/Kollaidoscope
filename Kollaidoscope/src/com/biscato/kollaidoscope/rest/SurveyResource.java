package com.biscato.kollaidoscope.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.Survey;
import com.biscato.kollaidoscope.persistence.SurveyDAO;

@Path("/surveys")
public class SurveyResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Survey> getSurveys() {
		SurveyDAO surveyDAO = new SurveyDAO();
 		return surveyDAO.getAllEntities();
	}

	@GET
	@Path("{id}")
	public Survey getSurveyForId(@PathParam("id") long id) {
		SurveyDAO surveyDAO = new SurveyDAO();
		//TODO: Error handling: a) wrong id b) no id (e.g. character instead of int) c) other error
		return surveyDAO.getEntityForId(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Survey createQuestion(Survey newSurvey)	 {
		SurveyDAO surveyDAO = new SurveyDAO();
		//TODO: Error handling when wrong data is supplied or information is missing
		return surveyDAO.createEntity(newSurvey);
	}
	
	//TODO: Implement PUT / UPDATE of survey
}
