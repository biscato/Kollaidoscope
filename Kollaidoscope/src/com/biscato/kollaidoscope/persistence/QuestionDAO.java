package com.biscato.kollaidoscope.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.biscato.kollaidoscope.model.Question;

public class QuestionDAO implements EntityDAO<Question> {

	@Override
	public List<Question> createAllEntities(List<Question> entities) {
		Collection<Question> newEntities;
		List<Question> createdQuestions;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Collection<Question> collection = new ArrayList<Question>(entities);
		
		try{
			newEntities = pm.makePersistentAll(collection);
		}
		finally{
			pm.close();
		}
		createdQuestions = new ArrayList<Question>(newEntities);
		return createdQuestions;
	}

	@Override
	public boolean deleteAllEntities() {
		//TODO: Error handling delete all questioNDAO
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Query query = pm.newQuery(Question.class);
			Collection<Question> myCol = (Collection<Question>) query.execute();
			pm.deletePersistentAll(myCol);
		}
		finally{
			pm.close();
		}
		return true;
	}

	@Override
	public Question createEntity(Question entity) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Question newEntity;
		try {
			newEntity = pm.makePersistent(entity);
		} finally {
			pm.close();
		}
		return newEntity;
	}

	@Override
	public boolean deleteEntity(Question entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Question updateEntity(Question entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question getEntityForId(long id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return (Question) pm.getObjectById(Question.class, id);		
	}

	@Override
	public List<Question> getAllEntities() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Question.class);
		Collection<Question> myCol = (Collection<Question>) query.execute();
		return new ArrayList<Question>(myCol);
	}
}
