package com.biscato.kollaidoscope.persistence;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.biscato.kollaidoscope.model.Team;

public class TeamDAO implements EntityDAO<Team> {

	@Override
	public Team createEntity(Team entity) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Team newEntity;
		
		try{
			newEntity = pm.makePersistent(entity);
		}
		finally{
			pm.close();
		}
		return newEntity;
	}

	@Override
	public List<Team> createAllEntities(List<Team> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(Team entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllEntities() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Team updateEntity(Team entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getEntityForId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Team> getAllEntities() {
		// TODO Auto-generated method stub
		return null;
	}

}
