package com.biscato.kollaidoscope.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.Answer;
import com.biscato.kollaidoscope.model.Question;
import com.biscato.kollaidoscope.persistence.AnswerDAO;
import com.biscato.kollaidoscope.persistence.QuestionDAO;

@Path("/testanswer")
public class TestAnswerResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> createVoting(){
		ArrayList<Answer> answers = new ArrayList<Answer>();
		answers = createAnswers();
		answers = persistAnswers(answers);
		return answers;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	//TODO: return boolean for success or failure
	public void deleteAllVotes(){
		AnswerDAO answer = new AnswerDAO();
		answer.deleteAllEntities();
	}
	
	private ArrayList<Answer> persistAnswers(ArrayList<Answer> answers){
		AnswerDAO answer = new AnswerDAO();
		ArrayList<Answer> list = new ArrayList<Answer>();
		for(Answer ans : answers){
			list.add(answer.createEntity(ans));
		}
		return list;
	}
	
	private ArrayList<Answer> createAnswers(){
		
		//get different timestamps for simulation over time
		long[] timestamps = getTimestamps();
		
		//get questions
		QuestionDAO questionDAO = new QuestionDAO();
 		List<Question> questions = questionDAO.getAllEntities();
		
		//get users
		String[] users = getTestUsers();
		
		//create answers for each user, timestamp with random rating
		ArrayList<Answer> answers = new ArrayList<Answer>();
		AnswerDAO answerDAO = new AnswerDAO();
		
		for(Question question : questions){
			for(String user : users){
				for(long timestamp : timestamps){
					Answer answer = new Answer();
					answer.setCreationTimestamp(timestamp);
					answer.setQuestionCategory(question.getCategory());
					answer.setQuestionCategoryId(question.getCategoryId());
					answer.setQuestionId(question.getId());
					answer.setQuestionText(question.getDescription());
					answer.setRating(getRandomRating(0,10));
					answer.setUser(user);
					answers.add(answerDAO.createEntity(answer));
				}
			}
		}
		
		return answers;
	}
	
	private int getRandomRating(int low, int high){
		Random aRandom = new Random();
	    if ( low > high ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)high - (long)low + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + low);
	    return randomNumber;
	}

	
	private long[] getTimestamps(){
		
		long[] timestamps = new long[8];
		
		Calendar minus1Week  = Calendar.getInstance();
		minus1Week.add(Calendar.WEEK_OF_YEAR, -1);
		timestamps[0] = minus1Week.getTimeInMillis();
		Calendar minus2Week  = Calendar.getInstance();
		minus2Week.add(Calendar.WEEK_OF_YEAR, -2);
		timestamps[1] = minus2Week.getTimeInMillis();
		Calendar minus3Week  = Calendar.getInstance();
		minus3Week.add(Calendar.WEEK_OF_YEAR, -3);
		timestamps[2] = minus3Week.getTimeInMillis();
		Calendar minus4Week  = Calendar.getInstance();
		minus4Week.add(Calendar.WEEK_OF_YEAR, -4);
		timestamps[3] = minus4Week.getTimeInMillis();
		
		Calendar minus3Months  = Calendar.getInstance();
		minus3Months.add(Calendar.WEEK_OF_YEAR, -13);
		timestamps[4] = minus3Months.getTimeInMillis();
		Calendar minus6Months  = Calendar.getInstance();
		minus6Months.add(Calendar.WEEK_OF_YEAR, -26);
		timestamps[5] = minus6Months.getTimeInMillis();
		Calendar minus12Months  = Calendar.getInstance();
		minus12Months.add(Calendar.WEEK_OF_YEAR, -52);
		timestamps[6] = minus12Months.getTimeInMillis();
		
		Calendar all  = Calendar.getInstance();
		all.set(1980, 6, 27);	
		timestamps[7] = all.getTimeInMillis();
		
		return timestamps;
	}
	
	private String[] getTestUsers(){
		String[] users = {
			"Hans.Mueller@gmail.com",
			"Georg.Friedrich@gmail.com",
			"Marcel.Breitling@gmail.com",
			"Uwe.Kunz@gmail.com",
			"Trevor.Hoffmann@gmail.com",
			"Wilhelm.Schmitt@gmail.com",
			"Kevin.Krauth@gmail.com",
			"Martin.Baumann@gmail.com",
			"Matt.Cain@gmail.com"
		};
		return users;
	}
}
