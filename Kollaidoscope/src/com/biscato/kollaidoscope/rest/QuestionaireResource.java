package com.biscato.kollaidoscope.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.Question;
import com.biscato.kollaidoscope.persistence.QuestionDAO;
import com.biscato.kollaidoscope.util.QuestionRankComparator;


@Path("/questionaire")
public class QuestionaireResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Question> getAllQuestionaire() {
		QuestionDAO questionDAO = new QuestionDAO();
		List<Question> questionList = questionDAO.getAllEntities();
		Collections.sort(questionList,new QuestionRankComparator());
 		return questionList;
	}

	@GET
	@Path("{id}")
	public Question getQuestionForId(@PathParam("id") long id) {
		QuestionDAO questionDAO = new QuestionDAO();
		//TODO: Error handling: a) wrong id b) no id (e.g. character instead of int) c) other error
		return questionDAO.getEntityForId(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Question> createQuestion(List<Question> newQuestions)	 {
		QuestionDAO questionDAO = new QuestionDAO();
		//TODO: Error handling when wrong data is supplied or information is missing
		
		ArrayList<Question> list = new ArrayList<Question>();
		for(Question quest : newQuestions){
			list.add(questionDAO.createEntity(quest));
		}
		return list;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAllQuestions(){
		QuestionDAO question = new QuestionDAO();
		question.deleteAllEntities();
	}
}
