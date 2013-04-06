package com.biscato.kollaidoscope.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.biscato.kollaidoscope.model.Survey;

public class SurveyDAO implements EntityDAO<Survey> {

	@Override
	public Survey createEntity(Survey entity) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Survey newEntity;
		
		try{
			newEntity = pm.makePersistent(entity);
		}
		finally{
			pm.close();
		}
		return newEntity;
	}

	@Override
	public List<Survey> createAllEntities(List<Survey> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(Survey entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllEntities() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Survey updateEntity(Survey entity) {
		// TODO Implement cancellation / inactivation of survey
		return null;
	}

	@Override
	public Survey getEntityForId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Survey> getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

}
