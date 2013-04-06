package com.biscato.kollaidoscope.persistence;

import java.util.List;

public interface EntityDAO<T> {
	//TODO: read about JAVA-generics --> "<>" notation
	public T createEntity(T entity);
	public List<T> createAllEntities(List<T> entities);
	public boolean deleteEntity(T entity);
	public boolean deleteAllEntities();
	public T updateEntity(T entity);
	public T getEntityForId(long id);
	public List<T> getAllEntities();

}
