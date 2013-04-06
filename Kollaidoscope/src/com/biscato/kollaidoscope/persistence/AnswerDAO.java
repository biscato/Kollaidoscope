package com.biscato.kollaidoscope.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.biscato.kollaidoscope.model.Answer;

public class AnswerDAO implements EntityDAO<Answer>{

	@Override
	public Answer createEntity(Answer entity) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Answer newEntity;
		
		try{
			newEntity = pm.makePersistent(entity);
		}
		finally{
			pm.close();
		}
		return newEntity;
	}

	@Override
	public List<Answer> createAllEntities(List<Answer> entities) {
		
		Collection<Answer> newEntities;
		List<Answer> createdAnswers;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Collection<Answer> collection = new ArrayList<Answer>(entities);
		
		try{
			newEntities = pm.makePersistentAll(collection);
		}
		finally{
			pm.close();
		}
		createdAnswers = new ArrayList<Answer>(newEntities);
		return createdAnswers;
	}

	@Override
	public boolean deleteAllEntities() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Query query = pm.newQuery(Answer.class);
			Collection<Answer> myCol = (Collection<Answer>) query.execute();
			pm.deletePersistentAll(myCol);
		}
		finally{
			pm.close();
		}
		return true;
	}

	@Override
	public boolean deleteEntity(Answer entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Answer updateEntity(Answer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Answer getEntityForId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answer> getAllEntities() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Answer.class);
		Collection<Answer> myCol = (Collection<Answer>) query.execute();
		return new ArrayList<Answer>(myCol);
	}
	
	public List<Answer> getEntitiesForTimeframe(long from, long to) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String filter = "(creationTimestamp >= " + from + " && creationTimestamp <= " + to + ")";
		Query query = pm.newQuery(Answer.class, filter);
		Collection<Answer> myCol = (Collection<Answer>) query.execute();
		return new ArrayList<Answer>(myCol);
	}
	
	public List<Answer> getEntitiesForTimeframeAndTeam(long from, long to, int teamId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String filter = "(teamId == " + teamId + ") && (creationTimestamp >= " + from + " && creationTimestamp <= " + to + ")";
		Query query = pm.newQuery(Answer.class, filter);
		Collection<Answer> myCol = (Collection<Answer>) query.execute();
		return new ArrayList<Answer>(myCol);
	}
}
